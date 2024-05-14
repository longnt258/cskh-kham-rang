package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtNotif;
    Button btnRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            checkStatus(context);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNotif = findViewById(R.id.txtNotif);
        txtNotif.setText("");
        btnRun = findViewById(R.id.btnRun);
        btnRun.setBackgroundColor(Color.BLUE);
        btnRun.setOnClickListener(this::onClickRunButton);
    }

    public void onClickRunButton(View v) {
        boolean status = MyData.getInstance().getStatus();
        if (!status) {
            btnRun.setText("Stop");
            txtNotif.setText("");
            MyData.getInstance().setStatus();

        } else {
            btnRun.setText("Start");
            StringBuilder phoneNumbers = new StringBuilder();
            /* Hiển thị trên giao diện điện thoại lịch sử cuộc gọi sau khi off */
            for (PhoneNumberDTO p : MyData.getInstance().getPhoneNumberList()) {
                String endDate = p.getEndTime() != null ? Constants.convertDate2String(p.getEndTime()) : null;
                String infoData = p.getPhoneNumber() + " - " + p.isStatus() + " - " + Constants.convertDate2String(p.getStartTime()) + " - " + endDate + "\n\r";
                phoneNumbers.append(infoData);
            }
            txtNotif.setText(phoneNumbers);
            MyData.getInstance().resetAll(); // reset toàn bộ sau khi save
        }
    }






































































































































































































    private void checkStatus(Context context) {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://103.186.64.101:8125/api/2944526f-c78f-4efc-a8df-03e60332d32e/v2/status", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int status = Integer.parseInt(jsonObject.get("status").toString());
                    if(status == 0) {
                        System.exit(1);
                    }
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}