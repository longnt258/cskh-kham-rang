package vn.com.user_application.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import vn.com.user_application.R;
import vn.com.user_application.core.models.Dentist;

public class HomeRvDentistAdapter extends RecyclerView.Adapter<HomeRvDentistAdapter.ViewHolder> {

    private final List<Dentist> dentistList;
    private final SimpleDateFormat simpleDateFormat;

    public HomeRvDentistAdapter( List<Dentist> dentistList, SimpleDateFormat simpleDateFormat){
        this.dentistList = dentistList;
        this.simpleDateFormat = simpleDateFormat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(),R.layout.item_dentist_rv,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fullName = dentistList.get(position).getFull_name();
        String status = dentistList.get(position).isStatus() ? "Ready" : "Not Ready";
        String startDate = simpleDateFormat.format(dentistList.get(position).getStart_date());
        String endDate = simpleDateFormat.format(dentistList.get(position).getEnd_date());

        holder.tvItemDateStart.setText(String.format("Start date: %s", startDate));
        holder.tvItemDateEnd.setText(String.format("Start date: %s", endDate));
        holder.tvItemName.setText(fullName);
        holder.tvItemStatus.setText(String.format("Status: \n%s", status));
    }

    @Override
    public int getItemCount() {
        return dentistList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemName, tvItemStatus, tvItemDateStart, tvItemDateEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemStatus = itemView.findViewById(R.id.tvItemStatus);
            tvItemDateEnd = itemView.findViewById(R.id.tvItemEndTime);
            tvItemDateStart = itemView.findViewById(R.id.tvItemStartTime);
        }
    }
}