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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegister extends AppCompatActivity {
    EditText txtUser,txtEmail,txtPassword,txtRetypePassword;
    ProgressBar progressBar;
    private TextView tvSignUp;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference rootRef, userRef, useridRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtUser=findViewById(R.id.student_register_username);
        txtEmail=findViewById(R.id.student_register_email);
        txtPassword=findViewById(R.id.student_register_password);
        txtRetypePassword=findViewById(R.id.student_register_RetypePassword);
        tvSignUp=findViewById(R.id.student_register_Signup);
        progressBar=findViewById(R.id.student_progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();
        userRef = rootRef.child("student");
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                                           final String username=txtUser.getText().toString().trim();
                                            String email =txtEmail.getText().toString().trim();
                                            String password=txtPassword.getText().toString().trim();
                                            String confirmPassword=txtRetypePassword.getText().toString().trim();
                                            if(TextUtils.isEmpty(username)){
                                                Toast.makeText(StudentRegister.this, "Please enter username", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(email)){
                                                Toast.makeText(StudentRegister.this, "Please enter email", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(password)){
                                                Toast.makeText(StudentRegister.this, "Please enter password", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            if(TextUtils.isEmpty(confirmPassword)){
                                                Toast.makeText(StudentRegister.this, "Please enter confrim password", Toast.LENGTH_SHORT).show();
                                                return;
                                            }

                                            if(password.length()<6){
                                                Toast.makeText(StudentRegister.this, "Password too short", Toast.LENGTH_SHORT).show();
                                            }

                                            progressBar.setVisibility(View.VISIBLE);
                                            if(password.equals(confirmPassword)){
                                                firebaseAuth.createUserWithEmailAndPassword(email, password)
                                                        .addOnCompleteListener(StudentRegister.this, new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                progressBar.setVisibility(View.GONE);
                                                                if (task.isSuccessful()) {
                                                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {
                                                                                Toast.makeText(StudentRegister.this, "Registration Successfully.Please chcek your email for verification", Toast.LENGTH_SHORT).show();
                                                                                txtUser.setText("");
                                                                                txtEmail.setText("");
                                                                                txtPassword.setText("");
                                                                                txtRetypePassword.setText("");
                                                                                String userId = firebaseAuth.getCurrentUser().getUid();
                                                                                useridRef = userRef.child(userId);
                                                                                useridRef.child("username").child("username").setValue(username);
                                                                            }else{
                                                                                Toast.makeText(StudentRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                    });
                                                                    startActivity(new Intent(getApplicationContext(),StudentNavigation.class));

                                                                } else {
                                                                    Toast.makeText(StudentRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
