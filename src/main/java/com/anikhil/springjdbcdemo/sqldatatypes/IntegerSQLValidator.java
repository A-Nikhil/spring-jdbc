package com.anikhil.springjdbcdemo.sqldatatypes;

public class IntegerSQLValidator implements SQLValidator {

	@Override
	public boolean isAcceptable(Object object) {
		return (object instanceof Short) || (object instanceof Integer) || (object instanceof Long);
	}
}
