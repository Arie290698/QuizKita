package com.ilkom.quizkita;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Quiz extends AppCompatActivity {
    TextView tvSoal;
    Button A,B,C,D;
    String jawaban;
    int i=1;
    int benar=0;
    String kode,namae;
    Intent intent;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Pengguna/" + FirebaseAuth.getInstance().getCurrentUser().getUid() +"/fname");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post = dataSnapshot.getValue(String.class);
                namae=post;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        intent = getIntent();
        kode = intent.getStringExtra("kode");
        total = intent.getIntExtra("total", 0);
        tvSoal = (TextView) findViewById(R.id.tvSoal);
        A = (Button) findViewById(R.id.btA);
        B = (Button) findViewById(R.id.btB);
        C = (Button) findViewById(R.id.btC);
        D = (Button) findViewById(R.id.btD);


        setSoal();


        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(A.getText().equals(jawaban)){
                            benar = benar + 1;
                            i = i+1;
                            setSoal();
                        }else{
                            i = i+1;
                            setSoal();
                        }
                    }
                },1500);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(B.getText().equals(jawaban)){
                            benar = benar + 1;
                            i = i+1;
                            setSoal();
                        }else{
                            i = i+1;
                            setSoal();
                        }
                    }
                },1500);
            }
        });


        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if(C.getText().equals(jawaban)){
                            benar = benar + 1;
                            i = i+1;
                            setSoal();
                        }else{
                            i = i+1;
                            setSoal();
                        }

            }
        });


        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(D.getText().equals(jawaban)){
                            benar = benar + 1;
                            i = i+1;
                            setSoal();
                        }else{
                            i = i+1;
                            setSoal();
                        }
                    }
                },1500);
            }
        });

    }

    private void setSoal() {
        if(i > total){
            Intent g = new Intent(this, Nilai.class);
            g.putExtra("NA", benar);
            g.putExtra("total", total);
            g.putExtra("kode", kode);
            g.putExtra("namae", namae);
            startActivity(g);
            finish();
        }else {
            TextView page = findViewById(R.id.pagination);
            page.setText("Soal "+ i + "/" + total);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("Soal/" + kode + "/" + i + "/Pertanyaan");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String soal = dataSnapshot.getValue(String.class);
                    tvSoal.setText(soal);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            DatabaseReference a = database.getReference("Soal/" + kode + "/" + i + "/A");

            a.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);
                    A.setText(a);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            DatabaseReference b = database.getReference("Soal/" + kode + "/" + i + "/B");

            b.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String b = dataSnapshot.getValue(String.class);
                    B.setText(b);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            DatabaseReference c = database.getReference("Soal/" + kode + "/" + i + "/C");

            c.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String c = dataSnapshot.getValue(String.class);
                    C.setText(c);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            DatabaseReference d = database.getReference("Soal/" + kode + "/" + i + "/D");

            d.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String d = dataSnapshot.getValue(String.class);
                    D.setText(d);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            DatabaseReference jwb = database.getReference("Soal/" + kode + "/" + i + "/Benar");

            jwb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String jwba = dataSnapshot.getValue(String.class);
                    jawaban = jwba;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }
    public void onBackPressed(){
        finish();
    }
}
