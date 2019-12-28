package com.example.rem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btnlogin = (Button)findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals("Admin") && password.getText().toString().equals("1234")){

                    Toast.makeText(getApplicationContext(),"Redirecting..",Toast.LENGTH_SHORT).show();

                    //JUST CHECKING THAT STUDENT NAVIGATION WORKS

                    Intent intent=new Intent(AdminLogin.this,StudentNavigation.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(getApplicationContext(),"Wrong Credentials!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}