package com.example.rem.ui_student.logout_student;

import android.content.Intent;
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

import com.example.rem.LoginOption;
import com.example.rem.R;

public class LogoutFragmentStudent extends Fragment {

    private LogoutViewModelStudent logoutViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModelStudent =
                ViewModelProviders.of(this).get(LogoutViewModelStudent.class);
        Intent logout = new Intent(getActivity(), LoginOption.class);
        startActivity(logout);
          View root = inflater.inflate(R.layout.fragment_jobs_student, container, false);
//        final TextView textView = root.findViewById(R.id.text_jobs_student);
//        logoutViewModelStudent.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
         return root;
    }
}