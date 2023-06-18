package com.lintang.coffee_point.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.R;
import com.lintang.coffee_point.UpdateMenu;

import java.util.List;

public class MenuAdminAdapter extends RecyclerView.Adapter<MenuAdminAdapter.MenuViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final List<MenuAdminItem> menuAdminItems;
    private Context context;
    private  Dialog dialog;

    public MenuAdminAdapter(
            Context context, List<MenuAdminItem> menuAdminItems,
            RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.menuAdminItems = menuAdminItems;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(this.context)
                .inflate(R.layout.recycler_menu_admin, parent, false), recyclerViewInterface
        );
    }
    public Dialog getDialog(){
        return  dialog;
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int position) {
        MenuAdminItem menuItem = menuAdminItems.get(position);
        Glide.with(context).load(menuItem.getImageResource()).into(menuViewHolder.imageMenu);
        menuViewHolder.textNamaMakanan.setText(menuItem.getNamaMakanan());
        menuViewHolder.textHargaMakanan.setText(menuItem.getHargaMakanan());
        menuViewHolder.textPenjelasanMakanan.setText(menuItem.getPenjelasanMakanan());

    }


    @Override
    public int getItemCount() {
        return menuAdminItems.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMenu;
        TextView textNamaMakanan;
        TextView textHargaMakanan;
        TextView textPenjelasanMakanan;
        Button btn_hapus;
        Button btn_edit;

        public MenuViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.imageMenu);
            textNamaMakanan = itemView.findViewById(R.id.textNamaMakanan);
            textHargaMakanan = itemView.findViewById(R.id.textHargaMakanan);
            textPenjelasanMakanan = itemView.findViewById(R.id.textPenjelasanMakanan);
            btn_edit = itemView.findViewById(R.id.buttonEdit);
            btn_hapus = itemView.findViewById(R.id.buttonHapus);

            this.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MenuAdminItem menuItem = menuAdminItems.get(position);

                        // Buka activity UpdateMenu untuk mengedit data dengan menggunakan Intent
                        Intent intent = new Intent(context, UpdateMenu.class);
                        intent.putExtra("id", menuItem.getId());
                        intent.putExtra("name", menuItem.getNamaMakanan());
                        intent.putExtra("harga", menuItem.getHargaMakanan());
                        intent.putExtra("desc", menuItem.getPenjelasanMakanan());
                        intent.putExtra("gambar", menuItem.getImageResource());
                        context.startActivity(intent);
                    }
                }
            });

            this.btn_hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onDelete(pos);
                        }
                    }
                }
            });
        }
    }
}
