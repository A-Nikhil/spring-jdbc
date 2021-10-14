package com.anikhil.springjdbcdemo.sqlfields;

import com.anikhil.springjdbcdemo.utils.ColumnMapping;
import com.anikhil.springjdbcdemo.validators.StringSQLValidator;

import java.util.ArrayList;
import java.util.List;

public enum CourseSQLFields implements SQLField {
    COURSE_ID(new ColumnMapping("course_id", true, true)),
    TITLE(new ColumnMapping("title", false, false, new StringSQLValidator())),
    DESCRIPTION(new ColumnMapping("description", false, false)),
    LINK(new ColumnMapping("link", false, false));

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
