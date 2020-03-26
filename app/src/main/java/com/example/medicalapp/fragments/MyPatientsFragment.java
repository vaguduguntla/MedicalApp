package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPatientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPatientsFragment extends Fragment {
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

  String PatientNamesList[];
  RecyclerView patientRecyclerView;
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
    return inflater.inflate(R.layout.fragment_my_patients, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view,savedInstanceState);
    PatientNamesList = getResources().getStringArray(R.array.patient_names);
    patientRecyclerView = view.findViewById(R.id.patient_name_recycler_view);
    PatientRecyclerViewAdapter patientRecyclerViewAdapter = new PatientRecyclerViewAdapter(getContext(), PatientNamesList);
    patientRecyclerView.setAdapter(patientRecyclerViewAdapter);
    patientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    //PatientRecyclerViewAdapter patientRecyclerViewAdapter = new PatientRecyclerViewAdapter(this, PatientNamesList);
  }
}
