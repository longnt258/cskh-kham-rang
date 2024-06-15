package com.api.cskh.springbootapi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static Date convertString2Date(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static Long getHoursRemained(Date date1, Date date2) {
        Long milliSecondRemained = date1.getTime() - date2.getTime();
        return TimeUnit.MILLISECONDS.toHours(milliSecondRemained);
    }

    public static void main(String[] args) {
        System.out.println(getHoursRemained(new Date(), getTimeWithDate(2024, 6, 15, 12, 20, 0)));
    }
}
