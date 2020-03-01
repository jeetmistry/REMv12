package com.example.rem.ui_recruiter.profile_recruiter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.Model.StoreRecruiterProfile;
import com.example.rem.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;



public class ProfileFragmentRecruiter extends Fragment {

    private ImageView profileImage ;
    private FloatingActionButton selectImage ;
    private int GALLERY = 1 , CAMERA = 2;

    EditText recruiter_profile_name;
    EditText recruiter_profile_email;
    EditText recruiter_profile_phone;
    EditText recruiter_profile_company_name;
    EditText recruiter_profile_company_location;
    String recruiter_userid;
    Button recruiter_profile_savebutton;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef,profileRef,userIdRef,userRef,userId,jobref;
    private ProfileViewModelRecruiter profileViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelRecruiter =
                ViewModelProviders.of(this).get(ProfileViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_profile_recruiter, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_recruiter);
        recruiter_profile_name = root.findViewById(R.id. recruiter_profile_name);
        recruiter_profile_email = root.findViewById(R.id. recruiter_profile_email);
        recruiter_profile_phone = root.findViewById(R.id.recruiter_profile_password);
        recruiter_profile_company_name = root.findViewById(R.id. recruiter_profile_company_name);
        recruiter_profile_company_location = root.findViewById(R.id.recruiter_profile_company_location);


        profileViewModelRecruiter.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                //initializing database variables
                firebaseAuth = FirebaseAuth.getInstance();

                firebaseDatabase = FirebaseDatabase.getInstance();
                rootRef = firebaseDatabase.getReference();
                userRef=rootRef.child("recruiter");
                String userId= firebaseAuth.getCurrentUser().getUid().toString();
                userIdRef=userRef.child(userId);
                profileRef = userIdRef.child("profile");


            }
        });
        profileImage=root.findViewById(R.id.recruiter_profile_imageView);
        selectImage=root.findViewById(R.id.recruiter_profile_floatingActionButton);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        recruiter_profile_savebutton = root.findViewById(R.id.recruiter_profile_savebutton);
        recruiter_profile_savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,phone,companyName,companyLocation;
                name= recruiter_profile_name.getText().toString();
                email=recruiter_profile_email.getText().toString();
                phone=recruiter_profile_phone.getText().toString();
                companyName=recruiter_profile_company_name.getText().toString();
                companyLocation=recruiter_profile_company_location.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getContext(), "Please enter Full name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){

                    Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(getContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(companyName)){
                    Toast.makeText(getContext(), "Please enter company name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(companyLocation)){
                    Toast.makeText(getContext(), "Please enter company location", Toast.LENGTH_SHORT).show();
                    return;
                }

                StoreRecruiterProfile srp = new StoreRecruiterProfile(name,email,phone,companyName,companyLocation);
                profileRef.setValue(srp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Profile Saved Succesfully", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });
        return root;
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    public void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY && resultCode==RESULT_OK) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), contentURI);

                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    profileImage.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA && resultCode==RESULT_OK) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(thumbnail);

            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
}