package com.example.rem.ui_admin.help_admin;

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

public class HelpFragmentAdmin extends Fragment {

    private HelpViewModelAdmin helpViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpViewModelAdmin =
                ViewModelProviders.of(this).get(HelpViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_help_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        helpViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}