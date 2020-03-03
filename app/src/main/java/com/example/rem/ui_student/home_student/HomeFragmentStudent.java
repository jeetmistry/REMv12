package com.example.rem.ui_student.home_student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragmentStudent extends Fragment {
    TextView name,emailid,phoneno,city,qualification,collegename,passingyear,fieldofinterest, profilemessage,personal,quadet;
        ImageView profileImage;
    //firebase variables declaration
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,profileRef;
    String userid;

    private HomeViewModelStudent homeViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelStudent =
                ViewModelProviders.of(this).get(HomeViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_home_student, container, false);


        //Textviews initialization
        profilemessage = root.findViewById(R.id.student_home_profile_msg);
        name=root.findViewById(R.id.student_home_info_name);
        emailid=root.findViewById(R.id.student_home_info_email);
        phoneno=root.findViewById(R.id.student_home_info_phoneno);
        city=root.findViewById(R.id.student_home_info_city);
        qualification=root.findViewById(R.id.student_home_info_qualification);
        collegename=root.findViewById(R.id.student_home_info_clgname);
        passingyear=root.findViewById(R.id.student_home_info_passingyear);
        fieldofinterest=root.findViewById(R.id.student_home_info_INTEREST);
        personal = root.findViewById(R.id.student_home_profile_personal);
        quadet = root.findViewById(R.id.student_home_profile_qualification);

        //ImageView
        profileImage = root.findViewById(R.id.student_home_profile_image);

        //database initialization
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userid = firebaseAuth.getCurrentUser().getUid();
        userIdRef = userRef.child(userid);
        profileRef = userIdRef.child("profile");

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String Name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    String City = dataSnapshot.child("city").getValue().toString();
                    String Qualification = dataSnapshot.child("qualification").getValue().toString();
                    String Collegename = dataSnapshot.child("collegeName").getValue().toString();
                    String Passingyear = dataSnapshot.child("passingYear").getValue().toString();
                    String field = dataSnapshot.child("fields").getValue().toString();

                    //setting visibility of profile
                    profilemessage.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.VISIBLE);
                    emailid.setVisibility(View.VISIBLE);
                    phoneno.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    qualification.setVisibility(View.VISIBLE);
                    collegename.setVisibility(View.VISIBLE);
                    passingyear.setVisibility(View.VISIBLE);
                    fieldofinterest.setVisibility(View.VISIBLE);
                    personal.setVisibility(View.VISIBLE);
                    quadet.setVisibility(View.VISIBLE);
                    profileImage.setVisibility(View.VISIBLE);


                    //assigning values
                    name.setText("Name : "+Name);
                    emailid.setText("Email : "+email);
                    phoneno.setText("Phone : "+phone);
                    city.setText("City : "+City);
                    qualification.setText("Qualification : "+Qualification);
                    collegename.setText("College Name : "+Collegename);
                    passingyear.setText("Passing Year : "+Passingyear);
                    fieldofinterest.setText("Field : "+field);


                }else{
                    profilemessage.setText("Please Create Your Profile");
                    name.setVisibility(View.INVISIBLE);
                    emailid.setVisibility(View.INVISIBLE);
                    phoneno.setVisibility(View.INVISIBLE);
                    city.setVisibility(View.INVISIBLE);
                    qualification.setVisibility(View.INVISIBLE);
                    collegename.setVisibility(View.INVISIBLE);
                    passingyear.setVisibility(View.INVISIBLE);
                    fieldofinterest.setVisibility(View.INVISIBLE);

                    profileImage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

}