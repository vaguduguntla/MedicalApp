package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.medicalapp.MainActivity;
import com.example.medicalapp.Patient;
import com.example.medicalapp.R;
import com.example.medicalapp.Users;

public class PatientProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Patient patient;

    public PatientProfileFragment() {
        // Required empty public constructor
    }

    public void setPatientInfo(Patient patientInput) {
        patient = patientInput;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientProfileFragment newInstance(Patient patient) {
        PatientProfileFragment fragment = new PatientProfileFragment();
        fragment.setPatientInfo(patient);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Button medicationsButton = view.findViewById(R.id.Medications_Button);
        medicalRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Inflate New View
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.openFragment(MedicalRecordsFragment.newInstance(patient.getPID()));
            }
        });
        medicalHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Inflate New View
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.openFragment(MedicalHistoryFragment.newInstance(patient.getPID()));
            }
        });
        medicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Inflate New View
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.openFragment(MedicationsFragment.newInstance(patient.getPID()));
            }
        });

        TextView title = view.findViewById(R.id.patient_profile_title);
        title.setText(patient.getName() + "(" + patient.getGender() + ", " + patient.getAge() + ")");
    }
}
