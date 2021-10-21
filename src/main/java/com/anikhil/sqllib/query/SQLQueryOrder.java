package com.anikhil.sqllib.query;

import java.util.Arrays;
import java.util.List;

public class SQLQueryOrder {

    private final SQLQueryKeyword[] notAcceptedKeywords;
    private final SQLQueryKeyword[] predecessor;

    public SQLQueryOrder(SQLQueryKeyword[] notAcceptedKeywords, SQLQueryKeyword[] predecessor) {
        this.notAcceptedKeywords = notAcceptedKeywords;
        this.predecessor = predecessor;
    }

    public List<SQLQueryKeyword> getNotAcceptedKeywords() {
        return Arrays.asList(notAcceptedKeywords);
    }

    public List<SQLQueryKeyword> getPredecessor() {
        return Arrays.asList(predecessor);
    }
}
