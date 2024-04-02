package vn.com.user_application.screens;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import vn.com.user_application.R;
import vn.com.user_application.screens.fragments.LoginFragment;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, LoginFragment.class, null)
                    .commit();
        }


    }
}
