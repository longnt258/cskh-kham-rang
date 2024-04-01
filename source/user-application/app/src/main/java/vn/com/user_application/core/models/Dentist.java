package vn.com.user_application.core.models;

import java.util.Date;

public class Dentist {
    private int dentist_id;
    private String full_name;
    private boolean status;
    private Date start_date;
    private Date end_date;

    public Dentist(){}

    public Dentist(int dentist_id, String full_name, boolean status, Date start_date, Date end_date) {
        this.dentist_id = dentist_id;
        this.full_name = full_name;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDentist_id() {
        return dentist_id;
    }

    public void setDentist_id(int dentist_id) {
        this.dentist_id = dentist_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
