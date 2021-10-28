package com.anikhil.sqllib.query;

import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;
import com.anikhil.sqllib.utils.ColumnUtils;

import java.util.Set;

public class SQLQueryConditionBuilder<T extends Table> {

    private final StringBuilder conditionBuilder;
    private final String tableName;
    private final Set<String> allColumnNames;
    private final ColumnUtils<T> columnUtils;

    public SQLQueryConditionBuilder(T tableEntity) {
        this.conditionBuilder = new StringBuilder();
        this.tableName = tableEntity.getTableName();
        this.allColumnNames = tableEntity.getAllColumnNames();
        this.columnUtils = new ColumnUtils<>(tableEntity);
    }

    // TODO: 10/29/21 Equals
    public SQLQueryConditionBuilder<T> equals(Column column, Object value)
            throws ColumnNotFoundException, WrongDataTypeException {
        this.columnUtils.doesColumnExist(column.getColumnName());
        this.columnUtils.dataTypeCheck(column, value);
        return this;
    }

    // TODO: 10/29/21 Greater than

    // TODO: 10/29/21 Less than

    // TODO: 10/29/21 LIKE

    // TODO: 10/29/21 in

    // TODO: 10/29/21 Bracketizer

    public SQLQueryCondition build() {
        return new SQLQueryCondition(this.conditionBuilder.toString());
    }
}
