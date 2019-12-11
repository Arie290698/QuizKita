package com.ilkom.quizkita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Nilai extends AppCompatActivity {
    TextView nilai;
    Button dashboard;
    TextView fname;
    String namae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        nilai = (TextView) findViewById(R.id.nilai);
        dashboard = (Button) findViewById(R.id.dashboard);



        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Nilai.this, Dashboard.class);
                startActivity(i);
            }
        });

        fname = (TextView) findViewById(R.id.fname_nilai);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Pengguna/" + FirebaseAuth.getInstance().getCurrentUser().getUid() +"/fname");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post = dataSnapshot.getValue(String.class);
                fname.setText(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        setSkor();
    }

    public void setSkor(){
        int NA = getIntent().getIntExtra("NA", 0);
        int total = getIntent().getIntExtra("total", 0);
        String kode = getIntent().getStringExtra("kode");
        String namae = getIntent().getStringExtra("namae");
        int ni = (NA*100)/total;
        nilai.setText("Nilai : "+ ni +"/100");
        String nilae = String.valueOf(ni);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference fnm = db.getReference("Rank/"+ kode + "/" +FirebaseAuth.getInstance().getCurrentUser().getUid() +"/Nama");
        fnm.setValue(namae);
        DatabaseReference nilay = db.getReference("Rank/"+ kode + "/" +FirebaseAuth.getInstance().getCurrentUser().getUid() +"/Nilai");
        nilay.setValue(nilae);
    }

    public void onBackPressed(){
        Intent i = new Intent(Nilai.this, Dashboard.class);
        startActivity(i);
        finish();
    }
}
