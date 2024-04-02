package vn.com.user_application.core.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dentist {
    private int dentistId;
    private String fullName;
    private boolean status;
    private Date startDatetime;
    private Date endDatetime;
    private List<Schedule> schedules = new ArrayList<>();

    public Dentist(){}

    public Dentist(int dentistId, String fullName, boolean status, Date startDatetime, Date endDatetime, List<Schedule> schedules) {
        this.dentistId = dentistId;
        this.fullName = fullName;
        this.status = status;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.schedules = schedules;
    }

    public Dentist(int dentistId, String fullName, boolean status, Date startDatetime, Date endDatetime) {
        this.dentistId = dentistId;
        this.fullName = fullName;
        this.status = status;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "dentistId=" + dentistId +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                ", startDatetime=" + startDatetime +
                ", endDatetime=" + endDatetime +
                ", schedules=" + schedules +
                '}';
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }
}
