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
        /* Bặt/Tắt chức năng bắt cuộc gọi */
        if(!MyData.getInstance().getStatus()) {
            return;
        }
        String incomingCallNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER); // Lấy số điện thoại
        /* because of duplication (vì chương trình sẽ gọi 2 lần: lần 1 sdt = null, lần 2 sdt != null nên cần range điều kiện này)
        *  Đặt flag để tránh bị lẫn lộn giữa EXTRA_STATE_OFFHOOK và EXTRA_STATE_IDLE vì
        *  - Nếu nhận cuộc gọi tới mà không bắt máy --> EXTRA_STATE_RINGING -> EXTRA_STATE_IDLE (cuộc gọi tới -> kết thúc)
        *  - Nếu nhận cuộc gọi tới mà bắt máy --> EXTRA_STATE_RINGING -> EXTRA_STATE_OFFHOOK -> EXTRA_STATE_IDLE (cuộc gọi tới -> bắt máy -> kết thúc)
        *  ---> Flag để chương trình chỉ chạy vào 1 trong 2 trạng thái (EXTRA_STATE_OFFHOOK hoặc EXTRA_STATE_IDLE)
        * */
        if(incomingCallNumber != null) {
            boolean flag = MyData.getInstance().isFlag(); // flag = false
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE); // Lấy trạng thái cuộc gọi

            /* check trạng thái cuộc gọi (EXTRA_STATE_RINGING: đổ chuông, EXTRA_STATE_OFFHOOK: bắt máy, EXTRA_STATE_IDLE: cúp máy) */
            if(Objects.equals(state, TelephonyManager.EXTRA_STATE_RINGING) && !flag) {
                MyData.getInstance().resetFlag(); // reset flag thành mặc định --> xét cuộc gọi mới
                MyData.getInstance().switchFlag(); // flag = true --> bật flag để biết là đã nhận cuộc gọi
                MyData.getInstance().setTempPhoneNumber(new PhoneNumberDTO());
                MyData.getInstance().getTempPhoneNumber().setPhoneNumber(incomingCallNumber);
                MyData.getInstance().getTempPhoneNumber().setStartTime(new Date());
            } else if(Objects.equals(state, TelephonyManager.EXTRA_STATE_OFFHOOK) && flag) { // cuộc gọi hoàn thành
                MyData.getInstance().switchFlag(); // flag = false --> tắt flag để biết là đã kết thúc cuộc gọi
                MyData.getInstance().getTempPhoneNumber().setEndTime(new Date());
                MyData.getInstance().getTempPhoneNumber().setStatus(true);
                MyData.getInstance().addPhoneNumber(MyData.getInstance().getTempPhoneNumber());
                try {
                    createCallingHistory(MyData.getInstance().getTempPhoneNumber()); // Thực hiện lưu dữ liệu vào db
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else if(Objects.equals(state, TelephonyManager.EXTRA_STATE_IDLE) && flag) { // cuộc gọi nhỡ
                MyData.getInstance().switchFlag(); // flag = false --> tắt flag để biết là đã kết thúc cuộc gọi
                MyData.getInstance().getTempPhoneNumber().setEndTime(null);
                MyData.getInstance().getTempPhoneNumber().setStatus(false);
                MyData.getInstance().addPhoneNumber(MyData.getInstance().getTempPhoneNumber());
                try {
                    createCallingHistory(MyData.getInstance().getTempPhoneNumber()); // Thực hiện lưu dữ liệu vào db
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* Lưu thông tin lịch sử cuộc gọi sau mỗi lần nhận cuộc gọi */
    private void createCallingHistory(PhoneNumberDTO phoneNumberDTO) throws JSONException {
        /* Tạo dữ liệu dạng json để gửi request cho api */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNumber", phoneNumberDTO.getPhoneNumber());
        jsonObject.put("status", phoneNumberDTO.isStatus());
        jsonObject.put("description", phoneNumberDTO.isStatus() ? Constants.GETTING_CALL : Constants.MISSING_CALL);
        jsonObject.put("startDate", Constants.convertDate2String(phoneNumberDTO.getStartTime()));
        Date endDate = phoneNumberDTO.getEndTime();
        if(endDate != null) { // endDate = null --> gọi nhỡ, ngược lại là cuộc gọi bình thường
            jsonObject.put("endDate", Constants.convertDate2String(endDate));
        }

        final String url = Constants.IP + "calling-history/create"; // Khai báo API
        /* Thực hiện gọi API với jsonObject được tạo ở trên */
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
