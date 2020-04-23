package com.example.medicalapp.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.MedicalRecord;
import com.example.medicalapp.R;

import java.util.ArrayList;

public class MedicalRecordsRecyclerViewAdapter extends RecyclerView.Adapter<MedicalRecordsRecyclerViewAdapter.MedicalRecordsViewHolder> implements Filterable {
    ArrayList<MedicalRecord> MedicalRecordsList;
    Context context;

    String recordType;
    String doctorName;

    public MedicalRecordsRecyclerViewAdapter(Context ct, ArrayList<MedicalRecord> List){
        context = ct;
        MedicalRecordsList = new ArrayList<>(List);
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
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
        holder.NameText.setText(MedicalRecordsList.get(position).getName());
        holder.DoctorText.setText(MedicalRecordsList.get(position).getDoctor());
        holder.DateText.setText(MedicalRecordsList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return MedicalRecordsList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    Filter medicalRecordFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint1) {

            if (recordType.equals("Any Type") && doctorName.equals("Any Doctor")) {


            }
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }

    };

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
