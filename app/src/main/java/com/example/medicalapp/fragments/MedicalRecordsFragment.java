package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;

import java.util.ArrayList;

public class MedicalRecordsFragment extends PatientProfileFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "mid";


    // TODO: Rename and change types of parameters
    private String mid;


    public MedicalRecordsFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    String n1[], n2[], n3[], n4[];

    ArrayList<MedicalRecord> MedicalRecordsList = new ArrayList<MedicalRecord>();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalRecordsFragment newInstance(String mid) {
        MedicalRecordsFragment fragment = new MedicalRecordsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mid);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mid = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.medical_records_page_fragment, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        n1 = getResources().getStringArray(R.array.record_name);
        n2 = getResources().getStringArray(R.array.type_of_record);
        n3 = getResources().getStringArray(R.array.record_date);
        n4 = getResources().getStringArray(R.array.doctor_names);

        for(int i =0; i<n1.length; i++){
            MedicalRecordsList.add(new MedicalRecord("1", null,n1[i],n3[i], n4[i]));
        }

        recyclerView = view.findViewById(R.id.medical_records_recycler_view);
        MedicalRecordsRecyclerViewAdapter adapter = new MedicalRecordsRecyclerViewAdapter(getContext(),MedicalRecordsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
