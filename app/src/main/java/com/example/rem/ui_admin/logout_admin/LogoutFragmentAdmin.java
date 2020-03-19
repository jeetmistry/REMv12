package com.example.rem.ui_admin.logout_admin;

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

public class LogoutFragmentAdmin extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Intent logout = new Intent(getActivity(), LoginOption.class);
        Toast.makeText(getActivity(), "Logging Out From Admin ...", Toast.LENGTH_SHORT).show();
        startActivity(logout);
        return inflater.inflate(R.layout.fragment_deletedjobs_admin, container, false);
    }
}
