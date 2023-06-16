package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity {
    EditText ed_judul;
    ImageView gambar;
    EditText ed_harga;
    EditText ed_desk;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button save;

    CollectionReference restaurantCollection;
    Uri imageUri;
    ProgressDialog progressDialog;
    String id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        db = FirebaseFirestore.getInstance();
        restaurantCollection = db.collection("restaurant");

        ed_judul = findViewById(R.id.editTextName);
        gambar = findViewById(R.id.gambar);
        ed_harga = findViewById(R.id.editPrice);
        ed_desk = findViewById(R.id.editDesc);
        save = findViewById(R.id.save);

        progressDialog = new ProgressDialog(AddItem.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        save.setOnClickListener(v ->{
            if(ed_judul.getText().length()>0&&ed_harga.getText().length()>0&&ed_desk.getText().length()>0){
                saveData(ed_judul.getText().toString(),ed_harga.getText().toString(),ed_desk.getText().toString());
            }
        });


    }
    private  void saveData(String name, String harga, String desc){
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("harga",harga);
        user.put("desc",desc);

        progressDialog.show();
        if(id!=null){
            db.collection("restaurant")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });
        }
    }
}