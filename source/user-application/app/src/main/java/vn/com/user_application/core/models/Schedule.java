package vn.com.user_application.core.models;

import java.util.Date;

public class Schedule {
    private int schedule_id;
    private String title;
    private String description;
    private Date booking_date;

    public Schedule(int schedule_id, String title, String description, Date booking_date) {
        this.schedule_id = schedule_id;
        this.title = title;
        this.description = description;
        this.booking_date = booking_date;
    }

    public Schedule(){}

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
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

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }
}
