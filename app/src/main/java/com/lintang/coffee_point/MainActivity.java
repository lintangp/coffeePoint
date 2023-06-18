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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fm;
    Fragment detailMenu, checkout, history, info, MenuAdmin;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Button button = findViewById(R.id.button);
        button.setOnClickListener(this);*/

        this.bottomNavigationView = this.findViewById(R.id.bottomNavigationView);

        this.fm = getSupportFragmentManager();
        this.detailMenu = new FragmentDetailMenu();
        this.checkout = new FragmentCheckout();
        this.history = new FragmentHistory();
        this.info = new FragmentInfo();
        this.MenuAdmin = new FragmentMenuAdmin();

        // sementara, seharusnya diganti home/dashboard
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, MenuAdmin).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_beranda:
                        // menampilkan home/dashboard
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, MenuAdmin).commit();
                        return true;

                    case R.id.menu_pesanan:
                        // menampilkan pesanan
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, checkout).commit();
                        return true;

                    case R.id.menu_riwayat:
                        // menampilkan history
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, history).commit();
                        return true;

                    case R.id.menu_tentang:
                        // menampilkan info restoran
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, info).commit();
                        return true;

                }
                return true;
            }
        });


    }


    @Override
    public void onClick(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // contoh ngakases data dari firestore
        db.collection("restaurant").document("info").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String info = documentSnapshot.getString("text");
                            // Display the data in the log
                            Log.d("Firestore", "Info: " + info);
                        } else {
                            Log.d("Firestore", "No such document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error getting document", e);
                    }
                });
    }
}