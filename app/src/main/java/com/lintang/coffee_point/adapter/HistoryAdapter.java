package com.lintang.coffee_point.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.HistoryTransaction;
import com.lintang.coffee_point.R;

import java.util.ArrayList;
import java.util.List;

//public class HistoryAdapter extends RecyclerView.Adapter {
//
//    private Context ctx;
//    private List<History> histories;
//
//    //digunakan untuk memasukkan context dan dataset ke dalam adapter
//    public HistoryAdapter(Context ctx, List<History> histories){
//        this.ctx = ctx;
//        this.histories = histories;
//    }
//
//    //class ViewHolder custom untuk Contact
//    class VHContact extends RecyclerView.ViewHolder {
//
//        public TextView tvPayment;
//        public TextView tvDatetime;
//        public TextView tvPayment;
//
//        public VHContact(@NonNull View rowView) {
//            super(rowView);
//            this.tvPayment = rowView.findViewById(R.id.tvPayment);
//            this.tvDatetime = rowView.findViewById(R.id.tvDatetime);
//            this.tvPayment = rowView.findViewById(R.id.tvPayment);
//        }
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new VHContact(
//                LayoutInflater.from(this.ctx).
//                        inflate(R.layout.history_item, parent, false)
//        );
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        History c = this.contactsFilter.get(position);
//        VHContact vh = (VHContact) holder;
//        vh.tvPayment.setText(c.initial());
//        vh.tvDatetime.setText(c.name);
//        vh.tvEmail.setText(c.email);
//        vh.tvPayment.setText(c.phone);
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.contactsFilter.size();
//    }

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ContactViewHolder>
{
    private Context context;
    private List<HistoryTransaction> HistoryList;
    public HistoryAdapter(Context context, ArrayList<HistoryTransaction> HistoryList){
        this.context = context;
        this.HistoryList = HistoryList;
    }

    @NonNull
    @Override
    public ContactViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final HistoryTransaction history = HistoryList.get(position);
        holder.tvTransaction.setText(history.getTransaction());
        holder.tvDatetime.setText(history.getDate() + " " + history.getTime());
        holder.tvPayment.setText("Rp" + history.getPayment());
    }

    @Override
    public int getItemCount() {
        return HistoryList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTransaction, tvDatetime, tvPayment;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransaction = itemView.findViewById(R.id.tvTransaction);
            tvDatetime = itemView.findViewById(R.id.tvDatetime);
            tvPayment = itemView.findViewById(R.id.tvPayment);
            }
        }
}
