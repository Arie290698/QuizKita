package com.ilkom.quizkita.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilkom.quizkita.Adapter.RankListAdapter;
import com.ilkom.quizkita.Adapter.StudiRankListAdapter;
import com.ilkom.quizkita.Model.StudiModel;
import com.ilkom.quizkita.R;

import java.util.ArrayList;

public class RankFragment extends Fragment {
TextView a,b;
    private static final Integer MAX_TWEETS = 5;
    View root;
    private StudiRankListAdapter srla;
    private RankListAdapter rla;
    private ListView lView;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_rank, container, false);

        srla = new StudiRankListAdapter(getActivity(),
                R.layout.studi, new ArrayList<StudiModel>());
        lView = (ListView) root.findViewById(R.id.list_studi);
        lView.setAdapter(srla);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Studi");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot tweetSnapshot : snapshot.getChildren()) {
                    StudiModel tweet = tweetSnapshot.getValue(StudiModel.class);
                    updateTweetList(tweet);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return root;
    }
    private void updateTweetList( StudiModel tweet ) {
        if ( srla.getCount() >= MAX_TWEETS ) {
            srla.remove( srla.getItem( 0 ) );
        }
        srla.insert(tweet, 0);
    }

}