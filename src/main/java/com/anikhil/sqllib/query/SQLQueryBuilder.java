package com.anikhil.sqllib.query;

import com.anikhil.sqllib.datatype.SQLDataType;
import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.DuplicateEntryException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
TODO : Validating Column Order in values
*  My thoughts
Create a variable column[] and set it when insert is called
Make sure to throw an error when values is called before insert
 */

/*
TODO : Validating query calls
 */
public class SQLQueryBuilder<T extends Table> {

    private final SQLQuery sqlQuery;
    private final StringBuilder queryBuilder;
    private final T tableEntity;
    private final String tableName;

    public SQLQueryBuilder(T tableEntity) {
        this.sqlQuery = new SQLQuery();
        this.queryBuilder = new StringBuilder();
        this.tableEntity = tableEntity;
        this.tableName = tableEntity.getTableName();
    }

    public Table getTableEntity() {
        return this.tableEntity;
    }

    public SQLQuery build() {
        this.sqlQuery.setQuery(this.queryBuilder.toString());
        this.sqlQuery.setComplete(true);
        return this.sqlQuery;
    }

    public SQLQueryBuilder<T> select(Column... columns) throws ColumnNotFoundException, DuplicateEntryException {
        this.queryBuilder.append("SELECT ");
        performColumnValidations(columns);
        for (Column column : columns) {
            this.queryBuilder
                    .append(column.getColumnName())
                    .append(", ");
        }
        this.queryBuilder.deleteCharAt(this.queryBuilder.length() - 2);
        return this;
    }

    public SQLQueryBuilder<T> insert(Column... columns) {
        this.queryBuilder.append("INSERT INTO ")
                .append(tableName)
                .append("(");
        for (Column column : columns) {
            this.queryBuilder
                    .append(column.getColumnName())
                    .append(", ");
        }
        this.queryBuilder.delete(this.queryBuilder.length() - 2, this.queryBuilder.length())
                .append(")");
        return this;
    }

    public SQLQueryBuilder<T> delete() {
        return this;
    }

    public SQLQueryBuilder<T> update() {
        return this;
    }

    public SQLQueryBuilder<T> from(String tableName) {
        this.queryBuilder
                .append("FROM ")
                .append(tableName)
                .append(" ");
        return this;
    }

    public SQLQueryBuilder<T> set() {
        return this;
    }

    public SQLQueryBuilder<T> values(Map<Column, Object> paramMap)
            throws ColumnNotFoundException, DuplicateEntryException, WrongDataTypeException {
        this.performColumnValidations(paramMap);
        this.queryBuilder.append(" VALUES(");
        Column column;
        Object data;
        for (Map.Entry<Column, Object> params : paramMap.entrySet()) {
            column = params.getKey();
            data = params.getValue();
            this.queryBuilder.append(getFormattedValueForData(data, column.getDataType()))
                    .append(", ");
        }
        this.queryBuilder.delete(this.queryBuilder.length() - 2, this.queryBuilder.length())
                .append(")");

        return this;
    }

    private String getFormattedValueForData(Object data, SQLDataType dataType) throws WrongDataTypeException {
        if (!dataType.getValidator().isAcceptable(data)) {
            throw new WrongDataTypeException();
        }
        switch (dataType) {
            case INTEGER:
            case DECIMAL:
                return String.format("%s", data);
            case STRING:
                return String.format("'%s'", data);
            case DATE:
            default:
        }
        return null;
    }

    private void performColumnValidations(Map<Column, Object> columnDataMap)
            throws ColumnNotFoundException, DuplicateEntryException {
        this.performColumnValidations(columnDataMap.keySet().toArray(new Column[0]));
    }

    private void performColumnValidations(Column[] columnsToBeChecked)
            throws ColumnNotFoundException, DuplicateEntryException {
        final Set<String> columnNames = this.getTableEntity().getAllColumnNames();
        final Map<String, Boolean> duplicationMap = new HashMap<>();

        // create a map of column:false,
        // where false implies no occurrence
        columnNames.forEach(column -> duplicationMap.put(column, false));

        for (Column column : columnsToBeChecked) {
            if (!columnNames.contains(column.getColumnName())) {
                throw new ColumnNotFoundException(column.getColumnName(), tableName);
            } else {
                if (Boolean.TRUE.equals(duplicationMap.get(column.getColumnName()))) {
                    throw new DuplicateEntryException("Duplicate column found : " + column.getColumnName());
                } else {
                    duplicationMap.put(column.getColumnName(), true);
                }
            }
        }
    }
}
