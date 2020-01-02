package com.example.rem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RecruiterRegister extends AppCompatActivity {
    EditText txtUser,txtEmail,txtPassword,txtRetypePassword;
    ProgressBar progressBar;
    private TextView tvSignUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtUser=findViewById(R.id.recruiter_register_username);
        txtEmail=findViewById(R.id.recruiter_register_email);
        txtPassword=findViewById(R.id.recruiter_register_password);
        txtRetypePassword=findViewById(R.id.recruiter_register_RetypePassword);
        tvSignUp=findViewById(R.id.recruiter_register_signup);
        progressBar=findViewById(R.id.recruiter_progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        tvSignUp.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v){
                                            String username=txtUser.getText().toString().trim();
                                            String email =txtEmail.getText().toString().trim();
                                            String password=txtPassword.getText().toString().trim();
                                            String confirmPassword=txtRetypePassword.getText().toString().trim();
                                            if(TextUtils.isEmpty(username)){
                                                Toast.makeText(RecruiterRegister.this, "Please enter username", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(email)){
                                                Toast.makeText(RecruiterRegister.this, "Please enter email", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(password)){
                                                Toast.makeText(RecruiterRegister.this, "Please enter password", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(confirmPassword)){
                                                Toast.makeText(RecruiterRegister.this, "Please enter confrim password", Toast.LENGTH_SHORT).show();
                                                return;
                                            }

                                            if(password.length()<6){
                                                Toast.makeText(RecruiterRegister.this, "Password too short", Toast.LENGTH_SHORT).show();
                                            }

                                            progressBar.setVisibility(View.VISIBLE);
                                            if(password.equals(confirmPassword)){
                                                firebaseAuth.createUserWithEmailAndPassword(email, password)
                                                        .addOnCompleteListener(RecruiterRegister.this, new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                progressBar.setVisibility(View.GONE);
                                                                if (task.isSuccessful()) {
                                                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {
                                                                                Toast.makeText(RecruiterRegister.this, "Registration Successfully.Please chcek your email for verification", Toast.LENGTH_SHORT).show();
                                                                                txtUser.setText("");
                                                                                txtEmail.setText("");
                                                                                txtPassword.setText("");
                                                                                txtRetypePassword.setText("");
                                                                            }else{
                                                                                Toast.makeText(RecruiterRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                    });
                                                                    startActivity(new Intent(getApplicationContext(),RecruiterNavigation.class));

                                                                } else {
                                                                    Toast.makeText(RecruiterRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                }

                                                                // ...
                                                            }
                                                        });

                                            }


                                        }

                                    }


        );
    }

}
