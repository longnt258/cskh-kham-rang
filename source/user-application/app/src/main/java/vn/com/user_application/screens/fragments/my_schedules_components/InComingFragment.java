package vn.com.user_application.screens.fragments.my_schedules_components;

import static vn.com.user_application.Application.getSimpleDateFormat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import vn.com.user_application.Application;
import vn.com.user_application.R;
import vn.com.user_application.adapters.SchedulePageAdapter;
import vn.com.user_application.core.models.Response;
import vn.com.user_application.core.models.Schedule;
import vn.com.user_application.core.network.ApiService;
import vn.com.user_application.screens.customs.VerticalSpaceItemDecoration;

public class InComingFragment extends Fragment {

    private List<Schedule> scheduleList = new ArrayList<>();
    private SchedulePageAdapter adapter;
    private RecyclerView recyclerView;

    public InComingFragment() {
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
        return inflater.inflate(R.layout.fragment_in_comming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        getScheduleList();

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rvHistory_Schedules);
        adapter = new SchedulePageAdapter(scheduleList);

        // Config Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        // Add Space between elements
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        recyclerView.setAdapter(adapter);

    }

    private void getScheduleList() {
        ApiService.apiService.getUserSchedule(Application.currentUser.getUserId()).enqueue(new Callback<Response<List<Schedule>>>() {
            @Override
            public void onResponse(Call<Response<List<Schedule>>> call, retrofit2.Response<Response<List<Schedule>>> response) {
                if(response.isSuccessful() && response.code() == 200){
                    if(response.body() != null && response.body().getStatus() == 1){
                        scheduleList.clear();
                        for(Schedule schedule : response.body().getData()){
                            Date date = null;
                            try {
                                date = getSimpleDateFormat().parse(schedule.getBookingDatetime());
                                if(date.after(new Date()))
                                    scheduleList.add(schedule);
                                Log.e("Date Before", date.toString());
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response<List<Schedule>>> call, @NonNull Throwable throwable) {
                throw new RuntimeException(throwable.getMessage());
            }
        });
    }
}