package com.lintang.coffee_point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateMenu extends AppCompatActivity {

    EditText etName, etDesc, etHarga;
    ImageView gambar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btnSave;

    Uri imageUri;
    ProgressDialog progressDialog;
    String id = "";
    String menuName, menuDesc, menuHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);
        Bundle bundle = getIntent().getExtras();

        etName = findViewById(R.id.etUpdateName);
        gambar = findViewById(R.id.updateGambar);
        etHarga = findViewById(R.id.etUpdatePrice);
        etDesc = findViewById(R.id.etUpdateDesc);
        btnSave = findViewById(R.id.saveUpdate);

        gambar.setOnClickListener(v -> {
            selectedImage();
        });

        if (bundle != null) {
            etName.setText(bundle.getString("name"));
            etDesc.setText(bundle.getString("desc"));
            etHarga.setText(bundle.getString("harga"));
            id = bundle.getString("id");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuName = etName.getText().toString();
                menuDesc = etDesc.getText().toString();
                menuHarga = etHarga.getText().toString();

                update(menuName, menuDesc, menuHarga);
            }
        });
    }

    private void update(String menuName, String menuDesc, String menuHarga) {
        progressDialog = new ProgressDialog(UpdateMenu.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        gambar.setDrawingCacheEnabled(true);
        gambar.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) gambar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("images")
                .child("IMG" + new Date().getTime() + ".jpeg");
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (taskSnapshot.getMetadata() != null) {
                    if (taskSnapshot.getMetadata().getReference() != null) {
                        taskSnapshot.getMetadata().getReference().getDownloadUrl()
                                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    String imageUrl = downloadUri.toString();
                                    saveData(menuName, menuHarga, menuDesc, imageUrl);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectedImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMenu.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo")) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            } else if (items[item].equals("Choose from Library")) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 20);
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK && data != null) {
            final Uri path = data.getData();
            Thread thread = new Thread(() -> {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    gambar.post(() -> {
                        gambar.setImageBitmap(bitmap);
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        if (requestCode == 10 && resultCode == RESULT_OK) {
            final Bundle extras = data.getExtras();
            Thread thread = new Thread(() -> {
                Bitmap bitmap = (Bitmap) extras.get("data");
                gambar.post(() -> {
                    gambar.setImageBitmap(bitmap);
                });
            });
            thread.start();
        }
    }

    private void saveData(String name, String harga, String desc, String gambar) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", name);
        menu.put("harga", harga);
        menu.put("desc", desc);
        menu.put("gambar", gambar);

        if (id != null) {
            db.collection("restaurant")
                    .document(id)
                    .set(menu)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),
                                    "Berhasil mengubah data", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Gagal mengubah data", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Data tidak valid", Toast.LENGTH_SHORT).show();
        }
    }
}