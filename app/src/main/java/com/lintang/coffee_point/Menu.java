package com.lintang.coffee_point;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lintang.coffee_point.adapter.MenuAdapter;
import com.lintang.coffee_point.Model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private RecyclerView recyclerViewMenu;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(this, menuItems);
        recyclerViewMenu.setAdapter(menuAdapter);
        menuItems.add(new MenuItem(R.drawable.gambar_menu1, "Java Coffe", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuItems.add(new MenuItem(R.drawable.gambar_menu2, "Red-Halvelt", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuItems.add(new MenuItem(R.drawable.gambar_menu3, "MAC-chiato", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuItems.add(new MenuItem(R.drawable.gambar_menu3, "MAC-chiato", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));
        menuItems.add(new MenuItem(R.drawable.gambar_menu3, "MAC-chiato", "Rp 16.000", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a ex aliquam nisi pulvinar aliquet"));

        // Mengupdate tampilan RecyclerView
        menuAdapter.notifyDataSetChanged();
    }
}