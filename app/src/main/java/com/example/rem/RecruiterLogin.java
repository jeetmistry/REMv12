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
import static com.google.firebase.auth.FirebaseAuth.*;

public class RecruiterLogin extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    ProgressBar progressBar;
    Button btn_recruiter_Login;
    private TextView Recruiter_signup;
    private FirebaseAuth firebaseAuth;
    private TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);
        txtEmail=findViewById(R.id.recruiter_login_username);
        txtPassword=findViewById(R.id.recruiter_login_password);
        btn_recruiter_Login   = findViewById(R.id.recruiter_Login);
        Recruiter_signup=findViewById(R.id.recruiter_login_Signup);
        progressBar=findViewById(R.id.recruiter_progressBar);
        forgot_password=(TextView)findViewById(R.id.recruiter_login_forgot);
        firebaseAuth= getInstance();


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

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RecruiterLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                        startActivity(new Intent(RecruiterLogin.this,RecruiterNavigation.class));

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
                startActivity(new Intent(getApplicationContext(),RecruiterRegister.class));
            }
        });
         forgot_password.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),recruiter_password.class));
             }
         });


    }


}

