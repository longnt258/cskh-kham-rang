package vn.com.user_application.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyScheduleViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] list;
    private final String[] listName = { "SẮP TỚI", "LỊCH SỬ"};

    public MyScheduleViewPagerAdapter(@NonNull Fragment fragment, Fragment[] list) {
        super(fragment);
        this.list = list;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list[position];
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public String getName(int position){
        return listName[position];
    }
}
