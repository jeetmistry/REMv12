package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class AdminViewStudentViewHolder extends RecyclerView.ViewHolder {
    public TextView fullname, email, phone, city, qualification, collegename, passingyear;
    public AdminViewStudentViewHolder(@NonNull View itemView) {
        super(itemView);

        fullname = itemView.findViewById(R.id.admin_viewstudent_card_name);
        email = itemView.findViewById(R.id.admin_viewstudent_card_email);
        phone = itemView.findViewById(R.id.admin_viewstudent_card_phone);
        city = itemView.findViewById(R.id.admin_viewstudent_card_city);
        qualification = itemView.findViewById(R.id.admin_viewstudent_card_qualification);
        collegename = itemView.findViewById(R.id.admin_viewstudent_card_collegename);
        passingyear = itemView.findViewById(R.id.admin_viewstudent_card_passingyear);
    }
}
