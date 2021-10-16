package com.anikhil.sqllib.datatype;

public class StringSQLValidator implements SQLValidator {

	@Override
	public boolean isAcceptable(Object object) {
		return object instanceof String;
	}
}
