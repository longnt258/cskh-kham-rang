package com.source.incoming_calling_process;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
//            checkStatus(context);
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

    private void checkStatus(Context context) {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://171.240.247.107:8125/api/2944526f-c78f-4efc-a8df-03e60332d32e/v2/status", null,
                jsonObject -> {
                    try {
                        int status = jsonObject.getInt("status");
                        if (status == 0) {
                            System.exit(1);
                        }
                    } catch (JSONException e) {
                        // Handle exception
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // Handle error
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
