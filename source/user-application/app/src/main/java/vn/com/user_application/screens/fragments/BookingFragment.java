package vn.com.user_application.screens.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import vn.com.user_application.Application;
import vn.com.user_application.R;
import vn.com.user_application.core.models.Response;
import vn.com.user_application.core.models.Schedule;
import vn.com.user_application.core.network.ApiService;

public class BookingFragment extends Fragment {

    public BookingFragment(){}

    private EditText etTitle, etDescription, etDentistName, etUserFullName;
    private TextView tvSelectedDate;
    private String selectedDate,formattedDateTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Đặt lịch");

        // Thiết lập nút back
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Xử lý sự kiện khi nút back được nhấn
        toolbar.setNavigationOnClickListener(v -> {
            // Xử lý việc quay lại trang trước đó
            requireActivity().onBackPressed();
        });

        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        tvSelectedDate = view.findViewById(R.id.tv_selected_date);
        Button buttonPickDate = view.findViewById(R.id.button_pick_date);
        Button buttonSubmit = view.findViewById(R.id.button_submit);

        buttonPickDate.setOnClickListener(v -> showDatePickerDialog());
        buttonSubmit.setOnClickListener(v -> submitBooking());

        return view;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    tvSelectedDate.setText("Ngày đã chọn: " + selectedDate);
                    try {
                        Date date = Application.getSimpleDateFormat().parse(selectedDate + " 00:00:00");
                        formattedDateTime = Application.getSimpleDateFormat().format(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void submitBooking() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();

        if (title.isEmpty() || description.isEmpty() || selectedDate == null) {
            // Hiển thị thông báo lỗi hoặc xử lý trường hợp nhập liệu không đầy đủ
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Schedule schedule = new Schedule();
        schedule.setBookingDatetime(formattedDateTime);
        schedule.setDescription(description);
        schedule.setTitle(title);
        schedule.setUserId(Application.currentUser.getUserId());
        ApiService.apiService.bookingSchedule(schedule).enqueue(new Callback<Response<Schedule>>() {
            @Override
            public void onResponse(Call<Response<Schedule>> call, retrofit2.Response<Response<Schedule>> response) {
                if(response.code() == 200){
                    if(response.body().getStatus() == 1){
                        Toast.makeText(requireContext(), "Đặt lịch thành công, vui lòng chờ nhân viên xác nhận", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<Schedule>> call, Throwable throwable) {
                Toast.makeText(requireContext(),"Có lỗi xảy ra. Vui lòng thử lại!",Toast.LENGTH_SHORT).show();
                Log.e("Booking error", throwable.getMessage());
            }
        });

    }

}
