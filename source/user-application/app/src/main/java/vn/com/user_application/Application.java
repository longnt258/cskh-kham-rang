package vn.com.user_application;

import java.text.SimpleDateFormat;
import java.util.Locale;

import vn.com.user_application.core.models.User;

public class Application extends android.app.Application {

    private static SimpleDateFormat simpleDateFormat;
    public static final String ipHost = "192.168.2.9";
    public static User currentUser;

    public static synchronized SimpleDateFormat getSimpleDateFormat() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        }
        return simpleDateFormat;
    }
}
