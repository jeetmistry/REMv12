package com.example.rem.ui_recruiter.logout_recruiter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rem.LoginOption;
import com.example.rem.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragmentRecruiter extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FirebaseAuth.getInstance().signOut();
        Intent logout = new Intent(getActivity(), LoginOption.class);
        Toast.makeText(getActivity(), "Logging Out From Recruiter ...", Toast.LENGTH_SHORT).show();
        startActivity(logout);
        return inflater.inflate(R.layout.fragment_home_recruiter, container, false);
    }
}
