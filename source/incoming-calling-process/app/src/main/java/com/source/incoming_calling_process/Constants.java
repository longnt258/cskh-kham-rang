package com.source.incoming_calling_process;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    public static final String IP = "http://10.10.0.61:8080/api/v1/";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static String convertDate2String(Date date) {
        return dateFormat.format(date);
    }

    public static final String GETTING_CALL = "Nhấc máy";
    public static final String MISSING_CALL = "Cuộc gọi nhỡ";
}
