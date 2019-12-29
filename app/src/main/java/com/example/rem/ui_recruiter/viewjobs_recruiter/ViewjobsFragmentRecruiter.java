package com.example.rem.ui_recruiter.viewjobs_recruiter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;

public class ViewjobsFragmentRecruiter extends Fragment {

    private ViewjobsViewModelRecruiter viewjobsViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewjobsViewModelRecruiter =
                ViewModelProviders.of(this).get(ViewjobsViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_viewjobs_recruiter, container, false);
        final TextView textView = root.findViewById(R.id.text_viewjobs_recruiter);
        viewjobsViewModelRecruiter.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}