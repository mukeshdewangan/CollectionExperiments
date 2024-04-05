package Reflection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        ModelClass m = new ModelClass();
        String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
        m.state = new ObjectMapper().readTree(jsonString);

        /*WhereClause whereClause = new WhereClause();
        whereClause.owner = "1k56c2c4xlti6";
        whereClause.schema_name ="hubspot_test";
        whereClause.service ="hubspot";
        whereClause.id = "moonbeam_select";
        m.where_clause = whereClause;
         */

        Map<String,String> whereClause= new HashMap<>();
        //whereClause.put("owner", "1k56c2c4xlti6");
        //whereClause.put("schema_name", "hubspot_test");
        //whereClause.put("service", "hubspot");
        whereClause.put("id", "moonbeam_select");

        m.where_clause = whereClause;
        System.out.println(m.toString());

        String s = getFileContent();
        ModelClass modelClass =  ModelClass.parseJson(s);


        //Class modelClass = m.getClass();
        //Field[] fields = modelClass.getDeclaredFields();
        System.out.println(modelClass.where_clause);

    }
    public static String getFileContent() {
        Path p = Paths.get("src/Reflection/aws_lambda.json");

        String content = "";
        try {
            content = new String(Files.readAllBytes(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(content);
        return content;
    }
}
