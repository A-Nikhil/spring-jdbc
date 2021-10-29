package com.anikhil.sqllib.utils;

import com.anikhil.sqllib.datatype.SQLDataType;
import com.anikhil.sqllib.exceptions.ColumnNotFoundException;
import com.anikhil.sqllib.exceptions.DuplicateEntryException;
import com.anikhil.sqllib.exceptions.WrongDataTypeException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ColumnUtils<T extends Table> {

    private final T tableEntity;
    private final String tableName;
    private final Set<String> allColumnNames;

    public ColumnUtils(T tableEntity) {
        this.tableEntity = tableEntity;
        this.tableName = tableEntity.getTableName();
        this.allColumnNames = tableEntity.getAllColumnNames();
    }

    public T getTableEntity() {
        return this.tableEntity;
    }

    public String getFormattedValueForData(Object data, SQLDataType dataType) throws WrongDataTypeException {
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

    public void performColumnValidations(Map<Column, Object> columnDataMap)
            throws ColumnNotFoundException, DuplicateEntryException {
        this.performColumnValidations(columnDataMap.keySet().toArray(new Column[0]));
    }

    public void performColumnValidations(Column[] columnsToBeChecked)
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

    public void doesColumnExist(String columnName) throws ColumnNotFoundException {
        if (!this.allColumnNames.contains(columnName)) {
            throw new ColumnNotFoundException(columnName, this.tableName);
        }
    }

    public void dataTypeCheck(Column column, Object value) throws WrongDataTypeException {
        if (!column.getDataType().getValidator().isAcceptable(value)) {
            throw new WrongDataTypeException("Wrong data type for column " + column.getColumnName());
        }
    }
}
