package com.lintang.coffee_point.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.R;

import java.util.List;

public class MenuAdminAdapter extends RecyclerView.Adapter<MenuAdminAdapter.MenuViewHolder> {

    private final List<MenuAdminItem> menuAdminItems;
    private Context context;
    private  Dialog dialog;

    public MenuAdminAdapter(Context context, List<MenuAdminItem> menuAdminItems) {
        this.context = context;
        this.menuAdminItems = menuAdminItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_menu_admin, parent, false);
        return new MenuViewHolder(view);
    }
    public Dialog getDialog(){
        return  dialog;
    }
    public interface OnItemClickListener{
        void onEditClick(int position);
        void onDeleteClick(int position);

    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int position) {
        MenuAdminItem menuItem = menuAdminItems.get(position);
        menuViewHolder.imageMenu.setImageResource(menuItem.getImageResource());
        menuViewHolder.textNamaMakanan.setText(menuItem.getNamaMakanan());
        menuViewHolder.textHargaMakanan.setText(menuItem.getHargaMakanan());
        menuViewHolder.textPenjelasanMakanan.setText(menuItem.getPenjelasanMakanan());
        menuViewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        menuViewHolder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return menuAdminItems.size();
    }


    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMenu;
        TextView textNamaMakanan;
        TextView textHargaMakanan;
        TextView textPenjelasanMakanan;
        Button btn_hapus;
        Button btn_edit;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.imageMenu);
            textNamaMakanan = itemView.findViewById(R.id.textNamaMakanan);
            textHargaMakanan = itemView.findViewById(R.id.textHargaMakanan);
            textPenjelasanMakanan = itemView.findViewById(R.id.textPenjelasanMakanan);
            btn_edit = itemView.findViewById(R.id.buttonEdit);
            btn_hapus = itemView.findViewById(R.id.buttonHapus);
        }
    }
}
