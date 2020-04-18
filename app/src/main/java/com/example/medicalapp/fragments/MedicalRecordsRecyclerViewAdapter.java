package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;

import java.util.ArrayList;

public class MedicalRecordsRecyclerViewAdapter extends RecyclerView.Adapter<MedicalRecordsRecyclerViewAdapter.MedicalRecordsViewHolder> {
    ArrayList<MedicalRecord> MedicalRecordsList;
    Context context;
    public MedicalRecordsRecyclerViewAdapter(Context ct, ArrayList<MedicalRecord> List){
        context = ct;
        MedicalRecordsList = new ArrayList<>(List);
    }

    @NonNull
    @Override
    public MedicalRecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.medical_records_row, parent, false);
        return new MedicalRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordsViewHolder holder, int position) {
        holder.NameText.setText(MedicalRecordsList.get(position).Name);
        holder.DoctorText.setText(MedicalRecordsList.get(position).Doctor);
        holder.DateText.setText(MedicalRecordsList.get(position).Date);

    }

    @Override
    public int getItemCount() {
        return MedicalRecordsList.size();
    }

    public class MedicalRecordsViewHolder extends RecyclerView.ViewHolder {
        TextView NameText, DoctorText, DateText;
        public MedicalRecordsViewHolder(@NonNull View itemView) {
            super(itemView);
            NameText = itemView.findViewById(R.id.Record_Name);
            DoctorText = itemView.findViewById(R.id.Doctor);
            DateText = itemView.findViewById(R.id.Date);
        }
    }
}
