package com.example.rem.ui_recruiter.feedback_recruiter;

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

public class FeedbackFragmentRecruiter extends Fragment {

    private FeedbackViewModelRecruiter feedbackViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedbackViewModelRecruiter =
                ViewModelProviders.of(this).get(FeedbackViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_feedback_recruiter, container, false);
        final TextView textView = root.findViewById(R.id.text_feedback_recruiter);
        feedbackViewModelRecruiter.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}