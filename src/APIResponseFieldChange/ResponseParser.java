package APIResponseFieldChange;

import APIResponseFieldChange.model.Card;
import APIResponseFieldChange.model.SurveyResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class ResponseParser {
    private ObjectMapper objectMapper;

    public void parseResponseConfigFile(String fileName) throws IOException {
        InetAddress IP = InetAddress.getLocalHost();
        System.out.println("IP of my system is := "+IP.getHostAddress());

        SurveyResponse response = null;
        String rawData = "{\"type\":\"notification_event\",\"app_id\":\"mq44cv6t\",\"data\":{\"type\":\"notification_event_data\",\"item\":{\"type\":\"user\",\"id\":\"623505d032921d41b068cb52\",\"user_id\":\"33b2976b-0d72-4073-b9e8-46ba65f8b217\",\"email\":\"\"}},\"links\":{},\"id\":\"notif_33e2dd41-0506-4a67-ba21-9c9fe79c0bdd\",\"topic\":\"user.deleted\",\"delivery_status\":\"retry\",\"delivery_attempts\":2,\"delivered_at\":0,\"first_sent_at\":1647642089,\"created_at\":1647642068,\"self\":null}";

        JsonNode data = objectMapper.readTree(rawData);
        //String rawData = null; // Setting it to null to save space

        boolean something =true;
        if(something){
            for(int i =0;i<4;i++){
                System.out.println(i);
                if(i==2)
                    break;
            }
            if(true){
                System.out.println(" in line 23");
            }
        }
        else
        {
            System.out.println(" else line 29 ");
        }
        System.out.println(" in line 31 ");

        try {
            response = objectMapper.readValue(new File(fileName), SurveyResponse.class);
        }
        catch (UnrecognizedPropertyException e ){
            System.out.println(e.getMessage());
        }

        System.out.println(response);
    }

    ResponseParser(){
        objectMapper = new ObjectMapper();

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        // Necessary for annotated views to remain private
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(JsonViews.Public.class));
    }

    public static void main(String[] args){
//        ResponseParser parser = new ResponseParser();
//
//        String responseFilePath = "src/survey_response.json";
//        try {
//            parser.parseResponseConfigFile(responseFilePath);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
        //cardParser();

        testContinue();
    }

    public static void testContinue(){
        int[] arr = {1,2,3,4,5,6,7,8,9};
        for (int i : arr) {
            if(i %2 ==0 ){
                if(i%4 ==0){
                    System.out.println("this is divisible by both 2 and 4");
                    continue;
                }
            }
            // multiply number by 10;
            System.out.println("multiply number by 10 is "+ i*10);
        }
    }
    public static void cardParser(){
        ResponseParser parser = new ResponseParser();

        String responseFilePath1 = "src/card_response1.json";
        String responseFilePath2 = "src/card_response2.json";
        try {
            parser.parseCardResponse(responseFilePath1);
            parser.parseCardResponse(responseFilePath2);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void parseCardResponse(String filePath) throws IOException {
        Card cardResponse = null;
        Set<String> newSet = new HashSet<String>();
        newSet.add("Invoice");
        newSet.add("Position");
        String str = objectMapper.writeValueAsString(newSet);
        System.out.println(str);

        str = "[]";
        Set<String> convertedString = objectMapper.readValue(str, HashSet.class);


        cardResponse = objectMapper.readValue(new File(filePath), Card.class);
        if (cardResponse.threeDSecure != null) {
            try {
                Card.SecureUsage secureUsage =
                        objectMapper.convertValue(cardResponse.threeDSecure, Card.SecureUsage.class);
                cardResponse.secureUsage = secureUsage;
                cardResponse.three_d_secure = cardResponse.threeDSecure.toString();
                System.out.println(secureUsage);
            } catch (IllegalArgumentException e) {
                if (cardResponse.threeDSecure instanceof String) {
                    String optional = (String) cardResponse.threeDSecure;
                    cardResponse.three_d_secure = optional;
                    System.out.println(cardResponse);
                }
            }
        }
        System.out.println(cardResponse);
    }
}
