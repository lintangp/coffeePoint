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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.adapter.MenuAdminAdapter;
import com.lintang.coffee_point.adapter.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class MenuAdmin extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerViewMenuAdmin;
    private MenuAdminAdapter menuAdminAdapter;
    private List<MenuAdminItem> menuAdminItems;
    Button btn_add;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(MenuAdmin.this);
        setContentView(R.layout.activity_menu_admin);
        recyclerViewMenuAdmin = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenuAdmin.setLayoutManager(new LinearLayoutManager(this));
        menuAdminItems = new ArrayList<>();
        menuAdminAdapter = new MenuAdminAdapter(getApplicationContext(), menuAdminItems, this);
        recyclerViewMenuAdmin.setAdapter(menuAdminAdapter);
        btn_add = findViewById(R.id.btn_add);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        showData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdmin.this, AddItem.class);
                startActivity(intent);
            }
        });
    }

    public void showData(){
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.show();
        db.collection("restaurant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        menuAdminItems.clear();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                MenuAdminItem menuAdmin = new MenuAdminItem(document.getId(), document.getString("name"), document.getString("harga"), document.getString("desc"),document.getString("gambar"));
                                menuAdminItems.add(menuAdmin);
                            }
                            menuAdminAdapter.notifyDataSetChanged();

                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(int position) {

    }

    public void onDelete(int position){
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menghapus data...");
        progressDialog.show();
        db.collection("restaurant").document(menuAdminItems.get(position).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        showData();
                        Toast.makeText(getApplicationContext(), "Data berhasil di hapus", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Data gagal di hapus", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onEdit(int position) {
        MenuAdminItem menu = menuAdminItems.get(position);
        Intent intent = new Intent(MenuAdmin.this, UpdateMenu.class);
        intent.putExtra("docId", menu.getId());
        intent.putExtra("name", menu.getNamaMakanan());
        intent.putExtra("harga", menu.getHargaMakanan());
        intent.putExtra("desc", menu.getPenjelasanMakanan());
        intent.putExtra("gambar", menu.getImageResource());
        startActivity(intent);

        //progressDialog.setTitle("Loading");
//        progressDialog.setMessage("Mengupdate data...");
//        progressDialog.show();
//        db.collection("restaurant").document(menuAdminItems.get(position).getDocId()).
//        update(<>)
//        .addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        menuAdminItems.clear();
//        if(task.isSuccessful()){
//        for (QueryDocumentSnapshot document : task.getResult()){
//        MenuAdminItem menuAdmin = new MenuAdminItem(document.getId(), document.getString("name"), document.getString("harga"), document.getString("desc"),document.getString("gambar"));
//        menuAdminItems.(menuAdmin);
//        }
//        menuAdminAdapter.notifyDataSetChanged();
//
//        }else{
//        Toast.makeText(getApplicationContext(), "Data gagal di update", Toast.LENGTH_SHORT).show();
//        }
//        progressDialog.dismiss();
//        }
//        })
//        .addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception e) {
//        progressDialog.dismiss();
//        Toast.makeText(getApplicationContext(), "Data gagal di hapus", Toast.LENGTH_SHORT).show();
//        }
//        });
    }
}

