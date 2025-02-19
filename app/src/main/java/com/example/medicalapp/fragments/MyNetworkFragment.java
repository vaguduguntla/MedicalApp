package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Doctor;
import com.example.medicalapp.MainActivity;
import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyNetworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNetworkFragment extends Fragment implements SearchView.OnQueryTextListener{
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MyNetworkFragment() {
    // Required empty public constructor
  }
  ArrayList<Doctor> DoctorNamesList = new ArrayList<>();
  String TempList;
  String didList[];
  int img = R.drawable.ic_doctor;
  RecyclerView doctorReyclerView;
  DoctorRecyclerViewAdapter doctorRecyclerViewAdapter;
  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment NotificationFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MyNetworkFragment newInstance(String param1, String param2) {
    MyNetworkFragment fragment = new MyNetworkFragment();
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

  public void onViewCreated(View view, Bundle savedInstanceState){
    super.onViewCreated(view, savedInstanceState);
    DoctorNamesList.clear();
    okhttp ok = new okhttp();
    okhttp ok1 = new okhttp();
    ok.appendUrl("all_doctors_did=1");
    try {
      String[] data = ok.run_request_and_handle_response(null);
      JSONArray jsonArr = new JSONArray(data[0]);

      for (int i=0;i<jsonArr.length();++i) {
        didList = jsonArr.getJSONObject(i).getString("doctors").split(",");
      }
      }
    catch (InterruptedException | JSONException e){
      e.printStackTrace();
    }
    for (int i = 0; i < didList.length-1; i++){
      DoctorNamesList.add(new Doctor(didList[i],didList[i+1]));
      }

    doctorReyclerView = view.findViewById(R.id.names_recycler_view);
    Button btn = (Button) view.findViewById(R.id.add_user_button);
    btn.setText(getResources().getString(R.string.add_new_doctor));

    btn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        MainActivity currentActivity = (MainActivity) v.getContext();
        currentActivity.openFragment(AddDoctorFragment.newInstance(currentActivity.rootDoctor.getDid()));
      }
    });

    doctorRecyclerViewAdapter = new DoctorRecyclerViewAdapter(DoctorNamesList, getContext(), img);
    doctorReyclerView.setAdapter(doctorRecyclerViewAdapter);
    doctorReyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
