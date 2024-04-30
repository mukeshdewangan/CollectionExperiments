package ExtractionMetrics;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

/**
 * This is an HTTP request and response filters class, which intercept the request and response. It tracks the
 * request-response time and total number of API calls being made throughout its life cycle. Currently, we publish below
 * metrics -
 *
 * <p>1. Total number of API calls. 2. Total time taken for those calls. 3. Number of API calls per endpoint. 4. Total
 * Time taken for api calls per endpoint. 5. Average time for response per endpoint. 6. Maximum number of parallel
 * requests. 7. Total number of API Calls for each status code per endpoint.
 */
public class RequestResponseTracker implements ClientRequestFilter, ClientResponseFilter {
    private static final String REQUEST_IDENTIFIER = "REQUEST_IDENTIFIER";
    private static final String API_CALLS = "api_calls";
    private static final String AVERAGE_TIME_PER_CALL = "average_time_per_call";
    private static final String TOTAL_API_CALLS = "total_api_calls";
    private static final String TOTAL_TIME_IN_MS = "total_time_in_ms";
    private static final String STATUS_CODES = "status_codes";
    private static final String MAXIMUM_PARALLEL_CALLS = "maximum_parallel_calls";
    private static final String API_EXTRACTION_METRICS = "api_extraction_metrics";
    private static final int DEFAULT_MAXIMUM_ENDPOINTS_TO_TRACK = 500;
    private static final int DEFAULT_COMPACTION_SIZE = 200;
    //private static final FivetranLogger LOG = FivetranLogger.getMainLogger();
    private final Set<String> endpointsWithPathVariable = new HashSet<>();
    // The map to track the number of api calls and response time per endpoint
    private final Map<String, EndpointTracker> endpointTrackerMap = new HashMap<>();
    // Total number of api calls
    private final AtomicLong totalCalls = new AtomicLong(0L);
    private final Map<String, Long> inProgressRequestTracker = new ConcurrentHashMap<>();
    private int maximumEndpointsToTrack = DEFAULT_MAXIMUM_ENDPOINTS_TO_TRACK;
    private int compactionSize = DEFAULT_COMPACTION_SIZE;
    // Total time taken in request-response, this implicitly takes cares of parallel calls.
    private long totalTime = 0L;
    // Maximum parallel calls during a sync
    private int maximumParallelCalls;
    private long requestsStartTime;
    private int inProgressRequests;
    private EndpointUrlParser endpointUrlParser = null;
    private boolean useEndpointUrlParser = false;

    private RequestResponseTracker() {}

    private static class RequestResponseTrackerHolder {
        private static final RequestResponseTracker INSTANCE = new RequestResponseTracker();
    }

    public long getEndpointCounter(String endpoint) {
        return endpointTrackerMap.getOrDefault(endpoint, new EndpointTracker()).getApiCallsCounter();
    }

    public long getTotalCalls() {
        return totalCalls.get();
    }

    public long getTotalTime() {
        return totalTime;
    }

    public int getMaximumParallelCalls() {
        return maximumParallelCalls;
    }

    public long getInProgressRequestTrackerSize() {
        return inProgressRequestTracker.size();
    }

    public int getEndpointTrackerSize() {
        return endpointTrackerMap.size();
    }

    @VisibleForTesting
    void setMaximumEndpointsToTrack(int maximumEndpointsToTrack) {
        this.maximumEndpointsToTrack = maximumEndpointsToTrack;
    }

    @VisibleForTesting
    void setCompactionSize(int compactionSize) {
        this.compactionSize = compactionSize;
    }

    /**
     * This function returns an instance of {@link RequestResponseTracker}. The private inner static class that contains
     * the instance of the singleton class. When the RequestResponseTracker, a singleton class is loaded,
     * RequestResponseTrackerHolder class is not loaded into memory and only when someone calls the getInstance()
     * method, this class gets loaded and creates the singleton class instance. This does not require synchronization.
     * Reference <a
     * href="https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">Initialization-on-demand_holder_idiom</a>
     *
     * @return a singleton instance of {@link RequestResponseTracker}
     */
    public static RequestResponseTracker getInstance() {
        return RequestResponseTrackerHolder.INSTANCE;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        long startTime = System.currentTimeMillis();
        String apiEndpointToTrack = requestContext.getUri().getPath();
        String apiEndpoint = getFinalEndpointToTrack(apiEndpointToTrack);
        String requestIdentifier = UUID.randomUUID().toString();
        requestContext.setProperty(REQUEST_IDENTIFIER, requestIdentifier);
        updateInProgressRequests(apiEndpoint, requestIdentifier, startTime);
    }

    private void updateInProgressRequests(String endpoint, String requestIdentifier, long startTime) {
        synchronized (this) {
            inProgressRequestTracker.put(requestIdentifier, startTime);
            if (inProgressRequests++ == 0) {
                requestsStartTime = startTime;
            }
            maximumParallelCalls = Math.max(maximumParallelCalls, inProgressRequests);
            EndpointTracker endpointTracker = endpointTrackerMap.getOrDefault(endpoint, new EndpointTracker());
            endpointTracker.addRequest(requestIdentifier, startTime);
            endpointTrackerMap.put(endpoint, endpointTracker);
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        long endTime = System.currentTimeMillis();
        totalCalls.incrementAndGet();
        int statusCode = responseContext.getStatus();
        String requestIdentifier = (String) requestContext.getProperty(REQUEST_IDENTIFIER);
        String apiEndpoint = getFinalEndpointToTrack(requestContext.getUri().getPath());
        if (inProgressRequestTracker.containsKey(requestIdentifier)) {
            addResponseTime(apiEndpoint, requestIdentifier, endTime, statusCode);
        } else {
            //LOG.warning("Request tracker did not find start time for request " + requestContext.getUri());
        }
    }

    private void addResponseTime(String apiEndpoint, String requestIdentifier, long endTime, int statusCode) {
        Long startTime = inProgressRequestTracker.get(requestIdentifier);
        long responseTime = endTime - startTime;

        synchronized (this) {
            compactTrackerIfRequired(apiEndpoint);
            updateResponseTimeForEndpoint(apiEndpoint, requestIdentifier, responseTime, endTime, statusCode);
            inProgressRequestTracker.remove(requestIdentifier);
            if (--inProgressRequests == 0) {
                totalTime += (endTime - requestsStartTime);
                requestsStartTime = endTime; // Just to ensure the start time is updated
            }
        }
    }

    public void addEndpointsWithPathVariables(String endpoint) {
        endpointsWithPathVariable.add(endpoint);
    }

    private String getFinalEndpointToTrack(String apiPath) {
        if (useEndpointUrlParser) {
            Optional<String> wildCardedEndpoint = endpointUrlParser.findEndpoint(apiPath);
            if (wildCardedEndpoint.isPresent()) return wildCardedEndpoint.get();
        }
        for (String endPoint : endpointsWithPathVariable) {
            if (apiPath.startsWith(endPoint)) return endPoint;
        }
        return apiPath;
    }

    private void compactTrackerIfRequired(String endpoint) {
        if ((endpointTrackerMap.size() < maximumEndpointsToTrack)
                || (endpointTrackerMap.size() == maximumEndpointsToTrack && endpointTrackerMap.containsKey(endpoint)))
            return;

        List<Map.Entry<String, EndpointTracker>> list = new ArrayList<>(endpointTrackerMap.entrySet());
        Comparator<Map.Entry<String, EndpointTracker>> valueComparator =
                (entry1, entry2) ->
                        Math.toIntExact(
                                (entry2.getValue().getApiCallsCounter()) - (entry1.getValue().getApiCallsCounter()));

        list.sort(valueComparator);

        List<Map.Entry<String, EndpointTracker>> subList = list.subList(0, compactionSize);
        endpointTrackerMap.clear();
        for (Map.Entry<String, EndpointTracker> entry : subList) {
            endpointTrackerMap.put(entry.getKey(), entry.getValue());
        }
    }

    /** This method resets all the private variable of the class. Currently, this method is being used by unit tests */
    @VisibleForTesting
    void resetMetrics() {
        totalCalls.set(0L);
        totalTime = 0L;
        endpointTrackerMap.clear();
        inProgressRequestTracker.clear();
        maximumParallelCalls = 0;
        inProgressRequests = 0;
        requestsStartTime = 0L;
        maximumEndpointsToTrack = DEFAULT_MAXIMUM_ENDPOINTS_TO_TRACK;
        compactionSize = DEFAULT_COMPACTION_SIZE;
        useEndpointUrlParser = false;
    }

    @VisibleForTesting
    Map<Integer, Integer> getStatusMapForEndpoint(String endpoint) {
        return endpointTrackerMap.containsKey(endpoint)
                ? new HashMap<>(endpointTrackerMap.get(endpoint).getStatusCodeMap())
                : new HashMap<>();
    }

    /**
     * Populates the API metrics to sync_stats.extract_metrics. Note: We publish the extraction metrics at the end of
     * the updater and syncMetricsMap is populated at that moment. But internally, it is published at the end of the
     * donkey code. After we publish the syncMetricsMap at the end of the updater, it is possible that some API calls
     * are still made. Eg. We observe that the standard config is again called near the end of sync after we publish the
     * api extraction metrics in the updater. So, there is a chance that these extra calls may mutate the map contents.
     * Hence, the syncMetricsMap should contain immutable copies of the data.
     */
    public void publishExtractMetricsToSyncStatistics() {
        if (inProgressRequests != 0) {
            //LOG.severe(inProgressRequests + " calls are still in progress");
        }

        Map<String, Object> syncMetricsMap = new HashMap<>();
        for (Map.Entry<String, EndpointTracker> endpointTracker : endpointTrackerMap.entrySet()) {
            Map<String, Object> value = new HashMap<>();
            long totalApiCalls = endpointTracker.getValue().getApiCallsCounter();
            long cumulativeResponseTime = endpointTracker.getValue().getTotalResponseTime();
            long totalDuration = endpointTracker.getValue().getParallelTotalDuration();
            value.put(API_CALLS, totalApiCalls);
            value.put(AVERAGE_TIME_PER_CALL, cumulativeResponseTime / totalApiCalls);
            value.put(TOTAL_TIME_IN_MS, totalDuration);
            value.put(STATUS_CODES, endpointTracker.getValue().getStatusCodeMap());
            syncMetricsMap.put(endpointTracker.getKey(), value);
        }

        syncMetricsMap.put(TOTAL_API_CALLS, totalCalls.get());
        syncMetricsMap.put(TOTAL_TIME_IN_MS, totalTime);
        syncMetricsMap.put(MAXIMUM_PARALLEL_CALLS, maximumParallelCalls);

        //SyncStatistics.STATS.recordExtractMetrics(API_EXTRACTION_METRICS, syncMetricsMap);
    }

    private void updateResponseTimeForEndpoint(
            String endpoint, String identifier, long responseTime, long endTime, int statusCode) {
        EndpointTracker endpointTracker = endpointTrackerMap.getOrDefault(endpoint, new EndpointTracker());
        endpointTracker.addResponseTime(responseTime);
        endpointTracker.removeRequest(identifier, endTime);
        endpointTracker.incrementStatusCodeCount(statusCode);
        endpointTrackerMap.put(endpoint, endpointTracker);
    }

    /**
     * Sets the list of endpoints to track for monitoring purposes from a configuration file.
     *
     * <p>This method reads a configuration file and parses it to extract a list of endpoint URLs. This method
     * suppresses any exception that might occur while opening the file so as the sync does not fail
     *
     * @param filePath The path to the configuration file containing the list of endpoints.
     */
    public void setEndpointsToTrackFromConfigFile(String filePath) {
        List<String> endpoints = readEndpointsFromConfig(filePath);
        endpointUrlParser = new EndpointUrlParser();
        endpointUrlParser.createParseTree(endpoints);
        useEndpointUrlParser = true;
    }

    private String loadContentFromFile(String fileName) throws IOException {
        URL resourceUrl = Resources.getResource(getClass(), fileName);
        return Resources.toString(resourceUrl, StandardCharsets.UTF_8);
    }

    List<String> readEndpointsFromConfig(String filePath) {
        RequestResponseTrackerConfiguration configuration;
        final ObjectMapper yamlMapper =
                new ObjectMapper(
                        new YAMLFactory()
                                .setStreamReadConstraints(
                                        StreamReadConstraints.builder().maxStringLength(Integer.MAX_VALUE).build()));
        try {
            String fileContent = loadContentFromFile(filePath);
            configuration = yamlMapper.readValue(fileContent, RequestResponseTrackerConfiguration.class);
        } catch (IOException | IllegalArgumentException e) {
            //LOG.warning("failed to read resource file: " + filePath + " with exception " + e);
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(configuration.endpoints);
    }
}

class EndpointTracker {
    // Total response time per endpoint during a sync, every call for that endpoint are tracked separately even if they
    // called in-parallel
    private long totalResponseTime = 0L;
    // When there are overlapping calls for the same endpoint, this variable store total duration for this endpoint is
    // busy.
    // Example - Suppose request named r1, request r2 has corresponding response times as t1 and t2, if there run
    // sequentially then
    // parallelTotalDuration == totalResponseTime (t1+t2) but if there executed parallely then parallelTotalDuration >
    // totalResponseTime;
    private long parallelTotalDuration = 0L;
    // Total number of api calls per endpoint
    private long callsCounter = 0L;
    private long startTime;

    final Set<String> inProgressRequests = new HashSet<>();
    private final Map<Integer, Integer> statusCodeMap = new HashMap<>();

    void addResponseTime(long responseTime) {
        totalResponseTime += responseTime;
        callsCounter++;
    }

    long getTotalResponseTime() {
        return totalResponseTime;
    }

    long getApiCallsCounter() {
        return callsCounter;
    }

    long getParallelTotalDuration() {
        return parallelTotalDuration;
    }

    Map<Integer, Integer> getStatusCodeMap() {
        return new HashMap<>(statusCodeMap);
    }

    void incrementStatusCodeCount(int statusCode) {
        statusCodeMap.put(statusCode, statusCodeMap.getOrDefault(statusCode, 0) + 1);
    }

    void addRequest(String requestIdentifier, long startTime) {
        if (inProgressRequests.isEmpty()) {
            this.startTime = startTime;
        }
        inProgressRequests.add(requestIdentifier);
    }

    void removeRequest(String requestIdentifier, long endTime) {
        inProgressRequests.remove(requestIdentifier);
        if (inProgressRequests.isEmpty()) {
            parallelTotalDuration += (endTime - startTime);
        }
    }
}

/**
 * Parses endpoint URLs or paths into a structured representation.
 *
 * <p>This creates a tree internally to parse the endpoint paths and returns the matching path. This handles this issues
 * with path parameters in the endpoints.
 */
class EndpointUrlParser {
    private static final String WILD_CARD = "WILD_CARD";
    private final Node root = new Node("/");

    private static class Node {
        String token;
        String endpoint = null;
        Map<String, Node> children = new HashMap<>();

        Node(String token) {
            this.token = token;
        }
    }

    /**
     * Creates a parse tree structure for the provided list of endpoints.
     *
     * @param endpoints a list of strings representing endpoint paths
     */
    void createParseTree(List<String> endpoints) {
        for (var endpoint : endpoints) addEndpointToTree(endpoint);
    }

    private void addEndpointToTree(String endpoint) {
        String[] tokens = endpoint.split("/");
        Node currentNode = root;

        for (String token : tokens) {
            if (token.isBlank()) continue;
            String actualToken = isWildCard(token) ? WILD_CARD : token;
            if (!currentNode.children.containsKey(actualToken)) {
                Node childNode = new Node(actualToken);
                currentNode.children.put(actualToken, childNode);
            }
            currentNode = currentNode.children.get(actualToken);
        }
        currentNode.endpoint = endpoint;
    }

    private static boolean isWildCard(String token) {
        return token.startsWith("{") && token.endsWith("}");
    }

    /**
     * Searches for a specific endpoint within an existing parse tree structure.
     *
     * <p>This method takes a string representing the target endpoint (URL or path) and attempts to locate it within the
     * parse tree. If the endpoint is found, an `Optional` containing the endpoint string is returned. Otherwise, an
     * empty `Optional` is returned.
     *
     * @param endpoint the string representing the endpoint to search for
     * @return an `Optional<String>` containing the endpoint if found, otherwise empty
     */
    Optional<String> findEndpoint(String endpoint) {
        String[] tokens = endpoint.split("/");
        Node currentNode = root;

        for (String token : tokens) {
            if (token.isBlank()) continue;
            if (currentNode.children.containsKey(token)) {
                currentNode = currentNode.children.get(token);
            } else if (currentNode.children.containsKey(WILD_CARD)) {
                currentNode = currentNode.children.get(WILD_CARD);
            } else {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(currentNode.endpoint);
    }
}

/**
 * Configuration class for the request/response tracker.
 *
 * <p>This class defines the configuration options for the request/response tracker component. This configuration is
 * created with a yaml file passed by the services employing it.
 *
 * <p>Endpoint paths can be added normally. However, the endpoints with path parameters would require wild-carding in
 * their inputs like /v1/products/{product_id}. The {} indicates that the particular segment of the path is a wildcard.
 */
class RequestResponseTrackerConfiguration {
    public List<String> endpoints;
}
