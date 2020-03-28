package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;

public class MedicalRecordsRecyclerViewAdapter extends RecyclerView.Adapter<MedicalRecordsRecyclerViewAdapter.MedicalRecordsViewHolder> {
    String recordName[],recordType[],Date[];
    Context context;
    public MedicalRecordsRecyclerViewAdapter(Context ct, String s1[], String s2[], String s3[]){
        context = ct;
        recordName = s1;
        recordType = s2;
        Date = s3;
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
        holder.t1.setText(recordName[position]);
        holder.t2.setText(recordType[position]);
        holder.t3.setText(Date[position]);

    }

    @Override
    public int getItemCount() {
        return recordName.length;
    }

    public class MedicalRecordsViewHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;
        public MedicalRecordsViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.Record_Name);
            t2 = itemView.findViewById(R.id.Type);
            t3 = itemView.findViewById(R.id.Date);
        }
    }
}
