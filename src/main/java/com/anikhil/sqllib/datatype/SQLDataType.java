package com.anikhil.sqllib.datatype;

public enum SQLDataType {
	STRING(new StringSQLValidator()),
	INTEGER(new IntegerSQLValidator()),
	DECIMAL(new DecimalSQLValidator()),
	DATE(new DateSQLValidator());

	private final SQLValidator validator;

	SQLDataType(SQLValidator validator) {
		this.validator = validator;
	}

	public SQLValidator getValidator() {
		return validator;
	}
}
