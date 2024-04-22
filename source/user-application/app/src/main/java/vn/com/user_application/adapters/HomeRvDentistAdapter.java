package vn.com.user_application.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
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
        String fullName = dentistList.get(position).getFullName();
        String status = dentistList.get(position).isStatus() ? "Ready" : "Not Ready";
        String startDate = dentistList.get(position).getStartDateTime() != null? simpleDateFormat.format(dentistList.get(position).getStartDateTime()) : "";
        String endDate = dentistList.get(position).getEndDateTime() != null? simpleDateFormat.format(dentistList.get(position).getEndDateTime()) : "";
        holder.tvItemDateStart.setText(String.format("Start date: %s", startDate.replace("T"," ")));
        holder.tvItemDateEnd.setText(String.format("End date: %s", endDate.replace("T"," ")));
        holder.tvItemName.setText(fullName);
        holder.tvItemStatus.setText(String.format("Status: %s", status));
    }

    @Override
    public int getItemCount() {
        return dentistList == null? 0 : dentistList.size();
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