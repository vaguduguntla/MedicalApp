package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;


public class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewAdapter.PatientViewHolder> {
    MyPatientsFragment ct;
    Context context;
    String PatientNamesList[];

    public PatientRecyclerViewAdapter(Context ct, String[] s1){
        context = ct;
        PatientNamesList = s1;
    }
    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_names_row, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        holder.patient_names.setText(PatientNamesList[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patient_names;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_names = itemView.findViewById(R.id.patient_names);
        }
    }
}
