package com.example.rem.ui_recruiter.addjobs_recruiter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddjobsFragmentRecruiter extends Fragment {


    EditText companyName;
    EditText companyDescription;
    EditText jobPost;
    String workingType,companyname,companydescription,jobpost;
    Spinner workingTypeSpinner;
    String userid;
    Button submitPost;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,jobref;
    private AddjobsViewModelRecruiter addjobsViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addjobsViewModelRecruiter =
                ViewModelProviders.of(this).get(AddjobsViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_addjobs_recruiter, container, false);
        final TextView textView = root.findViewById(R.id.text_addjobs_recruiter);
        companyName = root.findViewById(R.id.recruiter_addjobs_companyname);
        companyDescription = root.findViewById(R.id.recruiter_addjobs_companydescription);
        jobPost = root.findViewById(R.id.recruiter_addjobs_jobpost);
        workingTypeSpinner = root.findViewById(R.id.recruiter_addjobs_workingtype);
        submitPost = root.findViewById(R.id.recruiter_addjobs_addjob);
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef=rootRef.child("recruiter");
        userIdRef = userRef.child(userid);


        //set values in database by button click
        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyname = companyName.getText().toString();
                companydescription = companyDescription.getText().toString();
                jobpost = jobPost.getText().toString();

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.recruiter_workingtype_array,
                        android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                workingTypeSpinner.setAdapter(adapter);
                if (TextUtils.isEmpty(companyname)) {
                    Toast.makeText(getContext(), "Please enter Company Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(companydescription)) {
                    Toast.makeText(getContext(), "Please enter Company Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(jobpost)) {
                    Toast.makeText(getContext(), "Please enter Job Post", Toast.LENGTH_SHORT).show();
                    return;
                }
                jobref = userIdRef.child("Jobs").child(jobpost);

                workingTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String wt;
                        if (position == 0) {
                            Toast.makeText(getContext(), "Select a Working Type", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            wt = parent.getItemAtPosition(position).toString();
                            workingType = wt;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        workingTypeSpinner.requestFocus();
                        Toast.makeText(getContext(), "Select a working type", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
                if (!TextUtils.isEmpty(companyname) && !TextUtils.isEmpty(companydescription) && !TextUtils.isEmpty(jobpost)) {
                    jobref.child("Company Name").setValue(companyname);
                    jobref.child("Company Description").setValue(companydescription);
                    jobref.child("Job Post").setValue(jobpost);
                    jobref.child("Working Type").setValue(workingType);

                    jobPost.setText("");
                    companyName.setText("");
                    companyDescription.setText("");

                }
                else{
                    Toast.makeText(getContext(), "Cannot Post Job", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addjobsViewModelRecruiter.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}