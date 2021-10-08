package com.anikhil.springjdbcdemo.sqlfields;

import com.anikhil.springjdbcdemo.utils.ColumnMapping;

import java.util.List;

public interface SQLField {
    ColumnMapping getColumnMapping();
    List<SQLField> getPrimaryKeys();

    default String getDbColumn() {
        return getColumnMapping().getColumnName();
    }
}
