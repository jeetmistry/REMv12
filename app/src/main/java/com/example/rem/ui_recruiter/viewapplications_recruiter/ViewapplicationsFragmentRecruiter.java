package com.example.rem.ui_recruiter.viewapplications_recruiter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.Model.ViewApplicationsStudent;
import com.example.rem.R;
import com.example.rem.ViewHolders.RecruiterViewApplicationViewHolder;
import com.example.rem.ui_recruiter.RecruiterApplicationCardClickActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewapplicationsFragmentRecruiter extends Fragment {

    private RecyclerView applicationsRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef,userIdRef,appliedJobsRef;
    String userid;
    private ViewapplicationsViewModelRecruiter viewapplicationsViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewapplicationsViewModelRecruiter =
                ViewModelProviders.of(this).get(ViewapplicationsViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_viewapplications_recruiter, container, false);

        applicationsRecycler = root.findViewById(R.id.recyclerView_viewapplications_recruiter);
        layoutManager =new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(layoutManager);
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userIdRef = userRef.child("KIuCq2YHdSgo353RAwZ4wR91Jpd2");
        appliedJobsRef = userIdRef.child("Applied Applications");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerOptions<ViewApplicationsStudent> options = new FirebaseRecyclerOptions.Builder<ViewApplicationsStudent>()
                .setQuery(appliedJobsRef,ViewApplicationsStudent.class)
                .build();

        final FirebaseRecyclerAdapter<ViewApplicationsStudent, RecruiterViewApplicationViewHolder> adapter = new FirebaseRecyclerAdapter<ViewApplicationsStudent, RecruiterViewApplicationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final RecruiterViewApplicationViewHolder recruiterViewApplicationViewHolder, final int i, @NonNull final ViewApplicationsStudent viewApplicationsStudent) {
                recruiterViewApplicationViewHolder.jobPost.setText(viewApplicationsStudent.getJobpost());
                recruiterViewApplicationViewHolder.companyName.setText("Company Name : "+viewApplicationsStudent.getCompanyname());
                recruiterViewApplicationViewHolder.companyDescription.setText("Company Description : "+viewApplicationsStudent.getCompanydescription());
                recruiterViewApplicationViewHolder.workingtype.setText("Working Type : "+viewApplicationsStudent.getWorkingtype());
                recruiterViewApplicationViewHolder.status.setText("Status : "+viewApplicationsStudent.getStatus());

                recruiterViewApplicationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), RecruiterApplicationCardClickActivity.class);
                        intent.putExtra("job post",viewApplicationsStudent.getJobpost());
                        intent.putExtra("company name",viewApplicationsStudent.getCompanyname());
                        intent.putExtra("company description",viewApplicationsStudent.getCompanydescription());
                        intent.putExtra("working type",viewApplicationsStudent.getWorkingtype());
                        intent.putExtra("status",viewApplicationsStudent.getStatus());

                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public RecruiterViewApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewapplications_recruiter,parent,false);
                RecruiterViewApplicationViewHolder holder = new RecruiterViewApplicationViewHolder(view);
                return  holder;
            }
        };
        applicationsRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}
