package com.anikhil.sqllib.query;

import java.util.Arrays;
import java.util.List;

public class SQLQueryOrder {

    private final SQLQueryKeyword[] notAcceptedKeywords;
    private final SQLQueryKeyword[] predecessor;
    private final String keywordName;

    public SQLQueryOrder(String keywordName, SQLQueryKeyword[] notAcceptedKeywords, SQLQueryKeyword[] predecessor) {
        this.notAcceptedKeywords = notAcceptedKeywords;
        this.predecessor = predecessor;
        this.keywordName = keywordName;
    }

    public List<SQLQueryKeyword> getNotAcceptedKeywords() {
        return this.notAcceptedKeywords == null ? null : Arrays.asList(notAcceptedKeywords);
    }

    public List<SQLQueryKeyword> getPredecessor() {
        return this.predecessor == null ? null : Arrays.asList(predecessor);
    }

    public String getKeywordName() {
        return keywordName;
    }
}
