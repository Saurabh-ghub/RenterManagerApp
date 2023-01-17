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

public class FlatAdapter extends RecyclerView.Adapter<FlatAdapter.ViewHolder> {

   private List<Flat>flatList;
   private int itemLayout;
    public FlatAdapter(List<Flat>flatList,int itemLayout) {
        this.flatList = flatList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public FlatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlatAdapter.ViewHolder holder, int position) {
        Flat flat = flatList.get(position);
        holder.flatID.setText( "Start Date: " + flat.getJoining_date());
        holder.flatNickName.setText("Name: " + flat.getNickName());
        holder.containerView.setTag(flat);
    }

    @Override
    public int getItemCount() {
        return flatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView ;
        public TextView flatID;
        public TextView flatNickName;
//        public TextView flatDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.flat_row);
            flatID = itemView.findViewById(R.id.flat_id);
            flatNickName = itemView.findViewById(R.id.flat_nickname);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Flat_MainPage.class);
                    Flat flat = (Flat) containerView.getTag();
                    intent.putExtra("id",flat.getFlat_id());
                    intent.putExtra("nickname",flat.getNickName());
                    intent.putExtra("rent",flat.getFlat_rent());
                    intent.putExtra("electricrate",flat.getElectricity_rate());
                    intent.putExtra("joindate",flat.getJoining_date());
                    intent.putExtra("numrooms",flat.getNo_of_rooms());
                    intent.putExtra("initialmeterReading",flat.getIntial_meter_reading());
                    intent.putExtra("advance",flat.getAdvance());

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
