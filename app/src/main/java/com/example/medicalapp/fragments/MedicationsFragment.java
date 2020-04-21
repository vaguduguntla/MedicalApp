package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "mid";

    // TODO: Rename and change types of parameters
    private String mid;
    ArrayList<Medication> MedicationList = new ArrayList<>();
    RecyclerView recyclerView;

    public MedicationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mid Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicationsFragment newInstance(String mid) {
        MedicationsFragment fragment = new MedicationsFragment();
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
        return inflater.inflate(R.layout.fragment_medical_history_medications, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        recyclerView = view.findViewById(R.id.medical_history_medications_recyclerView);
        okhttp ok = new okhttp();
        ok.appendUrl("all_meds_pid="+mid);
        try {
            String[] data = ok.run_request_and_handle_response(null);
            JSONArray jsonArr = new JSONArray(data[0]);
            for (int i=0;i<jsonArr.length();++i) {
                MedicationList.add(new Medication(jsonArr.getJSONObject(i).getString("mid"),
                        jsonArr.getJSONObject(i).getString("pid"),
                        jsonArr.getJSONObject(i).getString("name"),
                        jsonArr.getJSONObject(i).getString("startDate"),
                        jsonArr.getJSONObject(i).getString("endDate")));
            }
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        MedicationsRecyclerViewAdapter adapter = new MedicationsRecyclerViewAdapter(getContext(), MedicationList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
