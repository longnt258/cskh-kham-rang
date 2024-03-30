package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Objects;

public class IncomingCallingProcess extends BroadcastReceiver {
    Context context;
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if(!MyData.getInstance().getStatus()) {
            return;
        }

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(Objects.equals(state, TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingCallNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(incomingCallNumber != null) {
                processIncomingCall(incomingCallNumber);
            }
        }
    }

    private void processIncomingCall(String phoneNumber) {
        if (phoneNumber != null) {
            Log.d("Phone number", phoneNumber);
            MyData.getInstance().addPhoneNumber(phoneNumber);
        } else {
            Log.d("Phone number", "No having phone number!");
        }
    }
}
