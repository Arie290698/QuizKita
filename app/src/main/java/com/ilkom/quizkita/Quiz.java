package com.ilkom.quizkita;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
    TextView soal;
    RadioGroup Jawaban;
    RadioButton A, B, C, D;
    Button next;
    int skor=0;
    int arr;
    int x;
    String jawaban;

    Soal soalPG = new Soal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        soal = (TextView) findViewById(R.id.soal);
        Jawaban = (RadioGroup) findViewById(R.id.Jawaban);
        A = (RadioButton) findViewById(R.id.A);
        B = (RadioButton) findViewById(R.id.B);
        C = (RadioButton) findViewById(R.id.C);
        D = (RadioButton) findViewById(R.id.D);
        next = (Button) findViewById(R.id.next);

        setKonten();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban();
            }
        });
    }

    public void setKonten(){
        Jawaban.clearCheck();
        arr = soalPG.pertanyaan.length;
        if(x >= arr){
            String jumlahSkor = String.valueOf(skor);
            Intent i = new Intent(Quiz.this, Nilai.class);
            i.putExtra("NA",jumlahSkor);
            startActivity(i);
            finish();
        }else{
            soal.setText(soalPG.getPertanyaan(x));
            A.setText(soalPG.getA(x));
            B.setText(soalPG.getB(x));
            C.setText(soalPG.getC(x));
            D.setText(soalPG.getD(x));
            jawaban = soalPG.getJawabanBenar(x);

        }
        x++;
    }

    public void cekJawaban(){
        if(A.isChecked()){
            if(A.getText().toString().equals(jawaban)){
                skor = skor + 10;
                setKonten();
            }else{
                setKonten();
            }
        }else if(B.isChecked()){
            if(B.getText().toString().equals(jawaban)){
                skor = skor + 10;
                setKonten();
            }else{
                setKonten();
            }
        }else if(C.isChecked()){
            if(C.getText().toString().equals(jawaban)){
                skor = skor + 10;
                setKonten();
            }else{
                setKonten();
            }
        }else if(D.isChecked()){
            if(D.getText().toString().equals(jawaban)){
                skor = skor + 10;
                setKonten();
            }else{
                setKonten();
            }
        }else{
            Toast.makeText(this, "Pilih Jawaban Terlebih Dahulu!1!1!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed(){
        Intent i = new Intent(Quiz.this, Dashboard.class);
        startActivity(i);
        finish();
    }
}
