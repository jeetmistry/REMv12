package com.example.rem.ui_admin.viewfeedback_admin;

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

import com.example.rem.Model.ViewFeedbackAdmin;
import com.example.rem.R;
import com.example.rem.ViewHolders.AdminFeedbackViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewfeedbacksFragmentAdmin extends Fragment {
    private RecyclerView viewFeedbackRecycler;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef,userRef;
    private ViewfeedbacksViewModelAdmin viewfeedbacksViewModelAdmin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewfeedbacksViewModelAdmin =
                ViewModelProviders.of(this).get(ViewfeedbacksViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_viewfeedback_admin, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        viewFeedbackRecycler = root.findViewById(R.id.recyclerView_viewfeedback_admin);
        layoutManager =new LinearLayoutManager(getContext());
        viewFeedbackRecycler.setLayoutManager(layoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ViewFeedbackAdmin> options = new FirebaseRecyclerOptions.Builder<ViewFeedbackAdmin>()
                .setQuery(rootRef.child("student"), new SnapshotParser<ViewFeedbackAdmin>() {
                    @NonNull
                    @Override
                    public ViewFeedbackAdmin parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new ViewFeedbackAdmin(snapshot.child("profile").child("name").getValue().toString(),
                                snapshot.child("feedbacks").child("feedback").getValue().toString()
                                );
                    }
                })
                .build();

        FirebaseRecyclerAdapter<ViewFeedbackAdmin, AdminFeedbackViewHolder> adapter = new FirebaseRecyclerAdapter<ViewFeedbackAdmin, AdminFeedbackViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminFeedbackViewHolder adminFeedbackViewHolder, int i, @NonNull ViewFeedbackAdmin viewFeedbackAdmin) {
                adminFeedbackViewHolder.name.setText(viewFeedbackAdmin.getName());
                adminFeedbackViewHolder.feedback.setText("Feedback : "+viewFeedbackAdmin.getFeedback());
            }

            @NonNull
            @Override
            public AdminFeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewfeedback_admin,parent,false);
                AdminFeedbackViewHolder holder = new AdminFeedbackViewHolder(view);
                return  holder;
            }
        };
        viewFeedbackRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}