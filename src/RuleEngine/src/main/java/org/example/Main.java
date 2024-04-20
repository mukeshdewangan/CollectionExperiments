package org.example;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        // Creating a rule
        Condition condition1 = new Condition("field1", Operator.GREATER_THAN, "10");
        Condition condition2 = new Condition("field2", Operator.CONTAINS, "abc");

        Rule rule1 = new Rule(condition1);
        Rule rule2 = new Rule(condition2);
//      List<Condition> conditions = new ArrayList<>();
//
//      conditions.add(condition1);
//      conditions.add(condition2);
        List<Rule> rules=  new ArrayList<Rule>();
        rules.add(rule1);
        rules.add(rule2);
        Rule innerRule = new Rule(rules, Operator.AND);
//      Rule rule = new Rule(innerRule, Operator.OR, condition2);
        //Rule rule = new Rule(conditions, Operator.AND);
        // Saving the rule
        RuleStorage.saveRule(innerRule, "rule.json");

        // Loading the rule
        Rule loadedRule = RuleStorage.loadRule("rule.json");
        System.out.println(loadedRule);
    }


}