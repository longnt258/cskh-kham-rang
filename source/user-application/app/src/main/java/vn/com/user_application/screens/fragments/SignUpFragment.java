package vn.com.user_application.screens.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import vn.com.user_application.Application;
import vn.com.user_application.R;
import vn.com.user_application.core.models.Response;
import vn.com.user_application.core.models.User;
import vn.com.user_application.core.network.ApiService;
import vn.com.user_application.screens.MainActivity;


public class SignUpFragment extends Fragment {

    private EditText edFullName, edUserName, edEmail, edPhoneNumber;
    private TextInputEditText edPassword, edConfirmPassword;
    private Button btnSignUp;
    private TextView tvBackToLogin;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        addListeningEvents();

    }

    private void initView(View view) {
        tvBackToLogin = view.findViewById(R.id.tvBackToLogin);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        edEmail = view.findViewById(R.id.ed_email);
        edConfirmPassword = view.findViewById(R.id.ed_confirm_password);
        edPassword = view.findViewById(R.id.ed_password);
        edFullName = view.findViewById(R.id.ed_full_name);
        edPhoneNumber = view.findViewById(R.id.ed_phone_number);
        edUserName = view.findViewById(R.id.ed_user_name);
    }

    private void addListeningEvents() {
        tvBackToLogin.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new LoginFragment())
                        .commit());
        
        btnSignUp.setOnClickListener(v -> performSignUp());
    }

    private void performSignUp() {
        String phoneNumber = edPhoneNumber.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String fullName = edFullName.getText().toString().trim();
        String userName = edUserName.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String confirmPassword = edConfirmPassword.getText().toString().trim();
        if(phoneNumber.isEmpty()) {
            edPhoneNumber.setError("Số điện thoại không được để trống");
            return;
        }
        if(email.isEmpty()){
            edEmail.setError("Email không được để trống");
            return;
        }
        if(fullName.isEmpty()){
            edFullName.setError("Tên không được để trống");
            return;
        }
        if(userName.isEmpty()){
            edUserName.setError("Tên đăng nhập không được để trống");
            return;
        }if(password.isEmpty()){
            edPassword.setError("Mật khẩu không được để trống");
        }if(confirmPassword.isEmpty()){
            edConfirmPassword.setError("Xác nhận mật khẩu không được để trống");
        }if(!password.equals(confirmPassword)){
            Toast.makeText(requireContext(), "Mật khẩu và xác nhận không trùng khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        User req = new User();
        req.setUsername(userName);
        req.setEmail(email);
        req.setPassword(password);
        req.setFullName(fullName);
        req.setPhoneNumber(phoneNumber);
        ApiService.apiService.register(req).enqueue(new Callback<Response<User>>() {
            @Override
            public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                Log.e("Response register",  "Request = " + call.request().body().toString());
                if(response.body() != null && response.body().getStatus() ==1 ){
                    Application.currentUser = response.body().getData();
                    startActivity(new Intent(requireActivity(), MainActivity.class));
                    requireActivity().finish();
                }else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Response<User>> call, Throwable throwable) {
                throw new RuntimeException(throwable.getMessage());
            }
        });
    }

}