package com.example.rem.ViewHolders;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;
import com.example.rem.ui_student.StudentJobCardClickActivity;

public class StudentViewJobViewHolder extends RecyclerView.ViewHolder {
    public TextView companyName, companyDescription,jobPost,workingtype;
    public View itemView;
    public StudentViewJobViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        companyName = itemView.findViewById(R.id.student_viewjobs_card_companyname);
        companyDescription = itemView.findViewById(R.id.student_viewjobs_card_companydescription);
        jobPost = itemView.findViewById(R.id.student_viewjobs_card_jobpost);
        workingtype = itemView.findViewById(R.id.student_viewjobs_card_workingtype);

    }
}
