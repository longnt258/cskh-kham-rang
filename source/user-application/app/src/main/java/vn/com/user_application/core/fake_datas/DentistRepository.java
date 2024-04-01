package vn.com.user_application.core.fake_datas;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import vn.com.user_application.core.models.Dentist;

public class DentistRepository {
    public static final List<Dentist> dentists = Arrays.asList(
            new Dentist(0, "Dr.Money", true, new Date(), new Date(new Date().getTime() + 330000L)),
            new Dentist(1, "Dr.LOOO", true, new Date(), new Date(new Date().getTime() + 340000L)),
            new Dentist(2, "Dr.YOLO", true, new Date(), new Date(new Date().getTime() + 350000L)),
            new Dentist(3, "Dr.Bala", true, new Date(), new Date(new Date().getTime() + 360000L)),
            new Dentist(4, "Dr.Hoho", true, new Date(), new Date(new Date().getTime() + 370000L)));
}
