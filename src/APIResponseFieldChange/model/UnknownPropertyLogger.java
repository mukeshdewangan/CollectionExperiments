package APIResponseFieldChange.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public interface UnknownPropertyLogger {
    @JsonAnySetter
    default void ignored(String name, Object value) {
        System.out.println("Unknown " + name + " : " + value);
    }
}