package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.medicalapp.MainActivity;
import com.example.medicalapp.R;

public class PatientProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pid";


    // TODO: Rename and change types of parameters
    private String pid;


    public PatientProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientProfileFragment newInstance(String pid) {
        PatientProfileFragment fragment = new PatientProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, pid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pid = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Button medicalRecordsButton = view.findViewById(R.id.Medical_Records_Button);
        Button medicalHistoryButton = view.findViewById(R.id.Medical_History_Button);
        medicalRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d("Click", pid);
                //Inflate New View
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.openFragment(MedicalRecordsFragment.newInstance(""/*mid*/));
            }
        });
        medicalHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d("Click", pid);
                //Inflate New View
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.openFragment(MedicalHistoryFragment.newInstance(""));
            }
        });
    }
}
