package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Medication;
import com.example.medicalapp.R;

import java.util.ArrayList;

public class MedicationsRecyclerViewAdapter extends RecyclerView.Adapter<MedicationsRecyclerViewAdapter.MedicationsViewHolder> {
    ArrayList<Medication> MedicationsList;
    Context context;
    public MedicationsRecyclerViewAdapter(Context ct, ArrayList<Medication> List){
        context = ct;
        MedicationsList = List;
    }

    @NonNull
    @Override
    public MedicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.medical_history_medications_row, parent, false);
        return new MedicationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationsViewHolder holder, int position) {
        holder.NameText.setText(MedicationsList.get(position).getName());
        holder.startDateText.setText(MedicationsList.get(position).getStartDate());
        holder.endDateText.setText(MedicationsList.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return MedicationsList.size();
    }

    public class MedicationsViewHolder extends RecyclerView.ViewHolder {
        TextView NameText, startDateText, endDateText;
        public MedicationsViewHolder(@NonNull View itemView) {
            super(itemView);
            NameText = itemView.findViewById(R.id.name_of_medication_or_issue_textView);
            startDateText = itemView.findViewById(R.id.start_date_textView);
            endDateText = itemView.findViewById(R.id.end_date_textView);
        }
    }
}
