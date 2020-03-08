package com.example.rem.ui_recruiter;

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
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rem.Model.ViewApplicationsStudent;
import com.example.rem.R;
import com.example.rem.RecruiterNavigation;
import com.example.rem.StudentNavigation;
import com.example.rem.ui_student.StudentJobCardClickActivity;
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
        String alertItem[] = {"Accept Application ","Keep Pending","Reject applicant","Cancel"};
        builder.setItems(alertItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        enterIntoFirebase();
                        break;
                    case 1:
                        enterPendingToFirebase();
                        break;
                    case 2:
                        enterRejected();
                    case 3:
                        dialog.cancel();
                        break;
                }

            }
        });
        builder.show();
    }

    private void enterRejected() {
        String status = "Rejected";
        ViewApplicationsStudent vas = new ViewApplicationsStudent(job,compname,compdesc,worktype,status);
        appliedJobRef.child(job).setValue(vas);

        //notification
        Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String text="The job application for post "+job+" has been Rejected ";
        NotificationCompat.Builder builder =new NotificationCompat.Builder(RecruiterApplicationCardClickActivity.this)
                .setSmallIcon(R.mipmap.ic_remlogo)
                .setContentTitle("Application Rejected")
                .setContentText(text)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSound(noti)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text));

        Intent intent= new Intent(RecruiterApplicationCardClickActivity.this, RecruiterNavigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String message="abc";
        intent.putExtra("Message",message);
        PendingIntent pendingIntent =PendingIntent.getActivity(RecruiterApplicationCardClickActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }

    private void enterPendingToFirebase() {
        String status = "Pending";
        ViewApplicationsStudent vas = new ViewApplicationsStudent(job,compname,compdesc,worktype,status);
        appliedJobRef.child(job).setValue(vas);

        //notification
        String text = "The job application for post "+job+" has been viewed and under scrunity ";
        Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =new NotificationCompat.Builder(RecruiterApplicationCardClickActivity.this)
                .setSmallIcon(R.mipmap.ic_remlogo)
                .setContentTitle("Pending Application")
                .setContentText(text)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSound(noti)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text));;

        Intent intent= new Intent(RecruiterApplicationCardClickActivity.this, RecruiterNavigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String message="abc";
        intent.putExtra("Message",message);
        PendingIntent pendingIntent =PendingIntent.getActivity(RecruiterApplicationCardClickActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }

    private void enterIntoFirebase() {

        String status = "Accepted";
        ViewApplicationsStudent vas = new ViewApplicationsStudent(job,compname,compdesc,worktype,status);
        appliedJobRef.child(job).setValue(vas);

        //notification
        Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String text="The job application for post "+job+" has been approved and accepted";
        NotificationCompat.Builder builder =new NotificationCompat.Builder(RecruiterApplicationCardClickActivity.this)
                .setSmallIcon(R.mipmap.ic_remlogo)
                .setContentTitle("Accepted Application")
                .setContentText(text)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSound(noti)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text));;

        Intent intent= new Intent(RecruiterApplicationCardClickActivity.this, RecruiterNavigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String message="abc";
       intent.putExtra("Message",message);
        PendingIntent pendingIntent =PendingIntent.getActivity(RecruiterApplicationCardClickActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());


    }
}
