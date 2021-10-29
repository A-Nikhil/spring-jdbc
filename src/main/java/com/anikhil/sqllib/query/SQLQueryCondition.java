package com.anikhil.sqllib.query;

public class SQLQueryCondition {

    private final String conditionString;

    protected SQLQueryCondition(String conditionString) {
        this.conditionString = conditionString;
    }

    @Override
    public String toString() {
        return conditionString;
    }
}
