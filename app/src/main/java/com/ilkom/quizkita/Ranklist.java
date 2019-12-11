package com.ilkom.quizkita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilkom.quizkita.Adapter.RankListAdapter;
import com.ilkom.quizkita.Model.RankModel;

import java.util.ArrayList;

public class Ranklist extends AppCompatActivity {
    private static final Integer MAX_TWEETS = 5;
    String kode,nama;
    Intent intent;
    private RankListAdapter rla;
    private ListView lView;
    private ListView mTweetsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranklist);

        rla = new RankListAdapter(this,
                R.layout.rank, new ArrayList<RankModel>());
        lView = (ListView) findViewById(R.id.rank_list);
        lView.setAdapter(rla);

        intent = getIntent();
        kode = intent.getStringExtra("kode");
        nama = intent.getStringExtra("nama");
        TextView rl = findViewById(R.id.tvrl);
        rl.setText("Ranking " +nama);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Rank/"+ kode);

        myRef.orderByChild("Nilai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot tweetSnapshot : snapshot.getChildren()) {
                    RankModel tweet = tweetSnapshot.getValue(RankModel.class);
                    updateTweetList(tweet);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    private void updateTweetList( RankModel tweet ) {
        if ( rla.getCount() >= MAX_TWEETS ) {
            rla.remove( rla.getItem( 0 ) );
        }
        rla.insert(tweet, 0);
    }
    public void onBackPressed(){
        finish();
    }
}
