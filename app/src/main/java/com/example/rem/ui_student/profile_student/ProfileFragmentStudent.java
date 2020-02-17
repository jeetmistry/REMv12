package com.example.rem.ui_student.profile_student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;

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
    FirebaseStorage storage = FirebaseStorage.getInstance();

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
    //FUNCTION FOR CTAKING PHOTO FROM CAMERA INTENT
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    //FUNCTION FOR CHOOSING IMAGE FROM GALLERY INTENT
    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
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
        if (requestCode == GALLERY && resultCode==RESULT_OK) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap imgbitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), contentURI);

                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    profileImage.setImageBitmap(imgbitmap);
            //        navprofileImage.setImageBitmap(imgbitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imgbitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA && resultCode==RESULT_OK) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(thumbnail);
//            navprofileImage.setImageBitmap(thumbnail);
            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

}