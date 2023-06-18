package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lintang.coffee_point.Model.HistoryTransaction;
import com.lintang.coffee_point.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;
    private List<HistoryTransaction> historyItems;
    private View lhistory;
    private ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        lhistory = inflater.inflate(R.layout.activity_history, container, false);

        progressDialog = new ProgressDialog(requireContext());

        rvHistory = lhistory.findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(requireContext()));

        historyItems = new ArrayList<>();
        historyAdapter = new HistoryAdapter(requireContext(), historyItems);
        rvHistory.setAdapter(historyAdapter);

        return lhistory;
    }

    @Override
    public void onStart() {
        super.onStart();
        showData();
    }

    public void showData() {
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.show();
        db.collection("history")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        historyItems.clear();
                        historyItems.add(new HistoryTransaction("Order",
                                "2 June 2023", "16:10", "32000"));
                        historyItems.add(new HistoryTransaction("Order",
                                "4 June 2023", "10:32", "64000"));
                        historyItems.add(new HistoryTransaction("Order",
                                "5 June 2023", "18:44", "45000"));
                        historyItems.add(new HistoryTransaction("Order",
                                "13 June 2023", "16:58", "80000"));

                        historyAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                });
    }
}
