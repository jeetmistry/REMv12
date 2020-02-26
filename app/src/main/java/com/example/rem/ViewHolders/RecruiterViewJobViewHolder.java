package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class RecruiterViewJobViewHolder extends RecyclerView.ViewHolder {
    public TextView companyName, companyDescription,jobPost,workingtype;
    public RecruiterViewJobViewHolder(@NonNull View itemView) {
        super(itemView);
        companyName = itemView.findViewById(R.id.recruiter_viewjobs_card_companyname);
        companyDescription = itemView.findViewById(R.id.recruiter_viewjobs_card_companydescription);
        jobPost = itemView.findViewById(R.id.recruiter_viewjobs_card_jobpost);
        workingtype = itemView.findViewById(R.id.recruiter_viewjobs_card_workingtype);
    }
}
