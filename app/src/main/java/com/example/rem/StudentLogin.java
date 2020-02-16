package com.example.rem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.*;

public class StudentLogin extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    ProgressBar progressBar;
    Button btn_student_Login;
    private TextView Student_signup;
    private FirebaseAuth mAuth;
    private TextView forgotPassword;

    //for one time login

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(getApplicationContext(), StudentNavigation.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        txtEmail=(EditText) findViewById(R.id.student_login_username);
        txtPassword=(EditText)findViewById(R.id.student_login_password);
        btn_student_Login   = (Button) findViewById(R.id.student_Login);
        Student_signup=(TextView)findViewById(R.id.student_login_Signup);
        progressBar=(ProgressBar) findViewById(R.id.student_progressBar);
        forgotPassword=(TextView)findViewById(R.id.student_login_forgot);
        mAuth = getInstance();

        btn_student_Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String email =txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(StudentLogin.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(StudentLogin.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6){
                    Toast.makeText(StudentLogin.this, "Password too short", Toast.LENGTH_SHORT).show();
                }


                progressBar.setVisibility(View.VISIBLE);
                mAuth .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(StudentLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if(mAuth.getCurrentUser().isEmailVerified()){
                                        startActivity(new Intent(StudentLogin.this,StudentNavigation.class));

                                    }else{
                                        Toast.makeText(StudentLogin.this,"Please verify your email address",Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Toast.makeText(StudentLogin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });
            }
        });

        Student_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StudentRegister.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),student_password.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }


}
