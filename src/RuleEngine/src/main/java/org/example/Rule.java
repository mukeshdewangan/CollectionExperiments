package org.example;

import java.util.List;

public class Rule {
    private Condition condition;
    private List<Rule> innerRules;
    private Operator operator;

    public Rule(){

    }
    // Constructor for plain rule with operator and operand
    public Rule(Condition condition, Operator operator) {
        this.condition = condition;
        this.operator = operator;
    }

    public Rule(Condition condition){
        this.condition = condition;
    }

    // Constructor for inner rule with inner rules and operator
    public Rule(List<Rule> innerRules, Operator operator) {
        this.innerRules = innerRules;
        this.operator = operator;
    }
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public Operator getOperator() {
        return operator;
    }

    public List<Rule> getInnerRules() {
        return innerRules;
    }

    public void setInnerRules(List<Rule> innerRules) {
        this.innerRules = innerRules;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
