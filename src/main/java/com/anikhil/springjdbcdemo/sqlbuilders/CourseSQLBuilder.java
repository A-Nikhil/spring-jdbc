package com.anikhil.springjdbcdemo.sqlbuilders;

import com.anikhil.springjdbcdemo.sqlfields.SQLField;
import com.anikhil.springjdbcdemo.utils.TableMapping;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CourseSQLBuilder extends SQLBuilder {

	private static final TableMapping courseTableMapping = TableMapping.COURSE;

	public String buildInsertQuery(SQLField[] sqlFields, Map<SQLField, Object> insertParams) {
		return super.buildInsertQuery(courseTableMapping.getTableName(),
				sqlFields,
				insertParams);
	}

	/**
	 * Builds select query for all fields
	 *
	 * @return String create query
	 */
	public String buildCreateQueryForAll() {
		return super.buildCreateQuery(courseTableMapping);
	}

	/**
	 * Builds select query for all fields
	 *
	 * @return String delete query
	 */
	public String buildDeleteQueryForAll() {
		return super.buildDeleteQuery(courseTableMapping);
	}

	public String buildUpdateQueryFor(Map<SQLField, Object> updateParamsMap) {
		StringBuilder updateSQLBuilder = new StringBuilder("UPDATE ")
				.append(courseTableMapping.getTableName())
				.append(" set ");
		for (Map.Entry<SQLField, Object> updateParam : updateParamsMap.entrySet()) {
			// TODO: 10/12/21 Create Field Check
			updateSQLBuilder.append(updateParam.getKey().getDbColumn())
					.append(" = ")
					.append(updateParam.getValue());
		}
		return updateSQLBuilder.toString();
	}
}
