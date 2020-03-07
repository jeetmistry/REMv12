package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class RecruiterViewApplicationViewHolder extends RecyclerView.ViewHolder {
    public TextView companyName, companyDescription,jobPost,workingtype,status;
    public View itemView;
    public RecruiterViewApplicationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        jobPost = itemView.findViewById(R.id.recruiter_viewapplications_card_jobpost);
        companyName = itemView.findViewById(R.id.recruiter_viewapplications_card_companyname);
        companyDescription = itemView.findViewById(R.id.recruiter_viewapplications_card_companydescription);
        workingtype = itemView.findViewById(R.id.recruiter_viewapplications_card_workingtype);
        status = itemView.findViewById(R.id.recruiter_viewapplications_card_status);
    }
}
