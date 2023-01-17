package com.example.renter_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonthlyPaymentAdapter extends RecyclerView.Adapter<MonthlyPaymentAdapter.ViewHolder> {
     private List<MonthlyRent> monthlyRentList;
   private int itemLayout;
    public MonthlyPaymentAdapter(List<MonthlyRent> monthlyRentList,int itemLayout) {
        this.monthlyRentList = monthlyRentList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public MonthlyPaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyPaymentAdapter.ViewHolder holder, int position) {
        MonthlyRent monthlyRent = monthlyRentList.get(position);
        holder.month.setText("Month Duration: " + monthlyRent.getMonth().split("_")[0] + " to " + monthlyRent.getMonth().split("_")[1]);
        holder.ReceiveAmt.setText("Recieved Payment: " + Integer.toString(monthlyRent.getRecvAmount()));
        holder.DueAmt.setText("Due Payment for month: " + Integer.toString(monthlyRent.getDueAmount()));
        holder.paidOn.setText("Paid On " + monthlyRent.getPaidDate());
       // holder.containerView.setTag(flat);
    }

    @Override
    public int getItemCount() {
        return monthlyRentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView ;
        public TextView month;
        public TextView ReceiveAmt;
        public TextView DueAmt;
        public TextView paidOn;
//        public TextView flatDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.monthlypayment_row);
            month = itemView.findViewById(R.id.month_name);
            ReceiveAmt = itemView.findViewById(R.id.RecievedAmount);
            DueAmt = itemView.findViewById(R.id.DueAmount);
            paidOn = itemView.findViewById(R.id.completerPaidOn);

            containerView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return true;
                }
            });
        }
    }
}
