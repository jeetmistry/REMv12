package com.example.rem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //just checking Recruiter Login Works
        Intent intent = new Intent(getApplicationContext(),RecruiterNavigation.class);
        startActivity(intent);
    }
}
