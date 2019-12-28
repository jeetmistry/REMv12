package com.example.rem.ui_student.home_student;

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

public class HomeFragmentStudent extends Fragment {

    private HomeViewModelStudent homeViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelStudent =
                ViewModelProviders.of(this).get(HomeViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_home_student, container, false);
        final TextView textView = root.findViewById(R.id.text_home_student);
        homeViewModelStudent.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}