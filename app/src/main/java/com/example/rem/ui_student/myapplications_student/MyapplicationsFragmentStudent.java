package com.example.rem.ui_student.myapplications_student;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.Model.ViewApplicationsStudent;
import com.example.rem.R;
import com.example.rem.ViewHolders.StudentViewMyApplicationsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyapplicationsFragmentStudent extends Fragment {

    private RecyclerView myapplicationsRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef,userIdRef,appliedJobsRef;
    String userid;

    private MyapplicationsViewModelStudent myapplicationsViewModelStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myapplicationsViewModelStudent =
                ViewModelProviders.of(this).get(MyapplicationsViewModelStudent.class);
        View root = inflater.inflate(R.layout.fragment_myapplications_student, container, false);
        myapplicationsRecycler = root.findViewById(R.id.recyclerView_myapplications_student);
        layoutManager =new LinearLayoutManager(getContext());
        myapplicationsRecycler.setLayoutManager(layoutManager);
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("student");
        userIdRef = userRef.child(userid);
        appliedJobsRef = userIdRef.child("Applied Applications");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ViewApplicationsStudent> options = new FirebaseRecyclerOptions.Builder<ViewApplicationsStudent>()
                .setQuery(appliedJobsRef,ViewApplicationsStudent.class)
                .build();

        FirebaseRecyclerAdapter<ViewApplicationsStudent,StudentViewMyApplicationsViewHolder> adapter = new FirebaseRecyclerAdapter<ViewApplicationsStudent, StudentViewMyApplicationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StudentViewMyApplicationsViewHolder studentViewMyApplicationsViewHolder, final int i, @NonNull ViewApplicationsStudent viewApplicationsStudent) {
                    if(studentViewMyApplicationsViewHolder.isRecyclable()) {
                        studentViewMyApplicationsViewHolder.jobPost.setText(viewApplicationsStudent.getJobpost());
                        studentViewMyApplicationsViewHolder.companyName.setText("Company Name : " + viewApplicationsStudent.getCompanyname());
                        studentViewMyApplicationsViewHolder.companyDescription.setText("Company Description : " + viewApplicationsStudent.getCompanydescription());
                        studentViewMyApplicationsViewHolder.workingtype.setText("Working Type : " + viewApplicationsStudent.getWorkingtype());
                        studentViewMyApplicationsViewHolder.status.setText("Status : " + viewApplicationsStudent.getStatus());

                        studentViewMyApplicationsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Select your option");
                                String alertItem[] = {"Delete Record "," Cancel "};
                                builder.setItems(alertItem, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch(which){
                                            case 0:
                                                String key = getRef(i).getKey();
                                                appliedJobsRef.child(key).removeValue();
                                                break;
                                            case 1:
                                                dialog.cancel();
                                        }

                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(getContext(), "No Applications applied", Toast.LENGTH_SHORT).show();
                    }
            }

            @NonNull
            @Override
            public StudentViewMyApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewapplications_student,parent,false);
                StudentViewMyApplicationsViewHolder holder = new StudentViewMyApplicationsViewHolder(view);
                return holder;
            }
        };
        myapplicationsRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}