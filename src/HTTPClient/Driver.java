package HTTPClient;

public class Driver {
    public static void main(String[] args) {

        Object event =
                new Object() {
                    public final String owner = "owner";
                    public final String schemaName = "schema";
                };
        HttpClientOptions httpClientOptions = new HttpClientOptions();
        //httpClientOptions.triggerPOST(event);
    }
}
