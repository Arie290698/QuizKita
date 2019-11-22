package com.ilkom.quizkita;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Nilai extends AppCompatActivity {
    TextView nilai;
    Button dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        nilai = (TextView) findViewById(R.id.nilai);
        dashboard = (Button) findViewById(R.id.dashboard);

        setSkor();

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Nilai.this, Dashboard.class);
                startActivity(i);
            }
        });
    }

    public void setSkor(){
        String NA = getIntent().getStringExtra("NA");
        nilai.setText("Nilai : "+NA);

    }

    public void onBackPressed(){
        Intent i = new Intent(Nilai.this, Dashboard.class);
        startActivity(i);
        finish();
    }
}
