package com.anikhil.springjdbcdemo.sqlbuilders;

import com.anikhil.springjdbcdemo.sqldatatypes.SQLDataType;
import com.anikhil.springjdbcdemo.sqldatatypes.SQLValidator;
import com.anikhil.springjdbcdemo.sqlfields.CourseSQLFields;
import com.anikhil.springjdbcdemo.sqlfields.SQLField;
import com.anikhil.springjdbcdemo.utils.TableMapping;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Generates query for basic SQL operations
 * These are complete operations that can happen on any table
 */
// FIXME: 15-10-2021 Correct the SQL builders
@Service
public class SQLBuilder {

	// prevent initialization
	protected SQLBuilder() {
	}

	public String buildSelectQuery(String tableName, SQLField... sqlFields) {
		return select(sqlFields) + from(tableName);
	}

	protected String buildInsertQuery(String tableName, SQLField[] sqlFields, Map<SQLField, Object> insertParams) {
		return insert(tableName, sqlFields) +
				values(sqlFields, insertParams);
	}

	public String buildDeleteQuery(TableMapping tableMapping) {
		return null;
	}

	protected String select(SQLField... sqlFields) {
		StringBuilder selectBuilder = new StringBuilder("SELECT ");
		for (SQLField sqlField : sqlFields) {
			selectBuilder.append(sqlField.getDbColumn()).append(", ");
		}

		// removing the last ,
		selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length());

		return selectBuilder.toString();
	}

	protected String from(String tableName) {
		return " FROM " +
				tableName;
	}

	protected String insert(String tableName, SQLField... sqlFields) {
		StringBuilder selectBuilder = new StringBuilder("INSERT ")
				.append("INTO ")
				.append(tableName)
				.append("(");

		for (SQLField sqlField : sqlFields) {
			// do not insert into auto_increment fields
			if (!sqlField.getColumnMapping().isAutoGenerated()) {
				selectBuilder.append(sqlField.getDbColumn()).append(", ");
			}
		}

		// removing the last , and inserting ") "
		selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length())
				.append(") ");

		return selectBuilder.toString();
	}

	// FIXME: 15-10-2021 Correct the logic
	// TODO: 15-10-2021 Add as stated by the data type
	protected String values(SQLField[] sqlFields, Map<SQLField, Object> valueParams) {
		StringBuilder selectBuilder = new StringBuilder("VALUES(");

		for (SQLField sqlField : sqlFields) {
			Object data = valueParams.get(sqlField);
			selectBuilder.append(formattedValue(sqlField.getColumnMapping().getSqlDataType(), data))
					.append(", ");
		}

		// removing the last , and inserting ") "
		selectBuilder.delete(selectBuilder.length() - 2, selectBuilder.length())
				.append(")");

		return selectBuilder.toString();
	}

	private String formattedValue(SQLDataType sqlDataType, Object data) {
		SQLValidator validator = sqlDataType.getValidator();
		if (validator.isAcceptable(data)) {
			return getDataAccordingToType(sqlDataType, data);
		}
		return null;
	}

	private String getDataAccordingToType(SQLDataType sqlDataType, Object data) {
		switch (sqlDataType) {
			case STRING:
				return String.format("'%s'", data);
			case INTEGER:
			case DECIMAL:
				return String.format("%s", data);
			case DATE:
				break;
		}
		return "";
	}

	protected String equal(Map<SQLField, SQLField> equalParams) {
//        return " = " +
//                sqlField.getDbColumn();
		return null;
	}

	protected String where(SQLField sqlField) {
		return " WHERE" + sqlField;
	}

	public static void main(String[] args) {
		Map<SQLField, Object> paramMap = new HashMap<>();
		paramMap.put(CourseSQLFields.TITLE, "TITLE");
		paramMap.put(CourseSQLFields.DESCRIPTION, "DESCRIPTION");
		paramMap.put(CourseSQLFields.LINK, "LINK");
		paramMap.put(CourseSQLFields.RANDOM, 3);
		SQLField[] sqlFields = {CourseSQLFields.TITLE, CourseSQLFields.DESCRIPTION, CourseSQLFields.LINK, CourseSQLFields.RANDOM};
		System.out.println(new SQLBuilder().buildInsertQuery("COURSE", sqlFields, paramMap));
	}
}
