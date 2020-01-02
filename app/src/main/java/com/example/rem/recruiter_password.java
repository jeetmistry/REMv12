package com.example.rem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recruiter_password extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_password);
        passwordEmail=(EditText)findViewById(R.id.recruiter_email);
        resetPassword=(Button)findViewById(R.id.recruiter_reset_password);
        firebaseAuth=FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=passwordEmail.getText().toString().trim();
                if(useremail.equals("")){
                    Toast.makeText(recruiter_password.this, "Please Enter Your Register Email ID", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(recruiter_password.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(recruiter_password.this, StudentLogin.class));
                            }else{
                                Toast.makeText(recruiter_password.this, "Error in sendig password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
