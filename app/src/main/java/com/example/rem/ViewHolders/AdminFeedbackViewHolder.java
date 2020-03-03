package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class AdminFeedbackViewHolder extends RecyclerView.ViewHolder {
    public TextView name,feedback;
    public AdminFeedbackViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.admin_viewfeedback_card_name);
        feedback = itemView.findViewById(R.id.admin_viewfeedback_card_feedback);
    }
}
