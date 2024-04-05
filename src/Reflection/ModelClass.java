package Reflection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ModelClass {
    //public WhereClause where_clause;
    public Map<String, String> where_clause;
    public boolean resync;
    public JsonNode state;
    public Map<String, List<String>> schemas_and_tables;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE integrations SET state=\'" + state + "\'");
        //stringBuilder.append(where_clause.toString());
        stringBuilder.append(getWhereClause()).append(" ;");
        return stringBuilder.toString();
    }

    public static ModelClass parseJson(String input) {
        ObjectMapper DefaultObjectMapper = new ObjectMapper();
        try {
            return DefaultObjectMapper.readValue(input, ModelClass.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse state modification json", e);
        }
    }

    private String getWhereClause(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" WHERE ");
        boolean andFlag = false;
        for (Map.Entry<String,String> entry : where_clause.entrySet()) {
            if(andFlag) stringBuilder.append(" AND ");
            stringBuilder.append( entry.getKey() + "=\'" + entry.getValue() + "\'");
            andFlag = true;
        }
        return stringBuilder.toString();
    }
}

class WhereClause {
    public String owner;
    public String service;
    public String schema_name;
    public String id;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" WHERE ");
        boolean andFlag = false;

        try {
            andFlag = appendFieldValue("owner", stringBuilder, andFlag);
            andFlag = appendFieldValue("schema_name", stringBuilder, andFlag);
            andFlag = appendFieldValue("service", stringBuilder, andFlag);
            appendFieldValue("id", stringBuilder, andFlag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*
        if(owner !=null) {
            appendAND(stringBuilder,andFlag);
            stringBuilder.append("owner =\'" + owner + "\'");
            andFlag = true;
        }
        if(schema_name !=null) {
            appendAND(stringBuilder,andFlag);
            stringBuilder.append("schema_name =\'" + schema_name + "\'");
            andFlag = true;
        }
        if(service !=null) {
            appendAND(stringBuilder,andFlag);
            stringBuilder.append("service =\'" + service + "\'");
            andFlag = true;
        }
        if(id !=null){
            appendAND(stringBuilder,andFlag);
            stringBuilder.append("id=\'"+ id + "\'");
            andFlag = true;
        }*/
        return stringBuilder.toString();
    }
    
    private boolean appendFieldValue(String fieldName, StringBuilder stringBuilder, boolean andFlag)
            throws NoSuchFieldException {
        Class modelClass = WhereClause.class;
        try {
            Field field = modelClass.getField(fieldName);
            Object value = field.get(this);
            if (value != null) {
                if(andFlag) stringBuilder.append(" AND ");
                stringBuilder.append(fieldName + "=\'" + value + "\'");
                andFlag = true;
            }
            return andFlag;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}

