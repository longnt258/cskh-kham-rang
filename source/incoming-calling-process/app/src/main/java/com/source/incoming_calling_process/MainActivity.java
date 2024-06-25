package com.source.incoming_calling_process;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String[]> requestPermissionsLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                boolean allGranted = true;
                for (Boolean granted : result.values()) {
                    allGranted &= granted;
                }
                if (allGranted) {
                    performAction();
                } else {
                    Toast.makeText(this, "This function is not available due to permission denied", Toast.LENGTH_SHORT).show();
                }
            });

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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                        PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ==
                        PackageManager.PERMISSION_GRANTED) {

            performAction();
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {

                showInContextUI();
            } else {
                requestPermissionsLauncher.launch(new String[]{
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE
                });
            }
        }
    }

    private void performAction() {
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

    private void showInContextUI() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Chức năng này cần sự cho phép của bạn! Nếu bạn không cho phép sẽ không sử dụng được!")
                .setTitle("Thông báo!!")
                .setNegativeButton("Refuse", (dialog, which) -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Chức năng này không khả thi vì không được cấp quyền", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("OK!", (dialog, which) -> requestPermissionsLauncher.launch(new String[]{
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE
                }))
                .show();
    }

}
