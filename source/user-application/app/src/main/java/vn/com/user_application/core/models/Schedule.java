package vn.com.user_application.core.models;

import java.util.Date;

public class Schedule {
    private String code;
    private String title;
    private String description;
    private String bookingDatetime;
    private int status;
    private String dentistName;
    private String userFullName;
    private int userId;

    public Schedule(){}

    public Schedule(String code, String title, String description, String bookingDatetime, int status, String dentistName, String userFullName) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.bookingDatetime = bookingDatetime;
        this.status = status;
        this.dentistName = dentistName;
        this.userFullName = userFullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookingDatetime() {
        return bookingDatetime;
    }

    public void setBookingDatetime(String bookingDatetime) {
        this.bookingDatetime = bookingDatetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
