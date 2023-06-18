package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.HistoryTransaction;
import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.adapter.HistoryAdapter;
import com.lintang.coffee_point.adapter.MenuAdminAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;
    private List<HistoryTransaction> historyItems;
    private View lhistory;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        lhistory = inflater.inflate(R.layout.activity_history, null, false);

        progressDialog = new ProgressDialog(requireContext());

        rvHistory = lhistory.findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
        historyItems = new ArrayList<>();
        historyAdapter = new HistoryAdapter(requireContext(), historyItems);
        rvHistory.setAdapter(historyAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

        return this.lhistory;
    }
}
