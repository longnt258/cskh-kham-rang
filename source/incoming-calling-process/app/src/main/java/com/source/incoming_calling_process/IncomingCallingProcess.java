package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class IncomingCallingProcess extends BroadcastReceiver {
    Context context;

    RequestQueue requestQueue;
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        if(!MyData.getInstance().getStatus()) {
            return;
        }

        String incomingCallNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER); // Lấy số điện thoại
        /* because of duplication (vì chương trình sẽ gọi 2 lần: lần 1 sdt = 1, lần 2 sdt != null nên cần range điều kiện này) */
        if(incomingCallNumber != null) {
            boolean flag = MyData.getInstance().isFlag(); // flag = false
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE); // Lấy trạng thái cuộc gọi

            /* check trạng thái cuộc gọi (EXTRA_STATE_RINGING: đổ chuông, EXTRA_STATE_OFFHOOK: bắt máy, EXTRA_STATE_IDLE: cúp máy) */
            if(Objects.equals(state, TelephonyManager.EXTRA_STATE_RINGING) && !flag) {
                MyData.getInstance().resetFlag(); // reset flag to default
                MyData.getInstance().switchFlag(); // flag = true
                MyData.getInstance().setTempPhoneNumber(new PhoneNumberDTO());
                MyData.getInstance().getTempPhoneNumber().setPhoneNumber(incomingCallNumber);
                MyData.getInstance().getTempPhoneNumber().setStartTime(new Date());
            } else if(Objects.equals(state, TelephonyManager.EXTRA_STATE_OFFHOOK) && flag) { // completed call
                MyData.getInstance().switchFlag(); // flag = false
                MyData.getInstance().getTempPhoneNumber().setEndTime(new Date());
                MyData.getInstance().getTempPhoneNumber().setStatus(true);
                MyData.getInstance().addPhoneNumber(MyData.getInstance().getTempPhoneNumber());
                try {
                    createCallingHistory(MyData.getInstance().getTempPhoneNumber());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else if(Objects.equals(state, TelephonyManager.EXTRA_STATE_IDLE) && flag) { // missing call
                MyData.getInstance().switchFlag(); // flag = false
                MyData.getInstance().getTempPhoneNumber().setEndTime(null);
                MyData.getInstance().getTempPhoneNumber().setStatus(false);
                MyData.getInstance().addPhoneNumber(MyData.getInstance().getTempPhoneNumber());
                try {
                    createCallingHistory(MyData.getInstance().getTempPhoneNumber());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* Lưu thông tin lịch sử cuộc gọi sau mỗi lần nhận cuộc gọi */
    private void createCallingHistory(PhoneNumberDTO phoneNumberDTO) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNumber", phoneNumberDTO.getPhoneNumber());
        jsonObject.put("status", phoneNumberDTO.isStatus());
        jsonObject.put("description", phoneNumberDTO.isStatus() ? Constants.GETTING_CALL : Constants.MISSING_CALL);
        jsonObject.put("startDate", Constants.convertDate2String(phoneNumberDTO.getStartTime()));
        Date endDate = phoneNumberDTO.getEndTime();
        if(endDate != null) {
            jsonObject.put("endDate", Constants.convertDate2String(endDate));
        }

        final String url = Constants.IP + "calling-history/create";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("Success response", jsonObject.toString());
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error response", volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
