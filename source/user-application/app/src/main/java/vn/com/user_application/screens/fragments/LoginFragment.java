package vn.com.user_application.screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import vn.com.user_application.Application;
import vn.com.user_application.R;
import vn.com.user_application.core.models.Response;
import vn.com.user_application.core.models.User;
import vn.com.user_application.core.network.ApiService;
import vn.com.user_application.screens.MainActivity;


public class LoginFragment extends Fragment {

    private EditText edUserName;
    private TextInputEditText edPassword;
    private Button btnLogin;
    private TextView tvDontHaveAcount;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        listeningEvents();
    }

    private void listeningEvents() {
        tvDontHaveAcount.setOnClickListener(v -> requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, SignUpFragment.class, null).commit());

        btnLogin.setOnClickListener(v -> {
            performLogin();
        });
    }

    private void performLogin() {
        String userName = edUserName.getText().toString().trim();
        String password = Objects.requireNonNull(edPassword.getText()).toString().trim();
        if (userName.isEmpty()) {
            edUserName.setError("User name is required");
        } else if (password.isEmpty()) {
            edPassword.setError("Password is required");
        } else {
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            ApiService.apiService.login(user).enqueue(new Callback<Response<User>>() {

                @Override
                public void onResponse( Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                    if (response.body() != null && response.body().getStatus() == 1) {
                        Application.currentUser = response.body().getData();
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                        requireActivity().finish();
                    } else {
                        Toast.makeText(requireActivity(), "Username or Password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure( Call<Response<User>> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }
    }


    private void initView(View view) {
        edUserName = view.findViewById(R.id.ed_user_name);
        edPassword = view.findViewById(R.id.ed_password);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvDontHaveAcount = view.findViewById(R.id.tvDontHaveAccount);
    }
}