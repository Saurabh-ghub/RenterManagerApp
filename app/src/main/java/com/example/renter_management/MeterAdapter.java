package com.example.renter_management;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeterAdapter extends RecyclerView.Adapter<MeterAdapter.ViewHolder> {
     private List<Meter> meterList;
   private int itemLayout;
    public MeterAdapter( List<Meter> meterList,int itemLayout) {
        this.meterList = meterList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public MeterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeterAdapter.ViewHolder holder, int position) {
        Meter meter = meterList.get(position);
        holder.meterReading.setText("Meter Reading: " +Float.toString(meter.getCurrent_reading()));
        holder.readingDate.setText("Reading Date :"+meter.getReading_date().toString());
        holder.electricCost.setText("Electric Cost in Rupees :"+Float.toString(meter.getCost()));
        holder.containerView.setTag(meter);
    }

    @Override
    public int getItemCount() {
        return meterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView ;
        public TextView meterReading;
        public TextView readingDate;
        public TextView electricCost;
//        public TextView flatDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.meter_row);
            meterReading = itemView.findViewById(R.id.MeterReading);
            electricCost = itemView.findViewById(R.id.electric_cost);
            readingDate = itemView.findViewById(R.id.reading_date);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),MeterReadingInfo.class);
                    Meter meter = (Meter) containerView.getTag();
                    intent.putExtra("id",meter.getId());

                    view.getContext().startActivity(intent);
                }
            });
            containerView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return true;
                }
            });
        }
    }
}
