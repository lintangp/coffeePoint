package com.lintang.coffee_point;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvRegister, tvNamaDepan, tvNamaBelakang, tvUsername, tvEmail, tvNoHp, tvKataSandi, tvLoginBold, tvLogintv;
    private EditText etNamaDepan, etNamaBelakang, etUsername, etEmail, etNoHp, etKataSandi;
    private Button btnRegis;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvRegister = findViewById(R.id.tvRegister);
        tvNamaDepan = findViewById(R.id.tvNamaDepan);
        etNamaDepan = findViewById(R.id.etNamaDepan);
        tvNamaBelakang = findViewById(R.id.tvNamaBelakang);
        etNamaBelakang = findViewById(R.id.etNamaBelakang);
        tvUsername = findViewById(R.id.tvUsername);
        etUsername = findViewById(R.id.etUsername);
        tvEmail = findViewById(R.id.tvEmail);
        etEmail = findViewById(R.id.etEmail);
        tvNoHp = findViewById(R.id.tvNoHp);
        etNoHp = findViewById(R.id.etNoHp);
        tvKataSandi = findViewById(R.id.tvKataSandi);
        etKataSandi = findViewById(R.id.etKataSandi);
        btnRegis = findViewById(R.id.btnRegis);

        tvLoginBold = findViewById(R.id.LoginBold);
        tvLogintv = findViewById(R.id.Logintv);

        btnRegis.setOnClickListener(this);
        tvLoginBold.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String namaDepan = etNamaDepan.getText().toString();
        String namaBelakang = etNamaBelakang.getText().toString();
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String nohp = etNoHp.getText().toString();
        String password = etKataSandi.getText().toString();

        switch (view.getId()) {
            case R.id.btnRegis:

                if (namaDepan.isEmpty() || namaBelakang.isEmpty() || username.isEmpty() || email.isEmpty() || nohp.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    if (password.length() < 6)
                        Toast.makeText(RegisterActivity.this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    UserID = mAuth.getCurrentUser().getUid();

                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Nama Depan", namaDepan);
                                    user.put("Nama Belakang", namaBelakang);
                                    user.put("Username", username);
                                    user.put("Email", email);
                                    user.put("No HP", nohp);
                                    user.put("Password", password);

                                    db.collection("User")
                                            .document(UserID).set(user)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Log.e("Create User", "Data User berhasil disimpan " + UserID);
                                                        }
                                                    });

                                    Intent i = new Intent(RegisterActivity.this, Login.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Create account failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.LoginBold:
                Intent i = new Intent(RegisterActivity.this, Login.class);
                startActivity(i);
                break;
        }


    }
}