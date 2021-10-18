package com.anikhil.springjdbcdemo.sqltables;

import org.springframework.stereotype.Component;

import com.anikhil.sqllib.datatype.SQLDataType;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CourseTable extends Table {

	public final Column courseId;
	public final Column title;
	public final Column description;
	public final Column link;

	public CourseTable() {
		this.tableName = "course";
		this.courseId = new Column("course_id", SQLDataType.INTEGER, false);
		this.title = new Column("title", SQLDataType.STRING, false);
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
		return new HashSet<>(Arrays.asList(this.courseId, this.title, this.description, this.link));
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
