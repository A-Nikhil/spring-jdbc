package com.anikhil.sqllib.query;

public class SQLQuery {

    /**
     * Can only be accessed by {@link com.anikhil.sqllib.query.SQLQueryBuilder}
     */
    protected final String query;
    private boolean isComplete;

    protected SQLQuery() {
        this.query = "";
        this.isComplete = false;
    }

    protected SQLQuery(String query) {
        this.query = query;
        this.isComplete = false;
    }

    protected SQLQuery(String query, boolean isComplete) {
        this.query = query;
        this.isComplete = isComplete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return this.query;
    }
}
