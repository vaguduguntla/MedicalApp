package com.example.medicalapp.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicalapp.Doctor;
import com.example.medicalapp.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteDrNameAdapter extends ArrayAdapter<Doctor> {
    List<Doctor> DrNamesListFull;

    public AutoCompleteDrNameAdapter(@NonNull Context context, @NonNull List<Doctor> DrNamesList) {
        super(context, 0, DrNamesList);
        DrNamesListFull = new ArrayList<>(DrNamesList);
    }
    @NonNull
    @Override
    public Filter getFilter(){
        return DrNamesFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.doctor_name_auto_fill_row, parent, false);
        }
        TextView drName = convertView.findViewById(R.id.add_record_doc_name_inputField);

        Doctor doctor = getItem(position);

        if(convertView != null){
            drName.setText(doctor.getName());
        }

        return convertView;
    }

    private Filter DrNamesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Doctor> Suggestions = new ArrayList<>();
            if(constraint == null || constraint.length() ==0){
                Suggestions.addAll(DrNamesListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Doctor doctor: DrNamesListFull){
                    if(doctor.getName().toLowerCase().contains(filterPattern)){
                        Suggestions.add(doctor);

                    }
                }
            }
            results.values = Suggestions;
            results.count = Suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Doctor)resultValue).getName();
        }
    };
}
