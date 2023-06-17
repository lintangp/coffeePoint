package com.lintang.coffee_point;

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
import androidx.recyclerview.widget.RecyclerView;

public class FragmentHistory extends Fragment {

    private View lhistory;
    private RecyclerView rvHistory;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        this.lhistory = inflater.inflate(R.layout.activity_history, null, false);
        this.rvHistory = this.lhistory.findViewById(R.id.rvHistory);


        return this.lhistory;
    }
}
