package com.example.rem.ui_admin.viewcompanies_admin;

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

import com.example.rem.Model.StoreRecruiterProfile;
import com.example.rem.R;
import com.example.rem.ViewHolders.AdminViewCompanyViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewcompaniesFragmentAdmin extends Fragment {

    private RecyclerView viewCompanyRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef,users;
    private ViewcompaniesViewModelAdmin viewcompaniesViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewcompaniesViewModelAdmin =
                ViewModelProviders.of(this).get(ViewcompaniesViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_viewcompanies_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        firebaseAuth = FirebaseAuth.getInstance();
        viewCompanyRecycler = root.findViewById(R.id.recyclerView_viewcompanies_admin);
        layoutManager =new LinearLayoutManager(getContext());
        viewCompanyRecycler.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("recruiter");
        String user = userRef.getKey();
        users  = userRef.child(user);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<StoreRecruiterProfile> options = new FirebaseRecyclerOptions.Builder<StoreRecruiterProfile>()
                .setQuery(userRef, new SnapshotParser<StoreRecruiterProfile>() {
                    @NonNull
                    @Override
                    public StoreRecruiterProfile parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new StoreRecruiterProfile(snapshot.child("profile").child("fullname").getValue().toString(),
                                snapshot.child("profile").child("email").getValue().toString(),
                                snapshot.child("profile").child("phone").getValue().toString(),
                                snapshot.child("profile").child("companyname").getValue().toString(),
                                snapshot.child("profile").child("companylocation").getValue().toString(),
                                snapshot.child("profile").child("fieldsOfWork").getValue().toString()
                        );
                    }
                })
                .build();

        FirebaseRecyclerAdapter<StoreRecruiterProfile,AdminViewCompanyViewHolder> adapter = new FirebaseRecyclerAdapter<StoreRecruiterProfile, AdminViewCompanyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminViewCompanyViewHolder adminViewCompanyViewHolder, int i, @NonNull StoreRecruiterProfile storeRecruiterProfile) {
                adminViewCompanyViewHolder.fullname.setText(storeRecruiterProfile.getFullname());
                adminViewCompanyViewHolder.email.setText("Email : "+storeRecruiterProfile.getEmail());
                adminViewCompanyViewHolder.phone.setText("Phone : "+storeRecruiterProfile.getPhone());
                adminViewCompanyViewHolder.companyname.setText("Company Name : "+storeRecruiterProfile.getCompanyname());
                adminViewCompanyViewHolder.companylocation.setText("Company Location : "+storeRecruiterProfile.getCompanylocation());


            }

            @NonNull
            @Override
            public AdminViewCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewcompany_admin,parent,false);
                AdminViewCompanyViewHolder holder = new AdminViewCompanyViewHolder(view);
                return holder;
            }
        };
        viewCompanyRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}