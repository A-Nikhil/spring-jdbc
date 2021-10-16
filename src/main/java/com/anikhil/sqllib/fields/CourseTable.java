package com.anikhil.sqllib.fields;

import com.anikhil.sqllib.datatype.SQLDataType;

public enum CourseTable {
	COURSE_ID(new Column("course_id", SQLDataType.STRING, false));

	private final Column column;

	CourseTable(Column column) {
		this.column = column;
	}

	public Column getColumn() {
		return column;
	}
}
