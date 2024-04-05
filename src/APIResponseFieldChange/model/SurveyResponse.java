package APIResponseFieldChange.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class SurveyResponse
        //implements UnknownPropertyLogger
{
    public String id;
    public String email;
    public String type;
    public Traversal traversal;
    public Integer number;
    public Choice choice;

    //public Map<String, Object> hidden = new HashMap<>();
    public Map<String, Object> customMap = new HashMap<>();

    //@JsonAnySetter
    public void setDynamicProperty(String name, String value) {
        customMap.put("custom_" + name, value);
    }

    @Override
    public String toString(){
        return " id -> "+ id + "\n" +
                " email -> " + email + "\n" +
                " type -> " + type + "\n" +
                " traversal -> " + traversal.toString() + "\n" +
                " number -> " + number + "\n" +
                " choice.id -> "+ choice.choiceId + "\n" +
                " choice.row_id -> " + choice.rowId;
    }
}

class Choice{
    @JsonProperty("choice_id")
    public String choiceId;
    @JsonProperty("row_id")
    public String rowId;
        }
enum Traversal{
    PREORDER,
    INORDER,
    POSTORDER
}