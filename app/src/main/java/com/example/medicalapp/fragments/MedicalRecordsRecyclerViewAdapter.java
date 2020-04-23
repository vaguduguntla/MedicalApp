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
import java.util.HashMap;

public class MedicalRecordsRecyclerViewAdapter extends RecyclerView.Adapter<MedicalRecordsRecyclerViewAdapter.MedicalRecordsViewHolder> implements Filterable {
    ArrayList<MedicalRecord> AllMedicalRecordsList;
    ArrayList<MedicalRecord> MedicalRecordsList;
    Context context;

    String recordType;
    String doctorName;

    private HashMap<String,ArrayList<MedicalRecord>> doctor_names_to_records = new HashMap<>();
    private HashMap<String,ArrayList<MedicalRecord>> type_to_record = new HashMap<>();

    public MedicalRecordsRecyclerViewAdapter(Context ct, ArrayList<MedicalRecord> List, HashMap<String,ArrayList<MedicalRecord>> doc_names, HashMap<String,ArrayList<MedicalRecord>> types){
        context = ct;
        AllMedicalRecordsList = List;
        MedicalRecordsList = List;
        doctor_names_to_records = doc_names;
        type_to_record = types;
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
        return medicalRecordFilter;
    }

    Filter medicalRecordFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint1) {

            FilterResults results = new FilterResults();

            if (recordType.equals("Any Type") && doctorName.equals("Any Doctor")) {
                results.values = AllMedicalRecordsList;
            }
            else if (recordType.equals("Any Type")) {
                results.values = doctor_names_to_records.get(doctorName);
            }
            else if (doctorName.equals("Any Doctor")) {
                results.values = type_to_record.get(recordType);
            }
            else {
                ArrayList<MedicalRecord> resultSet_1 = doctor_names_to_records.get(doctorName);

                ArrayList<MedicalRecord> resultSet_2 = type_to_record.get(recordType);

                resultSet_1.retainAll(resultSet_2);

                results.values = resultSet_1;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            MedicalRecordsList = (ArrayList) results.values;
            notifyDataSetChanged();
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
