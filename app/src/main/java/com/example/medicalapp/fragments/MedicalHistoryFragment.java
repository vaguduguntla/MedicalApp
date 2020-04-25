package com.example.medicalapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.CalendarActivity;
import com.example.medicalapp.MedicalHistory;
import com.example.medicalapp.Patient;
import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicalHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "hid";


    // TODO: Rename and change types of parameters
    private Patient patient;

    RecyclerView recyclerView;
    ArrayList<MedicalHistory> MedicalHistoryList = new ArrayList<>();

    TextView startDate;
    TextView endDate;


    public MedicalHistoryFragment() {
        // Required empty public constructor
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param hid Parameter 1.

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalHistoryFragment newInstance(Patient p) {
        MedicalHistoryFragment fragment = new MedicalHistoryFragment();
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
        return inflater.inflate(R.layout.fragment_medical_history_medications, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        startDate = view.findViewById(R.id.SD_MHM);
        endDate = view.findViewById(R.id.ED_MHM);

        TextView ownershipText = view.findViewById(R.id.ownership_textView);
        ownershipText.setText(patient.getName() + "'s");
        TextView pageType = view.findViewById(R.id.page_type_textView);
        pageType.setText("Medical History");

        okhttp ok =  new okhttp();
        ok.appendUrl("all_history_pid="+patient.getPID());
        try{
            String data[] = ok.run_request_and_handle_response(null);
            JSONArray jsonArr = new JSONArray(data[0]);
            for (int i=0;i<jsonArr.length();++i) {
                MedicalHistoryList.add(new MedicalHistory(jsonArr.getJSONObject(i).getString("hid"),
                        jsonArr.getJSONObject(i).getString("description"), // "Person" + Integer.toString(i),
                        jsonArr.getJSONObject(i).getString("startDate"),
                        jsonArr.getJSONObject(i).getString("endDate")));
            }
        }
        catch (InterruptedException | JSONException e){
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.medical_history_medications_recyclerView);
        MedicalHistoryRecyclerViewAdapter adapter = new MedicalHistoryRecyclerViewAdapter(getContext(), MedicalHistoryList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Calendar Stuff
        ImageView startCalendar = view.findViewById(R.id.startDate_Calendar_Button);
        ImageView endCalendar = view.findViewById(R.id.End_Date_Calendar_Button);
        ImageView plusButton = view.findViewById(R.id.add_history_medication);

        Button addHistoryButton = view.findViewById(R.id.add_medicalHistory_medications_button);

        addHistoryButton.setText("Add Medical History Item");

        addHistoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getView().findViewById(R.id.add_history_medication_layout).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.medical_history_medications_recyclerView).setVisibility(View.INVISIBLE);
            }
        });
        Intent incomingIntent = getActivity().getIntent();

        if(startDate == null){
            startDate.setText(incomingIntent.getStringExtra("date"));
        }
        else{
            endDate.setText(incomingIntent.getStringExtra("date"));
        }

        startCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivity(intent);


            }

        });
        endCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivity(intent);


            }
        });


    }
}
