package com.example.rem.ui_student.logout_student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.LoginOption;
import com.example.rem.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragmentStudent extends Fragment {

    private LogoutViewModelStudent logoutViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModelStudent =
                ViewModelProviders.of(this).get(LogoutViewModelStudent.class);
        FirebaseAuth.getInstance().signOut();
        Intent logout = new Intent(getActivity(), LoginOption.class);
        Toast.makeText(getActivity(),"Logging Out From Student",Toast.LENGTH_SHORT).show();
        startActivity(logout);
          View root = inflater.inflate(R.layout.fragment_jobs_student, container, false);

         return root;
    }
}