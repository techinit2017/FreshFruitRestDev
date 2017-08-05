package com.ffp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateFormatter(String format, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}
