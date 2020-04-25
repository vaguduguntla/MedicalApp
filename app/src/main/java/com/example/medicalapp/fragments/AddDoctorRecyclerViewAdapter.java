package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Doctor;
import com.example.medicalapp.R;

import java.util.ArrayList;

public class AddDoctorRecyclerViewAdapter extends RecyclerView.Adapter<AddDoctorRecyclerViewAdapter.AddDoctorViewHolder> implements Filterable {
    ArrayList<Doctor> DoctorList;
    ArrayList<Doctor> AllDoctorsListFull;
    Context context;
    int img = R.drawable.ic_add_box_black_24dp;
    public AddDoctorRecyclerViewAdapter(Context ct, ArrayList<Doctor> AddDoctorList){
        this.DoctorList = AddDoctorList;
        AllDoctorsListFull = new ArrayList<>(DoctorList);
        this.context = ct;

    }

    @NonNull
    @Override
    public AddDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.add_doctor_row,parent,false);
        return new AddDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddDoctorViewHolder holder, int position) {
        holder.drName.setText(DoctorList.get(position).getName());
        holder.specialty.setText(DoctorList.get(position).getSpecialty());
        holder.img.setImageResource(img);

    }

    @Override
    public int getItemCount() {
        return DoctorList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Doctor> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(AllDoctorsListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Doctor doctor: AllDoctorsListFull){
                    if(doctor.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(doctor);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            DoctorList.clear();
            DoctorList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public class AddDoctorViewHolder extends RecyclerView.ViewHolder {
        TextView drName, specialty;
        ImageView img;


        public AddDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            drName = itemView.findViewById(R.id.add_doctor_names);
            specialty = itemView.findViewById(R.id.add_doctor_specialty_row_textView);
            img = itemView.findViewById(R.id.image_next_to_name);

        }
    }
}
