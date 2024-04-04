package PIIDetection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ParseZuoraPiiConfig {
    public static void main(String[] args) {

        String zuoraPiiConfigFile = "ZuoraPiiColumns.json";
        try {
            parsePiiConfigFile(zuoraPiiConfigFile);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void parsePiiConfigFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TablesPiiInfo tablesPiiInfo = objectMapper.readValue(new File(fileName), TablesPiiInfo.class);
        System.out.println(tablesPiiInfo.tables.entrySet());
    }
}
