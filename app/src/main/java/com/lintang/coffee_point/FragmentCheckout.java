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

public class FragmentCheckout extends Fragment {

    private View lcheckout;
    private TextView tvCheckout, tvSubtotal, tvTotalHarga;
    private ImageView ivLogo, ivLineAtas, ivLineBawah;
    private Button btnBatal, btnBayar;
    private RecyclerView rvCheckout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.lcheckout = inflater.inflate(R.layout.activity_checkout,
                null,
                false);

        this.ivLogo = this.lcheckout.findViewById(R.id.ivLogo);
        this.ivLineAtas = this.lcheckout.findViewById(R.id.ivLineAtas);
        this.ivLineBawah = this.lcheckout.findViewById(R.id.ivLineBawah);
        this.tvCheckout = this.lcheckout.findViewById(R.id.tvCheckout);
        this.tvSubtotal = this.lcheckout.findViewById(R.id.tvSubtotal);
        this.tvTotalHarga = this.lcheckout.findViewById(R.id.tvTotalHarga);
        this.btnBatal = this.lcheckout.findViewById(R.id.btnBatal);
        this.btnBayar = this.lcheckout.findViewById(R.id.btnBayar);
        this.rvCheckout = this.lcheckout.findViewById(R.id.rvCheckout);


        return this.lcheckout;
    }
}
