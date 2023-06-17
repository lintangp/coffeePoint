package com.lintang.coffee_point;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.HistoryTransaction;
import com.lintang.coffee_point.adapter.HistoryAdapter;

import java.util.ArrayList;

public class History extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private ArrayList<HistoryTransaction> historyList = new ArrayList<>();
    private com.lintang.coffee_point.adapter.HistoryAdapter HistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.rvHistory);
        recyclerView.setHasFixedSize(true);

        historyList.add(new HistoryTransaction("Order",
                "2 June 2023", "16:10", "32000"));
        historyList.add(new HistoryTransaction("Order",
                "4 June 2023", "10:32", "64000"));
        historyList.add(new HistoryTransaction("Order",
                "5 June 2023", "18:44", "45000"));
        historyList.add(new HistoryTransaction("Order",
                "13 June 2023", "16:58", "80000"));

        HistoryAdapter = new HistoryAdapter(this, historyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(History.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(HistoryAdapter);
    }
}