package ExtractionMetrics;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class WebRequest {
    public static void main(String[] args) {
        //base64Encoding();
        String url = "https://console.cloud.google.com/bigquery";

        String url1 = "https://fivetran.slab.com/topics/runbooks-p76wadai";

        String url2 = "https://fivetran.slab.com/templates/service-documentation-zcs1jv6d";

        String url3 = "https://fivetran.slab.com/posts/extraction-metrics-for-api-based-connectors-jrcg4hkk";

        String url4 = "https://chat.openai.com/";

        String url5 = "https://www.hackerrank.com/x/library/hackerrank/all/questions/1485877/view";
        parseUrl(url);
        parseUrl(url1);
        parseUrl(url2);
        parseUrl(url3);
        parseUrl(url4);
        parseUrl(url5);
    }

    private static void base64Encoding() {
        String encoding =
                Base64.getEncoder()
                        .encodeToString(
                                (String.format("%s:%s", "lmcneil", "22DDx0y4ygbufQTv"))
                                        .getBytes());
        System.out.println(encoding);
    }

    private static void parseUrl(String url){
       // String url = "https://docs.google.com/spreadsheets/d/afaf";

        try {
            URI uri = new URI(url);

            // Extract base URL (scheme + authority)
            String baseUrl = uri.getScheme() + "://" + uri.getAuthority();

            // Extract path
            String path = uri.getPath();
            String[] pathSegments = path.split("/");
            String firstPathSegment = pathSegments.length > 1 ? pathSegments[1] : "";

            System.out.println("Base URL: " + baseUrl);
            System.out.println("First Path Segment: " + firstPathSegment);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
