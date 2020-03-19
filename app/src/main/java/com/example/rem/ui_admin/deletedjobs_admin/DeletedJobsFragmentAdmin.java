package com.example.rem.ui_admin.deletedjobs_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.Model.ViewJobsRecruiter;
import com.example.rem.R;
import com.example.rem.ViewHolders.AdminDeletedJobsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeletedJobsFragmentAdmin extends Fragment {

    private RecyclerView viewjobRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef;

    private DeletedJobsViewModelAdmin deletedJobsViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deletedJobsViewModelAdmin =
                ViewModelProviders.of(this).get(DeletedJobsViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_deletedjobs_admin, container, false);
        viewjobRecycler = root.findViewById(R.id.recyclerView_deletedjobs_admin);
        layoutManager =new LinearLayoutManager(getContext());
        viewjobRecycler.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();

        //by entering manually userid
        userRef = rootRef.child("recruiter").child("32S2yattjRgpx2qraHstF5fQVH92").child("Deleted Jobs");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<ViewJobsRecruiter> options= new
                FirebaseRecyclerOptions.Builder<ViewJobsRecruiter>()
                .setQuery(userRef, new SnapshotParser<ViewJobsRecruiter>() {
                    @NonNull
                    @Override
                    public ViewJobsRecruiter parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new ViewJobsRecruiter(snapshot.child("jobpost").getValue().toString(),
                                snapshot.child("companyname").getValue().toString(),
                                snapshot.child("companydescription").getValue().toString(),
                                snapshot.child("workingtype").getValue().toString());
                    }
                })
                .build();
        FirebaseRecyclerAdapter<ViewJobsRecruiter, AdminDeletedJobsViewHolder> adapter = new FirebaseRecyclerAdapter<ViewJobsRecruiter, AdminDeletedJobsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminDeletedJobsViewHolder adminDeletedJobsViewHolder, int i, @NonNull ViewJobsRecruiter viewJobsRecruiter) {
                adminDeletedJobsViewHolder.jobPost.setText(viewJobsRecruiter.getJobpost());
                adminDeletedJobsViewHolder.companyName.setText("Company Name : "+viewJobsRecruiter.getCompanyname());
                adminDeletedJobsViewHolder.companyDescription.setText("Company Description : "+viewJobsRecruiter.getCompanydescription());
                adminDeletedJobsViewHolder.workingtype.setText("Working Type : "+viewJobsRecruiter.getWorkingtype());
            }

            @NonNull
            @Override
            public AdminDeletedJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.card_deletedjobs_admin,parent,false);
                AdminDeletedJobsViewHolder holder = new AdminDeletedJobsViewHolder(view);
                return holder;
            }
        };
        viewjobRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}