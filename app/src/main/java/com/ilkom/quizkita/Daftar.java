package com.ilkom.quizkita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Daftar extends AppCompatActivity implements View.OnClickListener {
    private EditText eEmail, eFname, ePwd;
    private ProgressBar loading;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        eEmail = findViewById(R.id.email);
        eFname = findViewById(R.id.namalengkap);
        ePwd = findViewById(R.id.katasandi);
        loading = findViewById(R.id.loading);
        loading.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.tomboldaftar).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = eFname.getText().toString().trim();
        final String email = eEmail.getText().toString().trim();
        String password = ePwd.getText().toString().trim();

        if (name.isEmpty()) {
            eFname.setError(getString(R.string.input_error_name));
            eFname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            eEmail.setError(getString(R.string.input_error_email));
            eEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eEmail.setError(getString(R.string.input_error_email_invalid));
            eEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            ePwd.setError(getString(R.string.input_error_password));
            ePwd.requestFocus();
            return;
        }

        if (password.length() < 6) {
            ePwd.setError(getString(R.string.input_error_password_length));
            ePwd.requestFocus();
            return;
        }


        loading.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email
                            );

                            FirebaseDatabase.getInstance().getReference("/Pengguna")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    loading.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Daftar.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(Daftar.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tomboldaftar:
                registerUser();
                break;
        }
    }

    public void intentMasuk(View v) {
        Intent intent = new Intent(this, Masuk.class);
        startActivity(intent);
    }
}
