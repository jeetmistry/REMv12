package com.example.rem.ui_student.help_student;

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

public class HelpFragmentStudent extends Fragment {

    private HelpViewModelStudent helpViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpViewModelStudent =
                ViewModelProviders.of(this).get(HelpViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_help_student, container, false);
        final TextView textView = root.findViewById(R.id.text_help_student);
        helpViewModelStudent.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}