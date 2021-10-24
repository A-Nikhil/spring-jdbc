package com.anikhil.sqllib.query;

public class SQLQuery {

    /**
     * Can only be accessed by {@link com.anikhil.sqllib.query.SQLQueryBuilder}
     */
    protected String query;
    private boolean isComplete;

    protected SQLQuery() {
        this.query = "";
        this.isComplete = false;
    }

    protected SQLQuery(String query) {
        this.query = query;
        this.isComplete = false;
    }

    public String getQuery() {
        return query;
    }

    protected void setQuery(String query) {
        this.query = query;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
