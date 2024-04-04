package HTTPClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;

public class DefaultObjectMapper {
    public static final ObjectMapper UNDERSCORE_JSON = createUnderscoreObjectMapper();

    private static ObjectMapper createUnderscoreObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
//
//        //mapper.registerModule(new JavaTimeModule());
//        mapper.registerModule(new Jdk8Module());
//
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        return mapper;
    }

    public static final Client HTTP = createClient();

    private static Client createClient() {
        //return ClientBuilder.newClient(new ClientConfig().register(new JacksonJsonProvider(UNDERSCORE_JSON)));
        return ClientBuilder.newBuilder().register(new JacksonJsonProvider(UNDERSCORE_JSON)).build();
    }
}
