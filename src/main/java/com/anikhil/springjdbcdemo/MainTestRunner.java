package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.springjdbcdemo.sqltables.TableFactory;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.table.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTestRunner {
	private static final Logger LOG = LoggerFactory.getLogger(MainTestRunner.class);

	public static void main(String[] args) {
		testNewLibrary();
	}

	private static boolean checkIntegerValidator() {
		return false;
	}

	private static void testNewLibrary() {
		CourseTable courseTable = TableFactory.getTableByName("course", CourseTable.class);
		Column column = courseTable.courseId;
	}
}
