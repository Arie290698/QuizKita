package com.ilkom.quizkita.ui.studi;

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

import com.ilkom.quizkita.Quiz;
import com.ilkom.quizkita.R;

public class StudiFragment extends Fragment {
    Intent intent;
    private StudiViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(StudiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_studi, container, false);

        final Button indo = (Button) root.findViewById(R.id.indo);
        indo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(getActivity(), Quiz.class);
                startActivity(intent);
            }
        });

        return root;

    }


}