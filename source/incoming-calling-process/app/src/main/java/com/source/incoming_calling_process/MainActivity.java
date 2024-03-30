package com.source.incoming_calling_process;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    @SuppressLint("SetTextI18n")
    public void onClickRunButton(View v) {
        boolean status = MyData.getInstance().getStatus();
        if(!status) {
            btnRun.setText("Stop");
            txtNotif.setText("Starting to application....");
        } else {
            btnRun.setText("Start");
//            txtNotif.setText("");
            StringBuilder phoneNumbers = new StringBuilder();
            MyData.getInstance().getPhoneNumbers().forEach(phoneNumbers::append);
            txtNotif.setText(phoneNumbers);
        }
        MyData.getInstance().setStatus();
    }
}