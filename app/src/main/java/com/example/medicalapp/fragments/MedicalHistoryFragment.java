package com.example.medicalapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.CalendarActivity;
import com.example.medicalapp.MedicalHistory;
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
    private String hid;

    RecyclerView recyclerView;
    ArrayList<MedicalHistory> MedicalHistoryList = new ArrayList<>();
    boolean startdateactivity = false;
    boolean enddateactivity = false;
    Intent startDateIntent;
    Intent endDateIntent;



    public MedicalHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param hid Parameter 1.

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalHistoryFragment newInstance(String hid) {
        MedicalHistoryFragment fragment = new MedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, hid);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hid = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_history_medications, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        okhttp ok =  new okhttp();
        ok.appendUrl("all_history_pid="+hid);
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
        TextView startDate = view.findViewById(R.id.SD_MHM);
        TextView endDate = view.findViewById(R.id.ED_MHM);
        startDate.setText(startDateIntent.getStringExtra("date"));
        endDate.setText(endDateIntent.getStringExtra("date"));



        startCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivity(intent);
                startDateIntent = getActivity().getIntent();

            }

        });

        endCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivity(intent);
                endDateIntent = getActivity().getIntent();

            }
        });

    }
}
