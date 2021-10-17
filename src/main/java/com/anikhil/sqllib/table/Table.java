package com.anikhil.sqllib.table;

import com.anikhil.sqllib.fields.Column;

import java.util.Set;

public abstract class Table {

	public String tableName;

	public abstract String getTableName();

	public abstract Set<Column> getAllColumns();

	public abstract Set<String> getAllColumnNames();

	public abstract Set<Column> getPrimaryKeyColumns();
}
