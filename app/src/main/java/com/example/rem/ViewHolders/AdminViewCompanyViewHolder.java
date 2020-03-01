package com.example.rem.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rem.R;

public class AdminViewCompanyViewHolder extends RecyclerView.ViewHolder{
    public TextView fullname, email, phone, companyname,companylocation;
    public AdminViewCompanyViewHolder(@NonNull View itemView) {
        super(itemView);

        fullname = itemView.findViewById(R.id.admin_viewcompany_card_name);
        email = itemView.findViewById(R.id.admin_viewcompany_card_email);
        phone = itemView.findViewById(R.id.admin_viewcompany_card_phone);
        companyname = itemView.findViewById(R.id.admin_viewcompany_card_companyname);
        companylocation = itemView.findViewById(R.id.admin_viewcompany_card_companyloaction);
    }
}
