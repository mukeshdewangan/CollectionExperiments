package CustomConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainDriver {
    static Integration integration = new Integration();
    static ObjectMapper objectMapper = new ObjectMapper();

    static class NormalClass{
        public boolean flag;
    }
    public static void main(String[] args) throws IOException{

        NormalClass obj = new NormalClass();
        System.out.println(obj.flag);
        //driver();
        /*List<Integer[]> finalSlots = new ArrayList<>();

        float f = 123.45678f;
        System.out.println("float " + f );

        finalSlots.add( new Integer[]{5,6} );

        Integer[] existing = new Integer[]{5,6};
        if(finalSlots.contains( existing )){
            System.out.println("Passing");
        }
        */

        //BigDecimal bg = new BigDecimal("124567890.0987654321");
        //writeCustomConfig();
        //System.out.println("BigDecimal to Double = " + bg.floatValue());
    }

    public static void givenJsonHasUnknownValuesButJacksonIsIgnoringUnknowns_whenDeserializing_thenCorrect()
            throws JsonParseException, JsonMappingException, IOException {

        String jsonAsString =
                "{\"stringValue\":\"a\", \"intValue\":1, \"booleanValue\":true, \"stringValue2\":\"something\"}";

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MyDto readValue = mapper.readValue(jsonAsString, MyDto.class);

        System.out.println(readValue);
    }

    static class MyDto{
        public String stringValue;
        public int intValue;
        public boolean booleanValue;
    }

    private static void driver() throws IOException {
        givenJsonHasUnknownValuesButJacksonIsIgnoringUnknowns_whenDeserializing_thenCorrect();

        /*
        MainDriver md = new MainDriver();
        // Read a json from a file, set it to integration
        JsonNode jsonNode = objectMapper.readValue(new File("customconfig.json"), JsonNode.class);

        integration.config = Optional.of(jsonNode);
        ServiceInterface service = new MandrillService();
        md.addCustomConfig(service);

        System.out.println(service.toString());
        // call addCustomConfig to update customConfig in service

        // convert

         */
    }

    private static void writeCustomConfig() throws IOException {
        ServiceInterface service = new MandrillService();

        MandrillCustomConfig mdConfig = new MandrillCustomConfig();
        mdConfig.aFloat = 12.3f;
        mdConfig.baseUrl ="http://localhost";
        mdConfig.portNumber = 1008;
        Class<? extends CustomConfig> configClass = service.getCustomConfigClass();
        //JsonNode jsonNode = integration.customConfiguration.get();
        //CustomConfiguration config =  DefaultObjectMapper.JSON.convertValue(jsonNode, configClass);
        //service.setCustomConfiguration(config);
        objectMapper.writeValue(new File("customconfig.json"), mdConfig);
    }
    private void addCustomConfig(ServiceInterface service) {
        if (integration.config.isPresent()) {
            Class<? extends CustomConfig> configClass = service.getCustomConfigClass();
            JsonNode jsonNode = integration.config.get();
            CustomConfig config = objectMapper.convertValue(jsonNode, configClass);
            service.setCustomConfiguration(config);
        }
    }
}
