package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Patient;
import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPatientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPatientsFragment extends Fragment implements SearchView.OnQueryTextListener {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MyPatientsFragment() {
    // Required empty public constructor
  }

  ArrayList<Patient> PatientNamesList = new ArrayList<>();

  RecyclerView patientRecyclerView;

  PatientRecyclerViewAdapter patientRecyclerViewAdapter;
  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment SmsFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MyPatientsFragment newInstance(String param1, String param2) {
    MyPatientsFragment fragment = new MyPatientsFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.fragment_users, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view,savedInstanceState);
    patientRecyclerView = view.findViewById(R.id.names_recycler_view);
    okhttp ok = new okhttp();
    ok.appendUrl("all_patients");
    try {
      String[] data = ok.run_request_and_handle_response(null);
      JSONArray jsonArr = new JSONArray(data[0]);

      for (int i=0;i<jsonArr.length();++i) {
                PatientNamesList.add(new Patient(jsonArr.getJSONObject(i).getString("pid"),
                /*jsonArr.getJSONObject(i).getString("name")*/ "Person" + Integer.toString(i),
                jsonArr.getJSONObject(i).getString("age"),
                jsonArr.getJSONObject(i).getString("gender")));
      }
      Log.d("data",data[0]);
    } catch (InterruptedException | JSONException e) {
      e.printStackTrace();
    }

    patientRecyclerViewAdapter = new PatientRecyclerViewAdapter(getContext(), PatientNamesList);
    patientRecyclerView.setAdapter(patientRecyclerViewAdapter);
    patientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  }


  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    patientRecyclerViewAdapter.getFilter().filter(newText);
    return true;
  }


}
