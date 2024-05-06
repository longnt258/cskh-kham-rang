package com.source.incoming_calling_process;

import java.util.ArrayList;
import java.util.List;

public class MyData {
    private static final MyData INSTANCE = new MyData();
    private boolean status = false; // True: Bật, False: Tắt (khi start bắt cuộc gọi tới)
    private boolean flag = false; // Biến cờ để xác định cuộc gọi đã kết thúc hay chưa (trong file IncomingCallingProcess đã giải thích)
    private PhoneNumberDTO tempPhoneNumber = new PhoneNumberDTO();
    private List<PhoneNumberDTO> phoneNumberList = new ArrayList<>();
    private MyData() {

    }

    public static MyData getInstance() {
        return INSTANCE;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus() {
        this.status = !status;
    }
    public void addPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        this.phoneNumberList.add(phoneNumberDTO);
    }
    public List<PhoneNumberDTO> getPhoneNumberList() {
        return phoneNumberList;
    }

    public PhoneNumberDTO getTempPhoneNumber() {
        return tempPhoneNumber;
    }

    public void setTempPhoneNumber(PhoneNumberDTO tempPhoneNumber) {
        this.tempPhoneNumber = tempPhoneNumber;
    }
    public void switchFlag() {
        flag = !flag;
    }
    public boolean isFlag() {
        return flag;
    }
    public void resetFlag() {
        flag = false;
    }
    public void resetAll() {
        resetFlag();
        phoneNumberList = new ArrayList<>();
        status = false;
    }
}
