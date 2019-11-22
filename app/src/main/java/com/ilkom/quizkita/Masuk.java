package com.ilkom.quizkita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Masuk extends AppCompatActivity {
    private EditText eEmail, ePwd;
    private FirebaseAuth fireAuth;
    private ProgressBar loading;
    private Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        init();
    }

    private void init() {
        //Get Firebase fireAuth instance
        fireAuth = FirebaseAuth.getInstance();
        if (fireAuth.getCurrentUser() != null) {
            startActivity(new Intent(Masuk.this, Dashboard.class));
            finish();
        }
        eEmail = (EditText) findViewById(R.id.email);
        ePwd = (EditText) findViewById(R.id.katasandi);
        loading = (ProgressBar) findViewById(R.id.loading);
        masuk = (Button) findViewById(R.id.tombolmasuk);
    }


    public void loginFunc(View view) {
        String email = eEmail.getText().toString();
        final String password = ePwd.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        loading.setVisibility(View.VISIBLE);

        //authenticate user
        fireAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Masuk.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the fireAuth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        loading.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                ePwd.setError(getString(R.string.input_error_password_length));
                            } else {
                                Toast.makeText(Masuk.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(Masuk.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    public void intentDaftar(View v) {
        Intent intent = new Intent(this, Daftar.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        finish();
        System.exit(0);
    }
}
