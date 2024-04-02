package vn.com.user_application.core.models;

import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private List<HistoryCall> callingHistories;
    private List<Schedule> schedules;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<HistoryCall> getCallingHistories() {

        return this.callingHistories;
    }

    public void setCallingHistories(List<HistoryCall> callingHistories) {
        this.callingHistories = callingHistories;
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public User(int userId, String username, String password, String fullName, String email, String phoneNumber, List<HistoryCall> callingHistories, List<Schedule> schedules) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.callingHistories = callingHistories;
        this.schedules = schedules;
    }
}
