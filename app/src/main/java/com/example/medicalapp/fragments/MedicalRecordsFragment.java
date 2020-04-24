package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.MainActivity;
import com.example.medicalapp.MedicalRecord;
import com.example.medicalapp.Patient;
import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MedicalRecordsFragment extends PatientProfileFragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "rid";

    // TODO: Rename and change types of parameters
    private Patient patient;

    public MedicalRecordsFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    //String n1[], n2[], n3[], n4[];

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param rid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalRecordsFragment newInstance(Patient p) {
        MedicalRecordsFragment fragment = new MedicalRecordsFragment();
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
        return inflater.inflate(R.layout.medical_records_page_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        ArrayList<String> doctor_names = new ArrayList<String>();

        ArrayList<MedicalRecord> MedicalRecordsList = new ArrayList<MedicalRecord>();

        HashMap<String,ArrayList<MedicalRecord>> doctor_names_to_records = new HashMap<>();
        HashMap<String,ArrayList<MedicalRecord>> type_to_record = new HashMap<>();

        doctor_names.add("Any Doctor");

        type_to_record.put("Report",new ArrayList<MedicalRecord>());
        type_to_record.put("Lab",new ArrayList<MedicalRecord>());
        type_to_record.put("Doctors Notes",new ArrayList<MedicalRecord>());

        okhttp ok = new okhttp();
        ok.appendUrl("all_records_pid="+patient.getPID());

        try {
            String[] data = ok.run_request_and_handle_response(null);
            JSONArray jsonArr = new JSONArray(data[0]);
            for (int i=0;i<jsonArr.length();++i) {

                MedicalRecord newRecord = new MedicalRecord(jsonArr.getJSONObject(i).getString("rid"),
                        jsonArr.getJSONObject(i).getString("rtype"),
                        jsonArr.getJSONObject(i).getString("rname"),
                        jsonArr.getJSONObject(i).getString("date"),
                        jsonArr.getJSONObject(i).getString("name")
                );

                MedicalRecordsList.add(newRecord);

                type_to_record.get(jsonArr.getJSONObject(i).getString("rtype")).add(newRecord);

                if (doctor_names_to_records.containsKey(newRecord.getDoctor())) {
                    doctor_names_to_records.get(newRecord.getDoctor()).add(newRecord);
                }
                else {
                    doctor_names.add(newRecord.getDoctor());
                    doctor_names_to_records.put(newRecord.getDoctor(),new ArrayList<MedicalRecord>(Arrays.asList(newRecord)));
                }
            }


        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.medical_records_recycler_view);
        MedicalRecordsRecyclerViewAdapter adapter = new MedicalRecordsRecyclerViewAdapter(getContext(),MedicalRecordsList, doctor_names_to_records, type_to_record);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Spinner doctor_name_spinner = (Spinner) view.findViewById(R.id.doctor_spinner);

        ArrayAdapter<String> spinnerArrayAdaptor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, doctor_names);

        spinnerArrayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        doctor_name_spinner.setAdapter(spinnerArrayAdaptor);


        AdapterView doctor_name_filter = (AdapterView)doctor_name_spinner;
        doctor_name_filter.setOnItemSelectedListener(this);
        AdapterView record_type_filter = (AdapterView) view.findViewById(R.id.type_spinner);
        record_type_filter.setOnItemSelectedListener(this);

        Button addRecordButton = view.findViewById(R.id.add_record_button);

        addRecordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity currentActivity = (MainActivity) v.getContext();
                currentActivity.openFragment(AddNewRecordFragment.newInstance(patient));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Log.d("Item_selected:",item);

        /*if (parent.getId() == R.id.type_spinner) {
            //if (item == "All")
        }
        else {

        }*/

        String record_type = ((Spinner) (getView().findViewById(R.id.type_spinner))).getSelectedItem().toString();

        String doctor_name  = ((Spinner) (getView().findViewById(R.id.doctor_spinner))).getSelectedItem().toString();

        Log.d("Type", record_type);
        Log.d("Doctor", doctor_name);

        ((MedicalRecordsRecyclerViewAdapter)recyclerView.getAdapter()).setDoctorName(doctor_name);
        ((MedicalRecordsRecyclerViewAdapter)recyclerView.getAdapter()).setRecordType(record_type);


        ((MedicalRecordsRecyclerViewAdapter) recyclerView.getAdapter()).getFilter().filter(null);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
