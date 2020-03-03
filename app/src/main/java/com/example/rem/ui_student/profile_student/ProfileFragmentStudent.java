package com.example.rem.ui_student.profile_student;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.Visibility;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rem.Model.StoreRecruiterProfile;
import com.example.rem.Model.StoreStudentProfile;
import com.example.rem.R;
import com.example.rem.ui_student.home_student.HomeFragmentStudent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

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
    private ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference profileRef,rootRef, userRef,userIdRef;
    Uri contentURI;
    EditText student_profile_username;
    EditText student_profile_email;
    EditText student_profile_phonenumber;
    EditText student_profile_city;
    EditText student_profile_qualification;
    EditText student_profile_collegename;
    EditText student_profile_passing_year;
    EditText student_profile_fields;
    Button student_profile_resumesavebutton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelStudent =
                ViewModelProviders.of(this).get(ProfileViewModelStudent.class);
       final View root = inflater.inflate(R.layout.fragment_profile_student, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_student);
       student_profile_username = root.findViewById(R.id. student_profile_username);
       student_profile_email= root.findViewById(R.id. student_profile_email);
        student_profile_phonenumber= root.findViewById(R.id. student_profile_phonenumber);
        student_profile_city= root.findViewById(R.id. student_profile_city);
        student_profile_qualification= root.findViewById(R.id. student_profile_qualification);
        student_profile_collegename= root.findViewById(R.id. student_profile_collegename);
        student_profile_passing_year= root.findViewById(R.id. student_profile_passing_year);
        student_profile_fields=root.findViewById(R.id.student_profile_fields);


                profileImage=root.findViewById(R.id.student_profile_imageView);
                navprofileImage=root.findViewById(R.id.imageViewStudent);
               // saveProfile = (Button)root.findViewById(R.id.student_profile_resumesavebutton);
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                firebaseStorage = FirebaseStorage.getInstance();
                StorageReference storageReference = firebaseStorage.getReference();
                StorageReference userReference = storageReference.child("student/");
                String userid=firebaseAuth.getCurrentUser().getUid().toString();
                useridReference = userReference.child(userid+"/");
                StorageReference ProfileRef=useridReference.child("profile/");

//                saveProfile.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        useridReference.child("Images/").putFile(contentURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                Toast.makeText(getContext(), "Profile Uploaded", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                String ee= e.toString();
//                                Toast.makeText(getActivity(), ee, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    }
//                });




        selectImage=root.findViewById(R.id.student_profile_floatingActionButton);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef=rootRef.child("student");
        String userId= firebaseAuth.getCurrentUser().getUid().toString();
        userIdRef=userRef.child(userId);
        profileRef=userIdRef.child("profile");
        student_profile_resumesavebutton = root.findViewById(R.id.student_profile_resumesavebutton);

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


                    student_profile_username.setText(Name);
                    student_profile_email.setText(email);
                    student_profile_phonenumber.setText(phone);
                    student_profile_city.setText(City);
                    student_profile_city.setText(City);
                    student_profile_qualification.setText(Qualification);
                    student_profile_collegename.setText(Collegename);
                    student_profile_passing_year.setText(Passingyear);
                    student_profile_fields.setText(field);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        student_profile_resumesavebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,email,phone,city,qualification,collegeName,passingYear,fields;
                name=student_profile_username.getText().toString();
                email=student_profile_email.getText().toString();
                phone=student_profile_phonenumber.getText().toString();
                city=student_profile_city.getText().toString();
                qualification=student_profile_qualification.getText().toString();
                collegeName=student_profile_collegename.getText().toString();
                passingYear=student_profile_passing_year.getText().toString();
                fields=student_profile_fields.getText().toString();


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
                if(TextUtils.isEmpty(city)){
                    Toast.makeText(getContext(), "Please enter company name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(qualification)){
                    Toast.makeText(getContext(), "Please enter company location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(collegeName)){
                    Toast.makeText(getContext(), "Please enter company location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passingYear)){
                    Toast.makeText(getContext(), "Please enter company location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(fields)){
                    Toast.makeText(getContext(), "Please enter fields of Interest", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(contentURI!=null) {
                    uploadImage();
                }

               StoreStudentProfile ssp = new StoreStudentProfile(name,email,phone,city,qualification,collegeName,passingYear,fields);
               profileRef.setValue(ssp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Profile Saved Succesfully", Toast.LENGTH_SHORT).show();
                    }
                });
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

        //uploading image
        private void uploadImage() {

            if(contentURI!= null)
            {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = useridReference.child("images/"+ UUID.randomUUID().toString());
                ref.putFile(contentURI)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+(int)progress+"%");
                            }
                        });
            }
        }
    }

