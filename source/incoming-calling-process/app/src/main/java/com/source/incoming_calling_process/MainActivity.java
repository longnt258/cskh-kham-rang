package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
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
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
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

        } else { // save to db and remove list phone
            btnRun.setText("Start");
            StringBuilder phoneNumbers = new StringBuilder();
            /* Hiển thị trên giao diện điện thoại lịch sử cuộc gọi sau khi off */
            for (PhoneNumberDTO p : MyData.getInstance().getPhoneNumberList()) {
                String endDate = p.getEndTime() != null ? Constants.convertDate2String(p.getEndTime()) : null;
                String infoData = p.getPhoneNumber() + " - " + p.isStatus() + " - " + Constants.convertDate2String(p.getStartTime()) + " - " + endDate + "\n\r";
                phoneNumbers.append(infoData);
            }
            txtNotif.setText(phoneNumbers);
            MyData.getInstance().resetAll(); // reset all after save
        }
    }
}