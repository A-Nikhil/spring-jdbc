package com.anikhil.sqllib.query;

public enum SQLQueryKeyword {
    SELECT,
    INSERT,
    UPDATE,
    DELETE,
    FROM,
    VALUES,
    SET,
    WHERE;

    public static SQLQueryKeyword getKeyword(String keywordString) {
        keywordString = keywordString.toLowerCase();
        switch (keywordString) {
            case "select":
                return SELECT;
            case "insert":
                return INSERT;
            case "delete":
                return DELETE;
            case "update":
                return UPDATE;
            case "from":
                return FROM;
            case "set":
                return SET;
            case "values":
                return VALUES;
            default:
                return WHERE;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
