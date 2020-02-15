package com.example.rem.ui_admin.viewfeedback_admin;

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

public class ViewfeedbacksFragmentAdmin extends Fragment {

    private ViewfeedbacksViewModelAdmin viewfeedbacksViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewfeedbacksViewModelAdmin =
                ViewModelProviders.of(this).get(ViewfeedbacksViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_viewfeedback_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        viewfeedbacksViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}