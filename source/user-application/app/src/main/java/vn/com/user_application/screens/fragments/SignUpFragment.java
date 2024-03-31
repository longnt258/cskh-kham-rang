package vn.com.user_application.screens.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import vn.com.user_application.R;


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
        tvBackToLogin.setOnClickListener(v->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, LoginFragment.class,null));
    }
}