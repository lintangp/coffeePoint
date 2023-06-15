package com.lintang.coffee_point;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.adapter.MenuAdminAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuAdmin extends AppCompatActivity {

    private RecyclerView recyclerViewMenuAdmin;
    private MenuAdminAdapter menuAdminAdapter;
    private List<MenuAdminItem> menuAdminItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        recyclerViewMenuAdmin = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenuAdmin.setLayoutManager(new LinearLayoutManager(this));
        menuAdminItems = new ArrayList<>();
        menuAdminAdapter = new MenuAdminAdapter(this, menuAdminItems);
        recyclerViewMenuAdmin.setAdapter(menuAdminAdapter);
        menuAdminItems.add(new MenuAdminItem(R.drawable.gambar_menu1, "Java Coffe", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuAdminItems.add(new MenuAdminItem(R.drawable.gambar_menu2, "Red-Halvelt", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuAdminItems.add(new MenuAdminItem(R.drawable.gambar_menu3, "MAC-chiato", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));

        // Mengupdate tampilan RecyclerView
        menuAdminAdapter.notifyDataSetChanged();
    }
}