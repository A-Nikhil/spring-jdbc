package com.anikhil.springjdbcdemo.sqltables;

import com.anikhil.sqllib.datatype.SQLDataType;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseTable implements Table {

	private static final String tableName = "course";
	public final Column courseId;
	public final Column name;
	public final Column description;
	public final Column link;

	/**
	 * Table classes should not be initialized
	 */
	public CourseTable() {
		this.courseId = new Column("course_id", SQLDataType.INTEGER, false);
		this.name = new Column("name", SQLDataType.STRING, false);
		this.description = new Column("description", SQLDataType.STRING, false);
		this.link = new Column("link", SQLDataType.STRING, false);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(tableName).append(": {\n");
		for (Column column : this.getAllColumns()) {
			builder.append("\t")
					.append(column.getColumnName()).append(": ").append(column.getDataType().toString()).append("\n");
		}
		return builder.append("}").toString();
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public Set<Column> getAllColumns() {
		return new HashSet<>(Arrays.asList(this.courseId, this.name, this.description, this.link));
	}

	@Override
	public Set<String> getAllColumnNames() {
		return new HashSet<>(Arrays.asList("course_id", "name", "description", "link"));
	}

	@Override
	public Set<Column> getPrimaryKeyColumns() {
		return new HashSet<>(List.of(this.courseId));
	}
}
