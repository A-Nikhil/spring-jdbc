package com.anikhil.sqllib.query;

import com.anikhil.sqllib.datatype.SQLDataType;
import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.DuplicateEntryException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;

import java.util.Map;
import java.util.Set;

// TODO: 10/18/21 Complete values()
// TODO: 10/18/21 Validate inputs in values
public class SQLQueryBuilder<T extends Table> {

    private final SQLQuery sqlQuery;
    private final StringBuilder queryBuilder;
    private final T tableEntity;

    public SQLQueryBuilder(T tableEntity) {
        this.tableEntity = tableEntity;
        this.sqlQuery = new SQLQuery();
        this.queryBuilder = new StringBuilder();
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
        performTableCheck(columns);
        for (Column column : columns) {
            this.queryBuilder
                    .append(column.getColumnName())
                    .append(", ");
        }
        this.queryBuilder.deleteCharAt(this.queryBuilder.length() - 2);
        return this;
    }

    public SQLQueryBuilder<T> insert(String tableName, Column... columns) {
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
        this.queryBuilder.append(" VALUES(");
        Set<String> columnNames = this.getTableEntity().getAllColumnNames();
        Column column;
        Object data;
        for (Map.Entry<Column, Object> params : paramMap.entrySet()) {
            column = params.getKey();
            data = params.getValue();
            if (!columnNames.contains(column.getColumnName())) {
                throw new ColumnNotFoundException(column.getColumnName(), this.getTableEntity().getTableName());
            }
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
        return null;
    }

    private void performTableCheck(Column[] columnsToBeChecked)
            throws ColumnNotFoundException, DuplicateEntryException {
        Set<String> columnNames = this.getTableEntity().getAllColumnNames();
        for (Column column : columnsToBeChecked) {
            if (!columnNames.contains(column.getColumnName())) {
                throw new ColumnNotFoundException(column.getColumnName(), this.getTableEntity().getTableName());
            } else {
                if (!columnNames.remove(column.getColumnName())) {
                    throw new DuplicateEntryException();
                }
            }
        }
    }
}
