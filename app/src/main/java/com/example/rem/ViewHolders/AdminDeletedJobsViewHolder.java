package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class AdminDeletedJobsViewHolder extends RecyclerView.ViewHolder {
    public TextView companyName, companyDescription,jobPost,workingtype;
    public AdminDeletedJobsViewHolder(@NonNull View itemView) {
        super(itemView);
        jobPost = itemView.findViewById(R.id.admin_deletedjobs_card_jobpost);
        companyName = itemView.findViewById(R.id.admin_deletedjobs_card_companyname);
        companyDescription = itemView.findViewById(R.id.admin_deletedjobs_card_companydescription);
        workingtype = itemView.findViewById(R.id.admin_deletedjobs_card_workingtype);
    }
}
