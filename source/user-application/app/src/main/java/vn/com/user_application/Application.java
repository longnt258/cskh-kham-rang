package vn.com.user_application;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Application extends android.app.Application {

    private static SimpleDateFormat simpleDateFormat;

    public static synchronized SimpleDateFormat getSimpleDateFormat() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        }
        return simpleDateFormat;
    }
}
