package com.example.rem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static com.google.firebase.auth.FirebaseAuth.*;

public class RecruiterLogin extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    ProgressBar progressBar;
    Button btn_recruiter_Login;
    private TextView Recruiter_signup;
    private FirebaseAuth firebaseAuth;
    private TextView forgot_password;
    private FirebaseDatabase database;
    private DatabaseReference rootRef, userRef, useridRef;
    private String userEmail, sameEmail, loginEmail;

    //for one time login and verifying recruiter only

    final AuthStateListener authStateListener = new AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (firebaseUser != null) {
                String userid=firebaseUser.getUid();
                userEmail = firebaseUser.getEmail();
                userRef=rootRef.child("recruiter");
                useridRef = userRef.child(userid);
                final ProgressDialog pd = new ProgressDialog(RecruiterLogin.this);
                pd.setTitle("Logging Recruiter");
                pd.setMessage("Please wait logging in");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                useridRef.child("username").child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sameEmail = (String) dataSnapshot.getValue();
                        if (Objects.equals(userEmail, sameEmail)) {
                            Intent intent = new Intent(getApplicationContext(), RecruiterNavigation.class);
                            startActivity(intent);
                            pd.dismiss();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // error message
                    }
                });
            }
            else{
                    Toast.makeText(RecruiterLogin.this, "Please Sign IN", Toast.LENGTH_SHORT).show();
                }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);

        //variables
        txtEmail=findViewById(R.id.recruiter_login_username);
        txtPassword=findViewById(R.id.recruiter_login_password);
        btn_recruiter_Login   = findViewById(R.id.recruiter_Login);
        Recruiter_signup=findViewById(R.id.recruiter_login_Signup);
        progressBar=findViewById(R.id.recruiter_progressBar);
        forgot_password=(TextView)findViewById(R.id.recruiter_login_forgot);
        firebaseAuth= getInstance();
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();


        btn_recruiter_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final String email =txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RecruiterLogin.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RecruiterLogin.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6){
                    Toast.makeText(RecruiterLogin.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                final ProgressDialog pd = new ProgressDialog(RecruiterLogin.this);
                pd.setTitle("Logging Recruiter");
                pd.setMessage("Please wait, validating credentials and logging in.");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RecruiterLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                        String userid =firebaseAuth.getCurrentUser().getUid();
                                        userRef=rootRef.child("recruiter");
                                        useridRef = userRef.child(userid);
                                        useridRef.child("username").child("username");
                                        useridRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                loginEmail = (String) dataSnapshot.getValue();
                                                if(Objects.equals(email,loginEmail)){
                                                    startActivity(new Intent(RecruiterLogin.this,RecruiterNavigation.class));
                                                    pd.dismiss();
                                                }
                                                else{
                                                    FirebaseAuth.getInstance().signOut();
                                                    Toast.makeText(RecruiterLogin.this, "Please login using a Recruiter account only ", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    pd.dismiss();
                                                    startActivity(new Intent(RecruiterLogin.this,RecruiterLogin.class));

                                                    ;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                //error message
                                            }
                                        });


                                    }else{
                                        Toast.makeText(RecruiterLogin.this,"Please verify your email address",Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Toast.makeText(RecruiterLogin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });
            }
        });

        Recruiter_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),RecruiterRegister.class));
            }
        });
         forgot_password.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
                 startActivity(new Intent(getApplicationContext(),recruiter_password.class));
             }
         });


    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


}

