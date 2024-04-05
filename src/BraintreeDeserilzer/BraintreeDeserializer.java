package BraintreeDeserilzer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class BraintreeDeserializer {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        JsonNode node1 = objectMapper.readValue(new File("ship_to_primary_address.json"), JsonNode.class);
        ShipTo state1 = objectMapper.convertValue(node1, ShipTo.class);
        System.out.println(state1);

        JsonNode node2 = objectMapper.readValue(new File("ship_to_2_address.json"), JsonNode.class);
        //ShipTo state2 = objectMapper.convertValue(node2, ShipTo.class);

        Instant instant = Instant.parse("2015-08-13T22:50:45Z");
        System.out.println(instant.toEpochMilli());

    }
}
