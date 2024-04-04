package HTTPClient;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static HTTPClient.DefaultObjectMapper.UNDERSCORE_JSON;

public class HttpClientOptions {
    public static final Client HTTP = createHttpClient();
    private static Client createHttpClient() {
        return ClientBuilder.newBuilder().register(new JacksonJsonProvider(UNDERSCORE_JSON)).build();
    }

    public void triggerPOST(Object event){

        Response response = DefaultObjectMapper.HTTP
                .target("http://localhost:9000/eventPost")
                .request()
                .post(Entity.entity(event, MediaType.APPLICATION_JSON_TYPE));

         response.getLength();
        boolean a = response.bufferEntity();

        System.out.println( a );
    }

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        client.property(ClientProperties.READ_TIMEOUT,    1000);

        WebTarget target = client.target("https://jsonplaceholder.typicode.com/posts/1");

        try {
            String responseMsg = target.path("").request().get(String.class);
            System.out.println("responseMsg: " + responseMsg);
        } catch (ProcessingException pe) {
            pe.printStackTrace();
        }

    }
}
