package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.MedicalHistory;
import com.example.medicalapp.R;

import java.util.ArrayList;

public class MedicalHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MedicalHistoryRecyclerViewAdapter.MedicalHistoryViewHolder> {

    ArrayList<MedicalHistory> MedicalHistoryList;
    Context context;

    public MedicalHistoryRecyclerViewAdapter(Context ct, ArrayList<MedicalHistory> MedicalHistoryList){
        this.MedicalHistoryList = MedicalHistoryList;
        this.context = ct;
    }

    @NonNull
    @Override
    public MedicalHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medical_history_medications_row, parent, false);

        return new MedicalHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalHistoryViewHolder holder, int position) {
        holder.issue.setText(MedicalHistoryList.get(position).getIssue());
        holder.startDate.setText(MedicalHistoryList.get(position).getStartDate());
        holder.endDate.setText(MedicalHistoryList.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return MedicalHistoryList.size();
    }

    public class MedicalHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView issue, startDate, endDate;

        public MedicalHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            issue = itemView.findViewById(R.id.name_of_medication_or_issue_textView);
            startDate = itemView.findViewById(R.id.start_date_textView);
            endDate = itemView.findViewById(R.id.end_date_textView);

        }
    }
}
