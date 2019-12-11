package com.ilkom.quizkita.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilkom.quizkita.Masuk;
import com.ilkom.quizkita.Model.UserModel;
import com.ilkom.quizkita.R;

public class ProfileFragment extends Fragment {
    Intent intent;
    TextView namanya, emailnya, jknya, alamatnya, hpnya;
    EditText enamanya, ehp, ealamat;
    RadioButton pria, wanita;
    String ejk;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        namanya = (TextView) root.findViewById(R.id.tNama_Lengkap);
        emailnya = (TextView) root.findViewById(R.id.tEmail);
        hpnya = (TextView) root.findViewById(R.id.tNoHP);
        jknya = (TextView) root.findViewById(R.id.tJenis_Kelamin);
        alamatnya = (TextView) root.findViewById(R.id.tAlamat);

        enamanya = (EditText) root.findViewById(R.id.eNama);
        ehp = (EditText) root.findViewById(R.id.eHP);
        ealamat = (EditText) root.findViewById(R.id.eAlamat);
        pria = (RadioButton) root.findViewById(R.id.pria);
        wanita = (RadioButton) root.findViewById(R.id.wanita);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Pengguna/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel post = dataSnapshot.getValue(UserModel.class);
                namanya.setText(post.fname);
                emailnya.setText(post.email);
                jknya.setText(post.jk);
                hpnya.setText(post.hp);
                alamatnya.setText(post.alamat);

                enamanya.setText(post.fname);
                ehp.setText(post.hp);
                ealamat.setText(post.alamat);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        final Button simpan = (Button) root.findViewById(R.id.btSave);
        simpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(wanita.isChecked()){
                    ejk = wanita.getText().toString();
                }else if (pria.isChecked()){
                    ejk = pria.getText().toString();
                }
                writeNewUser(enamanya.getText().toString(), ehp.getText().toString(), ejk, ealamat.getText().toString());
                LinearLayout elo = (LinearLayout) root.findViewById(R.id.elo);
                elo.setVisibility(View.GONE);

                LinearLayout vlo = (LinearLayout) root.findViewById(R.id.vlo);
                vlo.setVisibility(View.VISIBLE);
            }
        });

        final Button btl = (Button) root.findViewById(R.id.btBatal);
        btl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout elo = (LinearLayout) root.findViewById(R.id.elo);
                elo.setVisibility(View.GONE);

                LinearLayout vlo = (LinearLayout) root.findViewById(R.id.vlo);
                vlo.setVisibility(View.VISIBLE);
            }
        });

        final Button update = (Button) root.findViewById(R.id.btUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout vlo = (LinearLayout) root.findViewById(R.id.vlo);
                vlo.setVisibility(View.GONE);

                LinearLayout elo = (LinearLayout) root.findViewById(R.id.elo);
                elo.setVisibility(View.VISIBLE);
            }
        });

        final Button indo = (Button) root.findViewById(R.id.tombol_keluar);
        indo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(getActivity(), Masuk.class);
                startActivity(intent);

            }
        });
        return root;
    }

    private void writeNewUser(String enama, String ehape, String ejeka, String ealam) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fnm = database.getReference("Pengguna/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +"/fname");
        fnm.setValue(enama);
        DatabaseReference hape = database.getReference("Pengguna/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +"/hp");
        hape.setValue(ehape);
        DatabaseReference jeka = database.getReference("Pengguna/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +"/jk");
        jeka.setValue(ejeka);
        DatabaseReference alam = database.getReference("Pengguna/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +"/alamat");
        alam.setValue(ealam);
    }

}
