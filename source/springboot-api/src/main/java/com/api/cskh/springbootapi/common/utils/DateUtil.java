package com.api.cskh.springbootapi.common.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static Calendar cal = Calendar.getInstance();

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
}
