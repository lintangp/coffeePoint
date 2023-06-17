package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.InputStream;
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

        gambar.setOnClickListener(v->{
            selectedImage();
        });

        progressDialog = new ProgressDialog(AddItem.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        save.setOnClickListener(v ->{
            if(ed_judul.getText().length()>0&&ed_harga.getText().length()>0&&ed_desk.getText().length()>0){
                saveData(ed_judul.getText().toString(),ed_harga.getText().toString(),ed_desk.getText().toString());
            }
        });
    }
    private void selectedImage(){
        final CharSequence[] items = {"Take Photo", "Choose from Library","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItem.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items,(dialog,item)->{
            if(items[item].equals("Take Photo")){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }else if(items[item].equals("Choose from Library")){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }else if(items[item].equals("Cancel")){
               dialog.dismiss();
            }
        });
        builder.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 20 && resultCode == RESULT_OK & data != null){
            final  Uri path = data.getData();
            Thread thread = new Thread(()->{
                try{
                    InputStream inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    gambar.post(()->{
                        gambar.setImageBitmap(bitmap);
                    });
                } catch (FileNotFoundException e) {
                   e.printStackTrace();
                }
            });
            thread.start();
        }
        if(requestCode == 10 && resultCode == RESULT_OK){
            final Bundle extras = data.getExtras();
            Thread thread = new Thread(()->{
                Bitmap bitmap = (Bitmap) extras.get("data");
                gambar.post(()->{
                    gambar.setImageBitmap(bitmap);
                });
            });
            thread.start();
        }

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