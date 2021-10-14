package com.anikhil.springjdbcdemo.sqldatatypes;

public class DecimalSQLValidator implements SQLValidator {

	@Override
	public boolean isAcceptable(Object object) {
		return false;
	}
}
