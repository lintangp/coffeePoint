package com.lintang.coffee_point.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import com.lintang.coffee_point.Model.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private List<MenuItem> menuItems;

    public MenuAdapter(Context context, List<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_menu, parent, false);
        return new MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int position) {
        MenuItem menuItem = menuItems.get(position);
        menuViewHolder.imageMenu.setImageResource(menuItem.getImageResource());
        menuViewHolder.textNamaMakanan.setText(menuItem.getNamaMakanan());
        menuViewHolder.textHargaMakanan.setText(menuItem.getHargaMakanan());
        menuViewHolder.textPenjelasanMakanan.setText(menuItem.getPenjelasanMakanan());
    }


    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMenu;
        TextView textNamaMakanan;
        TextView textHargaMakanan;
        TextView textPenjelasanMakanan;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.imageMenu);
            textNamaMakanan = itemView.findViewById(R.id.textNamaMakanan);
            textHargaMakanan = itemView.findViewById(R.id.textHargaMakanan);
            textPenjelasanMakanan = itemView.findViewById(R.id.textPenjelasanMakanan);
        }
    }
}
