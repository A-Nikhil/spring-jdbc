package com.anikhil.sqllib.query;

public class SQLQueryConditionBuilder {

    private final StringBuilder conditionBuilder;

    public SQLQueryConditionBuilder() {
        this.conditionBuilder = new StringBuilder();
    }

    // TODO: 10/29/21 Equals

    // TODO: 10/29/21 Greater than

    // TODO: 10/29/21 Less than

    // TODO: 10/29/21 LIKE

    // TODO: 10/29/21 in

    // TODO: 10/29/21 Bracketizer

    public SQLQueryCondition build() {
        return new SQLQueryCondition(this.conditionBuilder.toString());
    }
}
