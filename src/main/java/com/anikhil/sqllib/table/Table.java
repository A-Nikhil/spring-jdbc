package com.anikhil.sqllib.table;

import com.anikhil.sqllib.fields.Column;

import java.util.Set;

public interface Table {
	String getTableName();
	Set<Column> getAllColumns();
	Set<String> getAllColumnNames();
	Set<Column> getPrimaryKeyColumns();
}
