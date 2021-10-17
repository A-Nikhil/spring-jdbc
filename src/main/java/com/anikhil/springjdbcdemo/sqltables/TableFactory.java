package com.anikhil.springjdbcdemo.sqltables;

import com.anikhil.sqllib.table.Table;

public class TableFactory {

	private static final Table courseTable = new CourseTable();

	public static <T extends Table> T getTableByName(String tableName, Class<T> type) {
		if ("course".equalsIgnoreCase(tableName)) {
			return type.cast(courseTable);
		}
		// FIXME: 17-10-2021 FIx this null with else to make sure this factory always returns a Table
		return null;
	}

	/**
	 * Prevent instantiation
	 */
	private TableFactory () {}
}
