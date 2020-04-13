package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;

import java.util.ArrayList;

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<DoctorRecyclerViewAdapter.DoctorViewHolder> {
    ArrayList<Users> DoctorNamesList;
    int image;
    Context context;

    public DoctorRecyclerViewAdapter(ArrayList<Users> s1, Context ct, int img){
        image = img;
        context = ct;
        DoctorNamesList = s1;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.names_row, parent, false);

        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.doctors_names.setText(DoctorNamesList.get(position).Name);
        holder.imageView.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return DoctorNamesList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctors_names;
        ImageView imageView;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctors_names = itemView.findViewById(R.id.names);
            imageView = itemView.findViewById(R.id.image_next_to_name);
        }
    }



}
