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

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<DoctorRecyclerViewAdapter.DoctorViewHolder> implements Filterable {
    ArrayList<Users> DoctorNamesList;
    ArrayList<Users> DoctorNamesListFull;
    int image;
    Context context;

    public DoctorRecyclerViewAdapter(ArrayList<Users> s1, Context ct, int img){
        image = img;
        context = ct;
        DoctorNamesList = s1;
        DoctorNamesListFull = new ArrayList<>(DoctorNamesList);
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.names_row, parent, false);

        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.doctors_names.setText(DoctorNamesList.get(position).Name);
        holder.imageView.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return DoctorNamesList.size();
    }

    @Override
    public Filter getFilter() {
        return doctorNameFilter;
    }
    Filter doctorNameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Users> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(DoctorNamesListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Users user: DoctorNamesListFull){
                    if(user.getName().toLowerCase().contains(filterPattern)){
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
            DoctorNamesList.clear();
            DoctorNamesList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctors_names;
        ImageView imageView;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctors_names = itemView.findViewById(R.id.names);
            imageView = itemView.findViewById(R.id.image_next_to_name);
            final CardView cardView = (CardView)itemView.findViewById(R.id.Names);
            if(cardView==null){
                Log.d("Null","Null");
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Log.d("Click", "Works");
                    String clickedID = DoctorNamesList.get(getAdapterPosition()).getUid();


                    //Inflate New View
                    MainActivity currentActivity = (MainActivity) v.getContext();
                    currentActivity.openFragment(DoctorProfileFragment.newInstance(clickedID));
                }
            });
        }

    }

}
