package com.lintang.coffee_point;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetailMenu extends Fragment {
    View ldetailItem;
    ImageView ivItem;
    ImageButton btBack, btRemove, btAdd, btKeranjang;
    TextView tvHarga, tvJml, tvNamaItem, tvDescItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.ldetailItem = inflater.inflate(R.layout.detail_menu,
                null,
                false);

        this.ivItem = this.ldetailItem.findViewById(R.id.ivItem);
        this.btBack = this.ldetailItem.findViewById(R.id.btBack);
        this.btAdd = this.ldetailItem.findViewById(R.id.btAdd);
        this.btRemove = this.ldetailItem.findViewById(R.id.btremove);
        this.btKeranjang = this.ldetailItem.findViewById(R.id.btKeranjang);
        this.tvHarga= this.ldetailItem.findViewById(R.id.tvHarga);
        this.tvJml = this.ldetailItem.findViewById(R.id.tvJml);
        this.tvNamaItem = this.ldetailItem.findViewById(R.id.tvNamaItem);
        this.tvDescItem = this.ldetailItem.findViewById(R.id.tvDescItem);

        return this.ldetailItem;
    }
}
