package com.example.rem.ui_admin.viewstudents_admin;

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

public class ViewstudentsFragment extends Fragment {

    private ViewstudentsViewModelAdmin viewstudentsViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewstudentsViewModelAdmin =
                ViewModelProviders.of(this).get(ViewstudentsViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_viewstudent_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        viewstudentsViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}