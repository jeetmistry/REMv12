package com.example.rem.ui_admin.viewstudents_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.Model.StoreStudentProfile;
import com.example.rem.R;
import com.example.rem.ViewHolders.AdminViewStudentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewstudentsFragmentAdmin extends Fragment {

    private RecyclerView viewStudentsRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef,users;

    private ViewstudentsViewModelAdmin viewstudentsViewModelAdmin;
    private AdminViewStudentViewHolder viewStudentViewHolder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewstudentsViewModelAdmin =
                ViewModelProviders.of(this).get(ViewstudentsViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_viewstudent_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        firebaseAuth = FirebaseAuth.getInstance();
        viewStudentsRecycler = root.findViewById(R.id.recyclerView_viewstudent_admin);
        layoutManager =new LinearLayoutManager(getContext());
        viewStudentsRecycler.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<StoreStudentProfile>options = new FirebaseRecyclerOptions.Builder<StoreStudentProfile>()
                .setQuery(userRef, new SnapshotParser<StoreStudentProfile>() {
                    @NonNull
                    @Override
                    public StoreStudentProfile parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new StoreStudentProfile(snapshot.child("profile").child("name").getValue().toString(),
                                snapshot.child("profile").child("email").getValue().toString(),
                                snapshot.child("profile").child("phone").getValue().toString(),
                                snapshot.child("profile").child("city").getValue().toString(),
                                snapshot.child("profile").child("qualification").getValue().toString(),
                                snapshot.child("profile").child("collegeName").getValue().toString(),
                                snapshot.child("profile").child("passingYear").getValue().toString(),
                                snapshot.child("profile").child("fields").getValue().toString());
                    }
                })
                .build();

        FirebaseRecyclerAdapter<StoreStudentProfile,AdminViewStudentViewHolder> adapter = new FirebaseRecyclerAdapter<StoreStudentProfile, AdminViewStudentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminViewStudentViewHolder adminViewStudentViewHolder, int i, @NonNull StoreStudentProfile storeStudentProfile) {
                adminViewStudentViewHolder.fullname.setText(storeStudentProfile.getName());
                adminViewStudentViewHolder.email.setText("Email :"+storeStudentProfile.getEmail());
                adminViewStudentViewHolder.phone.setText("Phone :"+storeStudentProfile.getPhone());
                adminViewStudentViewHolder.city.setText("City : "+storeStudentProfile.getCity());
                adminViewStudentViewHolder.qualification.setText("Qualification : "+storeStudentProfile.getQualification());
                adminViewStudentViewHolder.collegename.setText("College Name : "+storeStudentProfile.getCollegeName());
                adminViewStudentViewHolder.passingyear.setText("Passing Year : "+storeStudentProfile.getPassingYear());

            }

            @NonNull
            @Override
            public AdminViewStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewstudent_admin,parent,false);
                AdminViewStudentViewHolder holder = new AdminViewStudentViewHolder(view);
                return holder;
            }
        };
        viewStudentsRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}