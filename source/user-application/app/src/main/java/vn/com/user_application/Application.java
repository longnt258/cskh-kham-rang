package vn.com.user_application;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

import vn.com.user_application.core.models.User;
import vn.com.user_application.screens.fragments.my_schedules_components.HistoryFragment;
import vn.com.user_application.screens.fragments.my_schedules_components.InComingFragment;

public class Application extends android.app.Application {

    private static SimpleDateFormat simpleDateFormat;
    public static final Fragment[] scheduleFragmentList = {new InComingFragment(),new HistoryFragment()};
    public static final String ipHost = "192.168.1.2";
    public static User currentUser;

    public static synchronized SimpleDateFormat getSimpleDateFormat() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        }
        return simpleDateFormat;
    }
}
