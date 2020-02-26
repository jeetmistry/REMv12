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

import com.example.rem.Model.ViewJobsRecruiter;
import com.example.rem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddjobsFragmentRecruiter extends Fragment {

    // declaring variables
    EditText companyName;
    EditText companyDescription;
    EditText jobPost,workType;
    String workingType,companyname,companydescription,jobpost;
    String userid;
    Button submitPost;

    //declaring database variables
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

        //initializing variables
        companyName = root.findViewById(R.id.recruiter_addjobs_companyname);
        companyDescription = root.findViewById(R.id.recruiter_addjobs_companydescription);
        jobPost = root.findViewById(R.id.recruiter_addjobs_jobpost);
        workType = root.findViewById(R.id.recruiter_addjobs_worktype);
        submitPost = root.findViewById(R.id.recruiter_addjobs_addjob);

        //initializing database variables
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
                workingType = workType.getText().toString();

                // validating not empty EditText
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
                if (TextUtils.isEmpty(workingType)) {
                    Toast.makeText(getContext(), "Please enter Work Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                //creating unique id for a particular job
                jobref = userIdRef.child("Jobs").push();

                //storing the jo in database
                if (!TextUtils.isEmpty(companyname) && !TextUtils.isEmpty(companydescription) && !TextUtils.isEmpty(jobpost) && !TextUtils.isEmpty(workingType)) {
                    ViewJobsRecruiter vjr = new ViewJobsRecruiter(companyname,companydescription,jobpost,workingType);
                    jobref.setValue(vjr);
                    jobPost.setText("");
                    companyName.setText("");
                    companyDescription.setText("");
                    workType.setText("");

                }
                else{
                    Toast.makeText(getContext(), "Cannot Post Job", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //viewmodel code
        addjobsViewModelRecruiter.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}