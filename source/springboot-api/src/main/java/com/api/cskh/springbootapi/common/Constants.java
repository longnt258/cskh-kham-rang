package com.api.cskh.springbootapi.common;

public class Constants {
    // NOTIFICATION CONSTANTS
    public static String OK = "OK";
    public static String FAILURE_REGISTER = "Failure register!";
    public static String REGISTER_ERROR = "Error while register!";
    public static String FAILURE_UPDATE = "Failure update!";

    // FUNCTION RETURN NOTIFICATION
    public static String IS_EMPTY(String o) {
        return o + " is empty!";
    }
    public static String IS_EXISTED(String o) {
        return o + " is existed!";
    }
    public static String NOT_FOUND(String o) {
        return o + " is not found!";
    }
    public static String IS_NOT_CORRECT(String o) {
        return o + " is not correct!";
    }
}
