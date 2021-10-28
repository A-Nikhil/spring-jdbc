package com.anikhil.sqllib.query;

public class SQLQueryCondition {

    private final String conditionString;

    protected SQLQueryCondition(String conditionString) {
        this.conditionString = conditionString;
    }

    public String getConditionString() {
        return conditionString;
    }
}
