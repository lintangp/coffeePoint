package com.lintang.coffee_point;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity{
    FragmentManager fm;
    Fragment info, MenuAdmin;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bottomNavigationView = this.findViewById(R.id.bottomNavigationView);
        this.fm = getSupportFragmentManager();
        this.info = new FragmentInfo();
        this.MenuAdmin = new FragmentMenuAdmin();

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, MenuAdmin).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_beranda:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, MenuAdmin).commit();
                        return true;

                    case R.id.menu_tentang:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, info).commit();
                        return true;

                }
                return true;
            }
        });
    }
}