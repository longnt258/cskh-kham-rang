package vn.com.user_application.screens.fragments.my_schedules_components;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HistoryFragment extends Fragment {

    private List<Schedule> scheduleList = new ArrayList<>();
    private SchedulePageAdapter adapter;
    private RecyclerView recyclerView;
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
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
                            Date date = new Date(schedule.getBookingDatetime());
                            if(date.after(new Date()))
                            scheduleList.add(schedule);
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