package com.example.rem.ui_student.profile_student;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class ProfileFragmentStudent extends Fragment {

    private ProfileViewModelStudent profileViewModelStudent;
    private ImageView profileImage ;
    private ImageView navprofileImage;
    private FloatingActionButton selectImage ;
    private int GALLERY = 1 , CAMERA = 2;
    FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference useridReference;
    private FirebaseUser firebaseUser;
    private String profileName;
    private String userid;
    private Button saveProfile;
    Uri contentURI;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelStudent =
                ViewModelProviders.of(this).get(ProfileViewModelStudent.class);
       final View root = inflater.inflate(R.layout.fragment_profile_student, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_student);
        profileViewModelStudent.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                profileImage=root.findViewById(R.id.student_profile_imageView);
                navprofileImage=root.findViewById(R.id.imageViewStudent);
                saveProfile = (Button)root.findViewById(R.id.student_profile_resumesavebutton);
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                userid = firebaseUser.getUid();
                firebaseStorage = FirebaseStorage.getInstance();
                StorageReference storageReference = firebaseStorage.getReference();
                StorageReference userReference = storageReference.child("student/");
                useridReference = userReference.child(userid+"/");

                saveProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        useridReference.child("Images/").putFile(contentURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getContext(), "Profile Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String ee= e.toString();
                                Toast.makeText(getActivity(), ee, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });


        selectImage=root.findViewById(R.id.student_profile_floatingActionButton);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        return root;
    }
    //FUNCTION FOR TAKING PHOTO FROM CAMERA INTENT
    private void takePhotoFromCamera() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 10);

        }
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    //FUNCTION FOR CHOOSING IMAGE FROM GALLERY INTENT
    private void choosePhotoFromGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
    }

    //ALERT DIALOG ASKING GALLERY OR CAMERA INTENT
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

    //For storing data in imageview of student profile
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY && resultCode==RESULT_OK){
            if (data != null) {
                contentURI = data.getData();
                try {
                    Bitmap imgbitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), contentURI);

                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    profileImage.setImageBitmap(imgbitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA && resultCode==RESULT_OK) {
            Uri contentURI = data.getData();


            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(thumbnail);

                }

            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }

    }

