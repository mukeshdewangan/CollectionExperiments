package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

// Serialize/Deserialize using JSON
public class RuleStorage {
    public static void saveRule(Rule rule, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), rule);
    }

    public static Rule loadRule(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Rule.class);
    }
}
