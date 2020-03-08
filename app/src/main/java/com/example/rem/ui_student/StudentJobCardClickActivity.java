package com.example.rem.ui_student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rem.Model.ViewApplicationsStudent;
import com.example.rem.R;
import com.example.rem.RecruiterNavigation;
import com.example.rem.StudentNavigation;
import com.example.rem.ui_recruiter.RecruiterApplicationCardClickActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentJobCardClickActivity extends AppCompatActivity {
    public TextView jobpost, companyname, companydescription, workingtype;
    Button apply;
    String job,compname,compdesc,worktype, userid;

    //Database variables
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,appliedJobRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_job_card_click);
        jobpost = findViewById(R.id.student_job_cardclick_jobpost);
        companyname = findViewById(R.id.student_job_cardclick_companyname);
        companydescription = findViewById(R.id.student_job_cardclick_companydescription);
        workingtype = findViewById(R.id.student_job_cardclick_workingtype);
        apply = findViewById(R.id.student_job_cardclick_applybtn);

        //initializing database variables
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userIdRef = userRef.child(userid);
        appliedJobRef = userIdRef.child("Applied Applications");

        //receiving extras from card
        Intent intent = getIntent();
        job = intent.getStringExtra("job post");
        compname = intent.getStringExtra("company name");
        compdesc = intent.getStringExtra("company description");
        worktype = intent.getStringExtra("working type");

        //displaying them to the user
        jobpost.setText("Job Post : "+job);
        companyname.setText("Company Name : "+compname);
        companydescription.setText("Company Description : "+compdesc);
        workingtype.setText("Working Type : "+worktype);

        //setting apply button click event
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlert();
            }
        });
    }

    private void createAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select your option");
        String alertItem[] = {"Apply for Job","Cancel"};
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
        String status = "Applied";
        ViewApplicationsStudent vas = new ViewApplicationsStudent(job,compname,compdesc,worktype,status);
        appliedJobRef.child(job).setValue(vas);

        //notification
        Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String text="The job application for post "+job+" has been approved and accepted";
        NotificationCompat.Builder builder =new NotificationCompat.Builder(StudentJobCardClickActivity.this)
                .setSmallIcon(R.mipmap.ic_remlogo)
                .setContentTitle("Accepted Application")
                .setContentText("The job application for post "+job+" has been approved and accepted")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSound(noti)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text));

        Intent intent= new Intent(StudentJobCardClickActivity.this, RecruiterNavigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(StudentJobCardClickActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());


    }






    }


