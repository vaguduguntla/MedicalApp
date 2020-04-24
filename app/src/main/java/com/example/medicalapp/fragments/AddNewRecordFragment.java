package com.example.medicalapp.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.medicalapp.CalendarActivity;
import com.example.medicalapp.Doctor;
import com.example.medicalapp.MainActivity;
import com.example.medicalapp.Patient;
import com.example.medicalapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewRecordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Patient patient;

    ArrayList<Doctor> allDoctorsList;
    TextView displayDate;
    ImageView calendarImage;
    DatePickerDialog.OnDateSetListener dateSetListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewRecordFragment() {
        // Required empty public constructor
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewRecordFragment newInstance(Patient p) {
        AddNewRecordFragment fragment = new AddNewRecordFragment();
        fragment.setPatient(p);
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
        return inflater.inflate(R.layout.fragment_add_new_record, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //date stuff
        displayDate = view.findViewById(R.id.add_record_date_edit_text);
        calendarImage = view.findViewById(R.id.calendar);
        Intent incomingIntent = getActivity().getIntent();
        displayDate.setText(incomingIntent.getStringExtra("date"));

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                intent.putExtra("AddPatientfragmentBundle",savedInstanceState);
                startActivity(intent);
            }
        });

        //doctor name autocomplete adapter stuff
        /*AutoCompleteTextView doctorNameEditText = view.findViewById(R.id.add_record_doc_name_inputField);
        AutoCompleteDrNameAdapter adapter = new AutoCompleteDrNameAdapter(getContext(), allDoctorsList);
        doctorNameEditText.setAdapter(adapter);*/

        TextView doctorName = view.findViewById(R.id.add_record_doc_name_inputField);
        doctorName.setText(((MainActivity)getActivity()).rootDoctor.getName());
    }
}
