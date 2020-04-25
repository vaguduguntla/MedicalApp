package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Doctor;
import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AddDoctorFragment extends Fragment implements SearchView.OnQueryTextListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "did";


    // TODO: Rename and change types of parameters
    private String did;
    ArrayList<Doctor> AllDoctorsList = new ArrayList<>();
    String SpecialityList[] = new String[]{"Proctologist", "Cardiologist", "Oncologist", "Cosmetic Surgeon", "Pedatrition"};
    String LocationList[] = new String[]{"Bloomfield Hills", "Ann Arbor", "New York", "London", "Bangkok"};

    int img = R.drawable.ic_add_box_black_24dp;
    RecyclerView recyclerView;
    AddDoctorRecyclerViewAdapter doctorRecyclerViewAdapter;



    public AddDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param did Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDoctorFragment newInstance(String did) {
        AddDoctorFragment fragment = new AddDoctorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, did);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            did = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_doctor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Populate Doctors list
        okhttp ok = new okhttp();
        ok.appendUrl("all_doctors");
        try {
            String[] data = ok.run_request_and_handle_response(null);
            JSONArray jsonArr = new JSONArray(data[0]);

            for (int i=0;i<jsonArr.length();++i) {
                AllDoctorsList.add(new Doctor(jsonArr.getJSONObject(i).getString("did"),jsonArr.getJSONObject(i).getString("name"), jsonArr.getJSONObject(i).getString("specialty")));
            }
            Log.d("data",data[0]);
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        //AutoComplete Stuff
        AutoCompleteTextView specialityACTV = view.findViewById(R.id.specialty_autocompleteTextView);
        AutoCompleteTextView locationACTV = view.findViewById(R.id.location_add_doctor_autocompleteTextView);
        ArrayAdapter<String> specialtyAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, SpecialityList);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, LocationList);
        specialityACTV.setAdapter(specialtyAdapter);
        locationACTV.setAdapter(locationAdapter);
        //RecyclerView Stuff
        recyclerView = view.findViewById(R.id.addDrRecyclerView);
        doctorRecyclerViewAdapter = new AddDoctorRecyclerViewAdapter(getContext(), AllDoctorsList);
        recyclerView.setAdapter(doctorRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        doctorRecyclerViewAdapter.getFilter().filter(newText);
        return false;
    }
}