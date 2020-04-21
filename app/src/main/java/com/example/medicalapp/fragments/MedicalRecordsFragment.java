package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MedicalRecordsFragment extends PatientProfileFragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "rid";

    // TODO: Rename and change types of parameters
    private String rid;

    private HashMap<String,ArrayList<MedicalRecord>> doctor_names_to_records = new HashMap<>();
    private HashMap<String,ArrayList<MedicalRecord>> type_to_record = new HashMap<>();

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
     * @param rid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalRecordsFragment newInstance(String rid) {
        MedicalRecordsFragment fragment = new MedicalRecordsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, rid);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rid = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.medical_records_page_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        ArrayList<String> doctor_names = new ArrayList<String>();

        type_to_record.put("Report",new ArrayList<MedicalRecord>());
        type_to_record.put("Lab",new ArrayList<MedicalRecord>());
        type_to_record.put("Doctors Notes",new ArrayList<MedicalRecord>());

        okhttp ok = new okhttp();
        ok.appendUrl("all_records_pid="+rid);

        try {
            String[] data = ok.run_request_and_handle_response(null);
            JSONArray jsonArr = new JSONArray(data[0]);
            for (int i=0;i<jsonArr.length();++i) {

                MedicalRecord newRecord = new MedicalRecord(jsonArr.getJSONObject(i).getString("rid"),
                        jsonArr.getJSONObject(i).getString("rtype"),
                        jsonArr.getJSONObject(i).getString("rname"),
                        jsonArr.getJSONObject(i).getString("date"),
                        jsonArr.getJSONObject(i).getString("d")
                );

                MedicalRecordsList.add(newRecord);

                type_to_record.get(jsonArr.getJSONObject(i).getString("rtype")).add(newRecord);
            }
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        for(int i =0; i<n1.length; i++){
            MedicalRecord newRecord = new MedicalRecord("1", null,n1[i],n3[i], n4[i]);
            MedicalRecordsList.add(newRecord);
            doctor_names.add(n4[i]);
            type_to_record.get(n2[i]).add(newRecord);
            if (doctor_names_to_records.containsKey(n4[i])) {
                doctor_names_to_records.get(n4[i]).add(newRecord);
            }
            else {
                doctor_names_to_records.put(n4[i],new ArrayList<MedicalRecord>(Arrays.asList(newRecord)));
            }
        }

        recyclerView = view.findViewById(R.id.medical_records_recycler_view);
        MedicalRecordsRecyclerViewAdapter adapter = new MedicalRecordsRecyclerViewAdapter(getContext(),MedicalRecordsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Spinner doctor_name_spinner = (Spinner) view.findViewById(R.id.doctor_spinner);

        ArrayAdapter<String> spinnerArrayAdaptor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, doctor_names);

        spinnerArrayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        doctor_name_spinner.setAdapter(spinnerArrayAdaptor);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Log.d("Item_selected:",item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
