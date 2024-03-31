package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtNotif;
    Button btnRun;
    Connection connection = null;

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
        if(!status) {
            btnRun.setText("Stop");
            txtNotif.setText("");
            connectSQLServer();
        } else {
            btnRun.setText("Start");
            StringBuilder phoneNumbers = new StringBuilder();
            MyData.getInstance().getPhoneNumbers().forEach(phoneNumbers::append);
            txtNotif.setText(phoneNumbers);
        }
        MyData.getInstance().setStatus();
    }

    public void connectSQLServer() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Log.d("CONNECT STRING", "OK 111");
            String connectStr = "jdbc:sqlserver://10.10.0.8;databaseName=cskh_kham_rang;integratedSecurity=true;";
            Log.d("CONNECT STRING", connectStr);
            connection = DriverManager.getConnection(connectStr);
            txtNotif.setText("Successful connection");
        } catch (Exception e) {
            Log.e("ERROR_CONNECTION", Objects.requireNonNull(e.getMessage()));
            txtNotif.setText("Failure connection");
        }
    }
}