package com.anikhil.sqllib.query;

import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;
import com.anikhil.sqllib.utils.ColumnUtils;

@SuppressWarnings("unused")
public class SQLQueryConditionBuilder<T extends Table> {

    private static final String EQ = " = ";
    private static final String GT = " < ";
    private static final String GTE = " <= ";
    private static final String LT = " > ";
    private static final String LTE = " >= ";
    private static final String LIKE = " LIKE ";
    private static final String IN = " IN ";
    private static final String AND = " AND ";
    private static final String OR = " OR ";

    private final StringBuilder conditionBuilder;
    private final ColumnUtils<T> columnUtils;

    public SQLQueryConditionBuilder(T tableEntity) {
        this.conditionBuilder = new StringBuilder();
        this.columnUtils = new ColumnUtils<>(tableEntity);
    }

    public SQLQueryCondition build() {
        return new SQLQueryCondition(this.conditionBuilder.toString());
    }

    /**
     * Adds an AND condition
     * @return conditionBuilder
     */
    public SQLQueryConditionBuilder<T> and() {
        this.conditionBuilder.append(AND);
        return this;
    }

    /**
     * Adds an OR condition
     * @return conditionBuilder
     */
    public SQLQueryConditionBuilder<T> or() {
        this.conditionBuilder.append(OR);
        return this;
    }

    public SQLQueryConditionBuilder<T> equals(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(EQ, column, value);
    }

    public SQLQueryConditionBuilder<T> greaterThan(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(GT, column, value);
    }

    public SQLQueryConditionBuilder<T> greaterThanEqualTo(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(GTE, column, value);
    }

    public SQLQueryConditionBuilder<T> lessThan(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(LT, column, value);
    }

    public SQLQueryConditionBuilder<T> lessThanEqualTo(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(LTE, column, value);
    }

    public SQLQueryConditionBuilder<T> like(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        return this.buildSnippetForCondition(LIKE, column, value);
    }

    public SQLQueryConditionBuilder<T> in(Column column, Object... values)
            throws ColumnNotFoundException, WrongDataTypeException {
        this.columnUtils.doesColumnExist(column.getColumnName());
        StringBuilder inBuilder = new StringBuilder();
        this.conditionBuilder.append(column.getColumnName())
                .append(IN)
                .append("(");
        for (Object value : values) {
            this.columnUtils.dataTypeCheck(column, value);
            inBuilder.append(this.columnUtils.getFormattedValueForData(value, column.getDataType()))
                    .append(", ");
        }
        inBuilder.delete(inBuilder.length() - 2, inBuilder.length())
                .append(")");
        this.conditionBuilder.append(inBuilder);
        return this;
    }

    public SQLQueryConditionBuilder<T> surroundBrackets() {
        this.conditionBuilder.insert(0, "(")
                .insert(this.conditionBuilder.length(), ")");
        return this;
    }

    private SQLQueryConditionBuilder<T> buildSnippetForCondition(String conditionSign, Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        this.columnUtils.doesColumnExist(column.getColumnName());
        this.columnUtils.dataTypeCheck(column, value);
        this.conditionBuilder.append(column.getColumnName())
                .append(conditionSign)
                .append(this.columnUtils.getFormattedValueForData(value, column.getDataType()));
        return this;
    }
}
