package vn.com.user_application.core.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dentist {
    private int dentistId;
    private String fullName;
    private boolean status;
    private Date startDateTime;
    private Date endDateTime;
    private List<Schedule> schedules = new ArrayList<>();

    public Dentist(){}

    public Dentist(int dentistId, String fullName, boolean status, Date startDateTime, Date endDateTime, List<Schedule> schedules) {
        this.dentistId = dentistId;
        this.fullName = fullName;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.schedules = schedules;
    }

    public Dentist(int dentistId, String fullName, boolean status, Date startDateTime, Date endDateTime) {
        this.dentistId = dentistId;
        this.fullName = fullName;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "dentistId=" + dentistId +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                ", startDatetime=" + startDateTime +
                ", endDatetime=" + endDateTime +
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
