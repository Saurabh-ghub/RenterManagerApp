package com.example.renter_management;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class RenterAdapter extends RecyclerView.Adapter<RenterAdapter.ViewHolder> {

    private List<Renter>renterList;
   private int itemLayout;
    public RenterAdapter(List<Renter>renterList,int itemLayout) {
        this.renterList = renterList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public RenterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RenterAdapter.ViewHolder holder, int position) {
        Renter renter= renterList.get(position);
        holder.renterName.setText(renter.getRenter_name());
        holder.renterPhone.setText(renter.getPhone());
        holder.containerView.setTag(renter);
    }

    @Override
    public int getItemCount() {
        return renterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView ;
        public TextView renterName;
        public TextView renterPhone;
//        public TextView flatDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.renter_row);
            renterName = itemView.findViewById(R.id.Renter_name);
            renterPhone = itemView.findViewById(R.id.renter_phone);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Rneter_info.class);
                    Renter renter = (Renter) containerView.getTag();
                    Log.d("Renter_detail",renter.toString());
                    intent.putExtra("id",renter.getRenter_id());

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
