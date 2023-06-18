package com.lintang.coffee_point.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.HistoryTransaction;
import com.lintang.coffee_point.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ContactViewHolder> {
    private Context context;
    private List<HistoryTransaction> historyList;

    public HistoryAdapter(Context context, List<HistoryTransaction> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        HistoryTransaction history = historyList.get(position);
        holder.tvTransaction.setText(history.getTransaction());
        holder.tvDatetime.setText(history.getDate() + " " + history.getTime());
        holder.tvPayment.setText("Rp" + history.getPayment());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvTransaction, tvDatetime, tvPayment;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransaction = itemView.findViewById(R.id.tvTransaction);
            tvDatetime = itemView.findViewById(R.id.tvDatetime);
            tvPayment = itemView.findViewById(R.id.tvPayment);
        }
    }
}
