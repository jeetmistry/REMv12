package com.example.rem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.rem.Model.Model;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private View view;
    private Object object;

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        this.view = view;
        this.object = object;
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for Student login activity
                if(models.get(position).getTitle().equals("Student")) {
                    Intent intent = new Intent(context, StudentLogin.class);
                    intent.putExtra("param", models.get(position).getTitle());
                    context.startActivity(intent);
                }

                //for Recruiter login activity
                if(models.get(position).getTitle().equals("Recruiter")) {
                    Intent intent = new Intent(context, RecruiterLogin.class);
                    intent.putExtra("param", models.get(position).getTitle());
                    context.startActivity(intent);
                }

                //for Admin login activity
                if(models.get(position).getTitle().equals("Admin")) {
                    Intent intent = new Intent(context, AdminLogin.class);
                    intent.putExtra("param", models.get(position).getTitle());
                    context.startActivity(intent);
                }

                //for About us activity
                if(models.get(position).getTitle().equals("About Us")) {
                    Intent intent = new Intent(context, AboutUs.class);
                    intent.putExtra("param", models.get(position).getTitle());
                    context.startActivity(intent);
                }
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
