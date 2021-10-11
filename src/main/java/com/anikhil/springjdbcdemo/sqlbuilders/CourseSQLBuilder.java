package com.anikhil.springjdbcdemo.sqlbuilders;

import com.anikhil.springjdbcdemo.sqlfields.SQLField;
import com.anikhil.springjdbcdemo.utils.TableMapping;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CourseSQLBuilder extends SQLBuilder {

	private static final TableMapping courseTableMapping = TableMapping.COURSE;

	/**
	 * Builds select query for all fields
	 * @return String select query
	 */
	public String buildSelectQueryForAll() {
		return super.buildSelectQuery(courseTableMapping);
	}

	/**
	 * Builds select query for all fields
	 * @return String select query
	 */
	public String buildInsertQueryForAll() {
		return super.buildInsertQuery(courseTableMapping);
	}

	/**
	 * Builds select query for all fields
	 * @return String create query
	 */
	public String buildCreateQueryForAll() {
		return super.buildCreateQuery(courseTableMapping);
	}

	/**
	 * Builds select query for all fields
	 * @return String delete query
	 */
	public String buildDeleteQueryForAll() {
		return super.buildDeleteQuery(courseTableMapping);
	}

	public String buildUpdateQueryFor(Map<SQLField, Object> updateParamsMap) {
		return null;
	}
}
