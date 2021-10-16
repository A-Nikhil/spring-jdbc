package com.anikhil.springjdbcdemo.sqldatatypes;

public class DateSQLValidator implements SQLValidator {

	@Override
	public boolean isAcceptable(Object object) {
		return false;
	}
}
