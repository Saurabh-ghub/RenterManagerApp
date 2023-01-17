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

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
      private List<Payment> paymentList;
   private int itemLayout;
    public PaymentAdapter(List<Payment> paymentList,int itemLayout) {
        this.paymentList = paymentList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        Payment payment = paymentList.get(position);
        holder.paymentAmt.setText( " Total Payment: Rs." + Integer.toString(payment.getPaid_amount()));
        holder.paymentDate.setText("Payment Date: " + payment.getPayment_date());
        holder.containerView.setTag(payment);
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView paymentAmt;
        public TextView paymentDate;

        //        public TextView flatDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.payment_row);
            paymentAmt = itemView.findViewById(R.id.PaymentAmt);
            paymentDate = itemView.findViewById(R.id.pay_date);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PaymentInfo.class);
                    Payment payment = (Payment) containerView.getTag();
                    intent.putExtra("PaymentId", payment.getId());


                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
