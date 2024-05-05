package vn.com.user_application.adapters;

import static vn.com.user_application.Application.getSimpleDateFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import vn.com.user_application.R;
import vn.com.user_application.core.models.Schedule;

public class SchedulePageAdapter extends RecyclerView.Adapter<SchedulePageAdapter.ViewHolder> {

    private final List<Schedule> scheduleList;

    public SchedulePageAdapter(List<Schedule> scheduleList){
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(),R.layout.schedule_user_infor_rv_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        String title = schedule.getTitle();
        String docsName = schedule.getDentistName();
        String bookDate = schedule.getBookingDatetime().substring(0,10);
        String bookTime = schedule.getBookingDatetime().substring(11);
        String status = schedule.getStatus() == 1 ? "Đã xác nhận" : "Chờ xác nhận";

        holder.tvDocsName.setText(docsName);
        holder.tvStatus.setText(status);
        holder.tvBookingDate.setText(bookDate);
        holder.tvBookingTime.setText(bookTime);
        holder.tvTitle.setText(title);

        Date date = null;
        try {
            date = getSimpleDateFormat().parse(schedule.getBookingDatetime());
            if(date.before(new Date()))
                holder.btnCancel.setVisibility(View.INVISIBLE);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        return scheduleList == null ? 0 : scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDocsName, tvBookingDate,tvBookingTime,tvStatus,tvTitle;
        Button btnCancel, btnReSchedule;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDocsName = itemView.findViewById(R.id.tvDocsName);
            tvBookingDate = itemView.findViewById(R.id.tvBookingDate);
            tvBookingTime = itemView.findViewById(R.id.tvBookingTime);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnReSchedule = itemView.findViewById(R.id.btnReschedule);

        }
    }
}
