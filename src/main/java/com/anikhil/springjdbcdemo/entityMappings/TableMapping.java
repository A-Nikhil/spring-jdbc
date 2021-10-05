package com.anikhil.springjdbcdemo.entityMappings;

import com.anikhil.springjdbcdemo.utils.SQLField;

import java.util.ArrayList;
import java.util.List;

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
}
