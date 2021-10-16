package com.anikhil.sqllib.query;

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

	public SQLQueryBuilder select() {
		return null;
	}

	public SQLQueryBuilder insert() {
		return null;
	}

	public SQLQueryBuilder delete() {
		return null;
	}

	public SQLQueryBuilder update() {
		return null;
	}

	public SQLQueryBuilder set() {
		return null;
	}

	public SQLQueryBuilder values() {
		return null;
	}
}
