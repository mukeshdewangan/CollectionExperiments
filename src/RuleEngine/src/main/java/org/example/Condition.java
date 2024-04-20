package org.example;

public class Condition {
    public String field;
    public Operator operator;
    public String value;

    public Condition(String field, Operator op, String value){
        this.field = field;
        this.operator = op;
        this.value = value;
    }
}
