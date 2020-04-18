package com.example.medicalapp.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.MainActivity;
import com.example.medicalapp.R;

import java.util.ArrayList;

public class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewAdapter.PatientViewHolder> implements Filterable {
    ArrayList<Users> PatientNamesList;
    Context context;
    int image = R.drawable.ic_account_circle_black_24dp;
    ArrayList<Users> PatientNamesListFull;
   // String PatientNamesList[];

    public PatientRecyclerViewAdapter(Context ct, ArrayList<Users> s1){
        context = ct;
        PatientNamesList = new ArrayList<>(s1);
        PatientNamesListFull = new ArrayList<>(PatientNamesList);
    }


    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.names_row, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        holder.patient_names.setText(PatientNamesList.get(position).Name);
        holder.imageView.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return PatientNamesList.size();
    }

    @Override
    public Filter getFilter() {
        return patientNameFilter;
    }
    Filter patientNameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Users> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(PatientNamesListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Users user: PatientNamesListFull){
                    if(user.getName().toLowerCase().startsWith(filterPattern)){
                        filteredList.add(user);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            PatientNamesList.clear();
            PatientNamesList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patient_names;
        ImageView imageView;
        public PatientViewHolder(@NonNull final View itemView) {
            super(itemView);
            patient_names = itemView.findViewById(R.id.names);
            imageView = itemView.findViewById(R.id.image_next_to_name);
            final CardView cardView = (CardView)itemView.findViewById(R.id.Names);
            if(cardView==null){
                Log.d("Null","Null");
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Log.d("Click", "Works");
                    String clickedID = PatientNamesList.get(getAdapterPosition()).getUid();


                    //Inflate New View
                    MainActivity currentActivity = (MainActivity) v.getContext();
                    currentActivity.openFragment(PatientProfileFragment.newInstance(clickedID));
                }
            });
        }
    }
}
