package com.anikhil.sqllib.query;

import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.DuplicateEntryException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;
import com.anikhil.sqllib.utils.ColumnUtils;

import java.util.Map;

public class SQLQueryBuilder<T extends Table> {

    private final SQLQuery sqlQuery;
    private final StringBuilder queryBuilder;
    private final T tableEntity;
    private final String tableName;
    private final ColumnUtils<T> columnUtils;

    public SQLQueryBuilder(T tableEntity) {
        this.sqlQuery = new SQLQuery();
        this.queryBuilder = new StringBuilder();
        this.tableEntity = tableEntity;
        this.tableName = tableEntity.getTableName();
        this.columnUtils = new ColumnUtils<>(tableEntity);
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
        this.columnUtils.performColumnValidations(columns);
        for (Column column : columns) {
            this.queryBuilder
                    .append(column.getColumnName())
                    .append(", ");
        }
        this.queryBuilder.deleteCharAt(this.queryBuilder.length() - 2)
                .append("FROM ")
                .append(this.tableName);
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
        this.queryBuilder.append("DELETE FROM ")
                .append(this.tableName);
        return this;
    }

    public SQLQueryBuilder<T> update(Map<Column, Object> updateParamMap)
            throws ColumnNotFoundException, DuplicateEntryException, WrongDataTypeException {
        this.columnUtils.performColumnValidations(updateParamMap);
        this.queryBuilder.append(" UPDATE ")
                .append(tableName)
                .append(" SET ");
        Column column;
        Object data;
        for (Map.Entry<Column, Object> entry : updateParamMap.entrySet()) {
            column = entry.getKey();
            data = entry.getValue();
            this.queryBuilder.append(column.getColumnName())
                    .append(" = ")
                    .append(this.columnUtils.getFormattedValueForData(data, column.getDataType()))
                    .append(", ");
        }
        this.queryBuilder.delete(this.queryBuilder.length() - 2,
                this.queryBuilder.length());
        return this;
    }

    public SQLQueryBuilder<T> values(Map<Column, Object> paramMap)
            throws ColumnNotFoundException, DuplicateEntryException, WrongDataTypeException {
        this.columnUtils.performColumnValidations(paramMap);
        this.queryBuilder.append(" VALUES(");
        Column column;
        Object data;
        for (Map.Entry<Column, Object> params : paramMap.entrySet()) {
            column = params.getKey();
            data = params.getValue();
            this.queryBuilder.append(this.columnUtils.getFormattedValueForData(
                    data, column.getDataType())
            ).append(", ");
        }
        this.queryBuilder.delete(this.queryBuilder.length() - 2, this.queryBuilder.length())
                .append(")");

        return this;
    }

    public SQLQueryBuilder<T> where(SQLQueryCondition condition) {
        this.queryBuilder.append(" WHERE ")
                .append(condition.toString());
        return this;
    }
}
