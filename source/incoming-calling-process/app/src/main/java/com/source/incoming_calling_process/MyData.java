package com.source.incoming_calling_process;

import java.util.ArrayList;
import java.util.List;

public class MyData {
    private static MyData INSTANCE = new MyData();
    private boolean status = false;
    private List<String> phoneNumbers = new ArrayList();
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

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber + " ");
    }

    public List<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }
}
