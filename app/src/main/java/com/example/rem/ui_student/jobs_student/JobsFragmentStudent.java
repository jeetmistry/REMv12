package com.example.rem.ui_student.jobs_student;

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

import com.example.rem.Model.ViewJobsStudent;
import com.example.rem.R;
import com.example.rem.ViewHolders.StudentViewJobViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobsFragmentStudent extends Fragment {
    private RecyclerView viewjobRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef;
    private JobsViewModelStudent jobsViewModelStudent;
    StudentViewJobViewHolder studentViewJobViewHolder ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jobsViewModelStudent =
                ViewModelProviders.of(this).get(JobsViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_jobs_student, container, false);
        final TextView textView = root.findViewById(R.id.text_jobs_student);
        viewjobRecycler = root.findViewById(R.id.recyclerView_jobs_student);
        layoutManager =new LinearLayoutManager(getContext());
        viewjobRecycler.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("recruiter");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ViewJobsStudent> options = new FirebaseRecyclerOptions.Builder<ViewJobsStudent>()
                .setQuery(userRef, new SnapshotParser<ViewJobsStudent>() {
                    @NonNull
                    @Override
                    public ViewJobsStudent parseSnapshot(@NonNull DataSnapshot snapshot) {
                        String key = userRef.child(snapshot.getKey()).child("Jobs").getKey();

                        return new ViewJobsStudent(snapshot.child(snapshot.getKey()).child("Jobs").child(key).child("companyname").getValue().toString(),
                                snapshot.child(snapshot.getKey()).child("Jobs").child(key).child("companydescription").getValue().toString(),
                                snapshot.child(snapshot.getKey()).child("Jobs").child(key).child(snapshot.getKey()).child("jobpost").getValue().toString(),
                                snapshot.child(snapshot.getKey()).child("Jobs").child(key).child("workingtype").getValue().toString());
                    }
                })
                .build();

        FirebaseRecyclerAdapter<ViewJobsStudent, StudentViewJobViewHolder> adapter = new FirebaseRecyclerAdapter<ViewJobsStudent, StudentViewJobViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StudentViewJobViewHolder studentViewJobViewHolder, int i, @NonNull ViewJobsStudent viewJobsStudent) {
                studentViewJobViewHolder.companyName.setText("Company Name : "+viewJobsStudent.getCompanyname());
                studentViewJobViewHolder.jobPost.setText(viewJobsStudent.getJobpost());
                studentViewJobViewHolder.companyDescription.setText("Company Description : "+viewJobsStudent.getCompanydescription());
                studentViewJobViewHolder.workingtype.setText("Working Type : "+viewJobsStudent.getWorkingtype());
            }

            @NonNull
            @Override
            public StudentViewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student_viewjobs,parent,false);
                StudentViewJobViewHolder holder = new StudentViewJobViewHolder(view);
                return holder;
            }
        };
        viewjobRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}