package com.anikhil.springjdbcdemo.utils;

import com.anikhil.springjdbcdemo.sqldatatypes.SQLDataType;
import com.anikhil.springjdbcdemo.sqlfields.CourseSQLFields;
import com.anikhil.springjdbcdemo.sqlfields.SQLField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This contains
 */
public enum TableMapping {

    COURSE("course", CourseSQLFields.values());

    private final String tableName;
    private final SQLField[] sqlFields;

    TableMapping(String tableName, SQLField[] sqlFields) {
        this.tableName = tableName;
        this.sqlFields = sqlFields;
    }

    public String getTableName() {
        return tableName;
    }

    public SQLField[] getSqlFields() {
        return sqlFields;
    }

    public List<SQLField> getPrimaryKeys() {
        List<SQLField> primaryKeyList = new ArrayList<>();
        for (SQLField field: this.sqlFields) {
            if (field.getColumnMapping().isPrimaryKey()) {
                primaryKeyList.add(field);
            }
        }
        return primaryKeyList;
    }

    public Map<String, SQLDataType> getFieldsWithDataTypes() {
        Map<String, SQLDataType> fieldSQLDataTypeMap = new HashMap<>();
        for (SQLField sqlField : this.sqlFields) {
            fieldSQLDataTypeMap.put(sqlField.getDbColumn(), sqlField.getColumnMapping().getSqlDataType());
        }
        return fieldSQLDataTypeMap;
    }

    public Set<String> getAllFieldNames() {
        Set<String> fieldNames = new HashSet<>();
        for (SQLField sqlField : this.sqlFields) {
            fieldNames.add(sqlField.getDbColumn());
        }
        return fieldNames;
    }
}
