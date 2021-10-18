package com.anikhil.sqllib.fields;

import com.anikhil.sqllib.datatype.SQLDataType;

public class Column {
    /**
     * Name of the column as defined in the database
     */
    private final String columnName;

    /**
     * SQL Data Type
     */
    private final SQLDataType dataType;

    /**
     * Primary Key Identifier
     */
    private final boolean primaryKey;

    /**
     * Constructor, can only be called from the subclass
     *
     * @param columnName Name of the column
     * @param dataType   Data Type of the column
     * @param primaryKey Is the column primary key
     */
    public Column(String columnName, SQLDataType dataType, boolean primaryKey) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.primaryKey = primaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public SQLDataType getDataType() {
        return dataType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }
}
