package com.example.rem.ui_recruiter.viewjobs_recruiter;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.Model.ViewJobsRecruiter;
import com.example.rem.R;
import com.example.rem.ViewHolders.RecruiterViewJobViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewjobsFragmentRecruiter extends Fragment {

    private RecyclerView viewjobrecruiter;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef,userIdRef,jobRef,deletedJobRef;
    private String userid;

    private ViewjobsViewModelRecruiter viewjobsViewModelRecruiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewjobsViewModelRecruiter =
                ViewModelProviders.of(this).get(ViewjobsViewModelRecruiter.class);
        View root = inflater.inflate(R.layout.fragment_viewjobs_recruiter, container, false);
        final TextView textView = root.findViewById(R.id.text_viewjobs_recruiter);
        firebaseAuth = FirebaseAuth.getInstance();
        viewjobrecruiter = root.findViewById(R.id.recyclerView_viewjobs_recruiter);
        layoutManager =new LinearLayoutManager(getContext());
        viewjobrecruiter.setLayoutManager(layoutManager);
        userid = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();
        userRef = rootRef.child("recruiter");
        userIdRef= userRef.child(userid);
        jobRef = userIdRef.child("Jobs");
        deletedJobRef = userIdRef.child("Deleted Jobs");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ViewJobsRecruiter> options= new
                FirebaseRecyclerOptions.Builder<ViewJobsRecruiter>()
                .setQuery(jobRef,ViewJobsRecruiter.class)
                .build();
        FirebaseRecyclerAdapter<ViewJobsRecruiter, RecruiterViewJobViewHolder> adapter =new FirebaseRecyclerAdapter<ViewJobsRecruiter, RecruiterViewJobViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final RecruiterViewJobViewHolder recruiterViewJobViewHolder, final int i, @NonNull final ViewJobsRecruiter viewJobsRecruiter) {

                recruiterViewJobViewHolder.jobPost.setText(viewJobsRecruiter.getJobpost());
                recruiterViewJobViewHolder.companyName.setText("Company Name : "+viewJobsRecruiter.getCompanyname());
                recruiterViewJobViewHolder.companyDescription.setText("Description : "+viewJobsRecruiter.getCompanydescription());
                recruiterViewJobViewHolder.workingtype.setText(" Working Type : "+viewJobsRecruiter.getWorkingtype());

                recruiterViewJobViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Select your option");
                        String alertItem[] = {"Delete Job ","Cancel "};
                        builder.setItems(alertItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch(which){
                                    case 0:
                                        String key = getRef(i).getKey();

                                        //adding deleted job in deleted job child
                                        String job,companyname,companydescription,workingtype;
                                        job = viewJobsRecruiter.getJobpost();
                                        companyname = viewJobsRecruiter.getCompanyname();
                                        companydescription=viewJobsRecruiter.getCompanydescription();
                                        workingtype=viewJobsRecruiter.getWorkingtype();
                                        ViewJobsRecruiter vjr = new ViewJobsRecruiter(job,companyname,companydescription,workingtype);
                                        deletedJobRef.push().setValue(vjr);

                                        //deleting the current job
                                        jobRef.child(key).removeValue();
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

            @NonNull
            @Override
            public RecruiterViewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recruiter_viewjobs,parent,false);
            RecruiterViewJobViewHolder holder = new RecruiterViewJobViewHolder(view);
            return holder;
            }
        };
        viewjobrecruiter.setAdapter(adapter);
        adapter.startListening();
    }
}