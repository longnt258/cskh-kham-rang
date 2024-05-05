package com.source.incoming_calling_process;

import java.util.Date;

public class PhoneNumberDTO {
    private String phoneNumber;
    private boolean status; // false: cuộc gọi nhỡ, true: ok
    private Date startTime;
    private Date endTime;
    private int callingSeconds; // endTime - startTime

    public PhoneNumberDTO() {
    }

//    public PhoneNumberDTO(String phoneNumber, boolean status, Date startTime, Date endTime, int callingSeconds) {
//        this.phoneNumber = phoneNumber;
//        this.status = status;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.callingSeconds = callingSeconds;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCallingSeconds() {
        return callingSeconds;
    }

    public void setCallingSeconds(int callingSeconds) {
        this.callingSeconds = callingSeconds;
    }
}
