package com.anikhil.springjdbcdemo.sqldatatypes;

public class StringSQLValidator implements SQLValidator {

	@Override
	public boolean isAcceptable(Object object) {
		return object instanceof String;
	}
}
