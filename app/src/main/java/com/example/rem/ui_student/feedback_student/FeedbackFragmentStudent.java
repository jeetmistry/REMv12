package com.example.rem.ui_student.feedback_student;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackFragmentStudent extends Fragment {

    private FeedbackViewModelStudent feedbackViewModelStudent;
    EditText feedback;
    TextView previousFeedback;
    Button sendFeedback;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,feedbackRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedbackViewModelStudent =
                ViewModelProviders.of(this).get(FeedbackViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_feedback_student, container, false);
        // initializing variables
        feedback = root.findViewById(R.id.editText_feedback_student);
        previousFeedback = root.findViewById(R.id.feedback_student_previousfeedback);
        sendFeedback = root.findViewById(R.id.button_feedback_student);
        firebaseAuth = FirebaseAuth.getInstance();
        String curUser = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userIdRef = userRef.child(curUser);
        feedbackRef = userIdRef.child("feedbacks");

        //sending feedback
        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feed = feedback.getText().toString();
                if (TextUtils.isEmpty(feed)) {
                    Toast.makeText(getContext(), "Please Enter a message", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    feedbackRef.child("feedback").setValue(feed).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Feedback Posted Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



        feedbackRef.child("feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String prefeed;
                    prefeed = dataSnapshot.getValue().toString();
                    previousFeedback.setText(prefeed);
                }
                else{
                    previousFeedback.setText("No Previous Feedbacks");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;
    }
}