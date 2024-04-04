package com.api.cskh.springbootapi.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static Calendar cal = Calendar.getInstance();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Date getTimeWithoutDate(int hour, int minute, int second, int millisecond) {
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millisecond);

        return cal.getTime();
    }

    public static Date getTimeWithDate(int year, int month, int day,
                                       int hour, int minute, int second) {
        cal.set(year, month - 1, day, hour, minute, second);
        return cal.getTime();
    }

    public static String convertDate2String(Date date) {
        return dateFormat.format(date);
    }
}
