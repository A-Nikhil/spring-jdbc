package com.anikhil.springjdbcdemo.utils;

import java.util.List;

public interface SQLField {
    ColumnMapping getColumnMapping();
    List<SQLField> getPrimaryKeys();

    default String getDbColumn() {
        return getColumnMapping().getColumnName();
    }
}
