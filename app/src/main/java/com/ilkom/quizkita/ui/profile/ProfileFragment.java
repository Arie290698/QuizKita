package com.ilkom.quizkita.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.ilkom.quizkita.Masuk;
import com.ilkom.quizkita.Quiz;
import com.ilkom.quizkita.R;

public class ProfileFragment extends Fragment {
    Intent intent;
    private ProfileViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

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

}