package com.example.rem.ui_recruiter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rem.Model.ViewApplicationsStudent;
import com.example.rem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RecruiterApplicationCardClickActivity extends AppCompatActivity {
    public TextView jobpost, companyname, companydescription, workingtype,status;
    Button accept;
    String job,compname,compdesc,worktype, userid,statuss,key;

    //Database variables
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,appliedJobRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_application_card_click);

        jobpost = findViewById(R.id.recruiter_application_cardclick_jobpost);
        companyname = findViewById(R.id.recruiter_application_cardclick_companyname);
        companydescription = findViewById(R.id.recruiter_application_cardclick_companydescription);
        workingtype = findViewById(R.id.recruiter_application_cardclick_workingtype);
        status = findViewById(R.id.recruiter_application_cardclick_status);
        accept = findViewById(R.id.recruiter_application_cardclick_acceptbtn);


        //initializing database variables
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userIdRef = userRef.child("KIuCq2YHdSgo353RAwZ4wR91Jpd2");


        //receiving extras from card
        Intent intent = getIntent();
        job = intent.getStringExtra("job post");
        compname = intent.getStringExtra("company name");
        compdesc = intent.getStringExtra("company description");
        worktype = intent.getStringExtra("working type");
        statuss = intent.getStringExtra("status");

        appliedJobRef = userIdRef.child("Applied Applications");

        //displaying them to the user
        jobpost.setText("Job Post : "+job);
        companyname.setText("Company Name : "+compname);
        companydescription.setText("Company Description : "+compdesc);
        workingtype.setText("Working Type : "+worktype);
        status.setText("Status : Applied");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlert();
            }
        });
    }

    private void createAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select your option");
        String alertItem[] = {"Accept Application ","Cancel"};
        builder.setItems(alertItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        enterIntoFirebase();
                        break;
                    case 1:
                        dialog.cancel();
                        break;
                }

            }
        });
        builder.show();
    }

    private void enterIntoFirebase() {

        String status = "Accepted";
        ViewApplicationsStudent vas = new ViewApplicationsStudent(job,compname,compdesc,worktype,status);
        appliedJobRef.child(job).setValue(vas);

        ;
    }
}
