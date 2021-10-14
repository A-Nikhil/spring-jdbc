package com.anikhil.springjdbcdemo.sqlfields;

import com.anikhil.springjdbcdemo.sqldatatypes.SQLDataType;
import com.anikhil.springjdbcdemo.utils.ColumnMapping;

import java.util.ArrayList;
import java.util.List;

public enum CourseSQLFields implements SQLField {
	COURSE_ID(new ColumnMapping("course_id", true, true, SQLDataType.INTEGER)),
	TITLE(new ColumnMapping("title", false, false, SQLDataType.STRING)),
	DESCRIPTION(new ColumnMapping("description", false, false, SQLDataType.STRING)),
	LINK(new ColumnMapping("link", false, false, SQLDataType.STRING));

	private final ColumnMapping columnMapping;

	CourseSQLFields(ColumnMapping columnMapping) {
		this.columnMapping = columnMapping;
	}

	public ColumnMapping getColumnMapping() {
		return columnMapping;
	}

	@Override
	public List<SQLField> getPrimaryKeys() {
		return new ArrayList<>(List.of(COURSE_ID));
	}
}
