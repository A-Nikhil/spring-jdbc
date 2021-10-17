package com.anikhil.sqllib.query;

import com.anikhil.sqllib.fields.Column;

public class SQLQueryBuilder {

    private final SQLQuery sqlQuery;
    private final StringBuilder queryBuilder;

    public SQLQueryBuilder() {
        this.sqlQuery = new SQLQuery();
        this.queryBuilder = new StringBuilder();
    }

    public SQLQuery build() {
        this.sqlQuery.setQuery(this.queryBuilder.toString());
        this.sqlQuery.setComplete(true);
        return this.sqlQuery;
    }

    public SQLQueryBuilder select(Column... columns) {
		this.queryBuilder.append("SELECT ");
		for (Column column : columns) {
			this.queryBuilder
					.append(column.getColumnName())
					.append(", ");
		}
		this.queryBuilder.deleteCharAt(this.queryBuilder.length() - 2);
        return this;
    }

    public SQLQueryBuilder insert() {
        return this;
    }

    public SQLQueryBuilder delete() {
        return this;
    }

    public SQLQueryBuilder update() {
        return this;
    }

    public SQLQueryBuilder from(String tableName) {
        this.queryBuilder
				.append("FROM ")
				.append(tableName)
				.append(" ");
		return this;
    }

    public SQLQueryBuilder set() {
        return this;
    }

    public SQLQueryBuilder values() {
        return this;
    }
}
