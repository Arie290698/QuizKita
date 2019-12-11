package com.ilkom.quizkita.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ilkom.quizkita.Adapter.StudiListAdapter;
import com.ilkom.quizkita.Model.StudiModel;
import com.ilkom.quizkita.R;

import java.util.ArrayList;

public class StudiFragment extends Fragment {
    private static final Integer MAX_TWEETS = 5;

    private StudiListAdapter mTweetListAdapter;

    private ListView mTweetsListView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_studi, container, false);

        mTweetListAdapter = new StudiListAdapter(getActivity(),
                R.layout.studi, new ArrayList<StudiModel>());
        mTweetsListView = (ListView) root.findViewById(R.id.list_studi);
        mTweetsListView.setAdapter(mTweetListAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Studi");
        // read tweets
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
        if ( mTweetListAdapter.getCount() >= MAX_TWEETS ) {
            mTweetListAdapter.remove( mTweetListAdapter.getItem( 0 ) );
        }
        mTweetListAdapter.insert(tweet, 0);
    }
}