package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.adapter.MenuAdminAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuAdmin extends AppCompatActivity {

    private RecyclerView recyclerViewMenuAdmin;
    private MenuAdminAdapter menuAdminAdapter;
    private List<MenuAdminItem> menuAdminItems;
    Button btn_add, btn_edit, btn_hapus;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog = new ProgressDialog(MenuAdmin.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        recyclerViewMenuAdmin = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenuAdmin.setLayoutManager(new LinearLayoutManager(this));
        menuAdminItems = new ArrayList<>();
        menuAdminAdapter = new MenuAdminAdapter(getApplicationContext(), menuAdminItems);
        recyclerViewMenuAdmin.setAdapter(menuAdminAdapter);
        btn_add = findViewById(R.id.btn_add);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.show();
        db.collection("restaurant")
                        .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                MenuAdminItem menuAdmin = new MenuAdminItem(document.getString("name"), document.getString("harga"), document.getString("desc"));
                                                menuAdminItems.add(menuAdmin);
                                            }
                                            menuAdminAdapter.notifyDataSetChanged();

                                        }else{
                                            Toast.makeText(getApplicationContext(), "Data gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdmin.this, AddItem.class);
                startActivity(intent);
            }
        });


        // Mengupdate tampilan RecyclerView
    }
}