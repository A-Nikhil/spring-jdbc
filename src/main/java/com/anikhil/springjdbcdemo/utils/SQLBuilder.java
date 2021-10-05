package com.anikhil.springjdbcdemo.utils;

import com.anikhil.springjdbcdemo.entityMappings.TableMapping;
import org.springframework.stereotype.Service;

/**
 * Generates query for basic SQL operations
 * These are complete operations that can happen on any table
 */
@Service
public class SQLBuilder {
    protected static final String SELECT = "SELECT ";
    protected static final String INSERT = "INSERT ";
    protected static final String FROM = " FROM ";
    protected static final String VALUES = " VALUES( ";
    protected static final String EQUALS = " = ";
    protected static final String WHERE = " WHERE ";
    protected static final String INTO = " INTO ";

    public String buildSelectQuery(TableMapping tableMapping) {
        return select(tableMapping.getSqlFields()) +
                from(tableMapping.getTableName());
    }


    public String buildInsertQuery(TableMapping tableMapping) {
        return null;
    }

    public String buildUpdateQuery(TableMapping tableMapping) {
        return null;
    }

    public String buildCreateQuery(TableMapping tableMapping) {
        return null;
    }

    public String buildDeleteQuery(TableMapping tableMapping) {
        return null;
    }

    public String select(SQLField... sqlFields) {
        StringBuilder selectBuilder = new StringBuilder(SELECT);
        for (SQLField sqlField : sqlFields) {
            selectBuilder.append(sqlField.getDbColumn()).append(", ");
        }

        // removing the last ,
        selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length());

        return selectBuilder.toString();
    }

    public String insert(String tableName, SQLField... sqlFields) {
        StringBuilder selectBuilder = new StringBuilder(INSERT)
                .append(INTO)
                .append(tableName)
                .append("( ");
        for (SQLField sqlField : sqlFields) {
            selectBuilder.append(sqlField.getDbColumn()).append(", ");
        }

        // removing the last , and inserting ") "
        selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length() - 1)
                .append(") ");

        return selectBuilder.toString();
    }

    public String values(String tableName, SQLField... sqlFields) {
        StringBuilder selectBuilder = new StringBuilder(VALUES);
        for (SQLField sqlField : sqlFields) {
            selectBuilder.append(":").append(sqlField.getDbColumn()).append(", ");
        }

        // removing the last , and inserting ") "
        selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length() - 1)
                .append(") ");

        return selectBuilder.toString();
    }

    public String from(String tableName) {
        return FROM +
                tableName;
    }

    public String equals(SQLField sqlField) {
        return EQUALS +
                sqlField.getDbColumn();
    }

    public String where(SQLField sqlField) {
        return WHERE + sqlField;
    }

    public static void main(String[] args) {
        String selectQuery = new SQLBuilder().buildSelectQuery(TableMapping.COURSE);
        System.out.println(selectQuery);
    }
}
