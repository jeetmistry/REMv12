package com.example.rem.ui_recruiter.home_recruiter;

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

public class HomeFragmentRecruiter extends Fragment {
    TextView name,emailid,phoneno,companyname,companylocation,fieldofinterest, profilemessage,personal,compdet;
    ImageView profileImage;

    //declaring firebase variables
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,userRef,userIdRef,profileRef;
    String userid;

    private HomeViewModelRecruiter homeViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelRecruiter =
                ViewModelProviders.of(this).get(HomeViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_home_recruiter, container, false);
        //Textviews initialization
        profilemessage = root.findViewById(R.id.recruiter_home_profile_msg);
        name=root.findViewById(R.id.recruiter_home_info_name);
        emailid=root.findViewById(R.id.recruiter_home_info_email);
        phoneno=root.findViewById(R.id.recruiter_home_info_phoneno);
        companyname=root.findViewById(R.id.recruiter_home_info_companyname);
        companylocation=root.findViewById(R.id.recruiter_home_info_companylocation);
        fieldofinterest=root.findViewById(R.id.recruiter_home_info_field);
        personal = root.findViewById(R.id.recruiter_home_profile_personal);
        compdet = root.findViewById(R.id.recruiter_home_profile_company);

        //ImageView
        profileImage = root.findViewById(R.id.recruiter_home_profile_image);

        //database initialization
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("recruiter");
        userid = firebaseAuth.getCurrentUser().getUid();
        userIdRef = userRef.child(userid);
        profileRef = userIdRef.child("profile");

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String Name = dataSnapshot.child("fullname").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    String Companyname = dataSnapshot.child("companyname").getValue().toString();
                    String Companylocation = dataSnapshot.child("companylocation").getValue().toString();
                    String field = dataSnapshot.child("fieldsOfWork").getValue().toString();

                    //setting visibility of profile
                    profilemessage.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.VISIBLE);
                    emailid.setVisibility(View.VISIBLE);
                    phoneno.setVisibility(View.VISIBLE);
                    companyname.setVisibility(View.VISIBLE);
                    companylocation.setVisibility(View.VISIBLE);

                    fieldofinterest.setVisibility(View.VISIBLE);
                    personal.setVisibility(View.VISIBLE);
                    compdet.setVisibility(View.VISIBLE);
                    profileImage.setVisibility(View.VISIBLE);


                    //assigning values
                    name.setText("Name : "+Name);
                    emailid.setText("Email : "+email);
                    phoneno.setText("Phone : "+phone);
                    companyname.setText("Company Name : "+Companyname);
                    companylocation.setText("Company Location : "+Companylocation);
                    fieldofinterest.setText("Field : "+field);


                }else{
                    profilemessage.setText("Please Create Your Profile");
                    name.setVisibility(View.INVISIBLE);
                    emailid.setVisibility(View.INVISIBLE);
                    phoneno.setVisibility(View.INVISIBLE);
                    companyname.setVisibility(View.INVISIBLE);
                    companylocation.setVisibility(View.INVISIBLE);

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