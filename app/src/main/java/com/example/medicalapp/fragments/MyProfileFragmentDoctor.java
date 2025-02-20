package com.example.medicalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.medicalapp.R;
import com.example.medicalapp.okhttp;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragmentDoctor extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MyProfileFragmentDoctor() {
    // Required empty public constructor
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
  public static MyProfileFragmentDoctor newInstance(String param1, String param2) {
    MyProfileFragmentDoctor fragment = new MyProfileFragmentDoctor();
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
    Log.d("This ran", "actually somehow");


    return inflater.inflate(R.layout.fragment_my_profile_doctor, container, false);
  }


  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view,savedInstanceState);
    ImageView button = view.findViewById(R.id.edit_button);
    button.setVisibility(view.VISIBLE);

    TextView titleName, Specialty, mondayStart, mondayEnd, tuesStart, tuesEnd, WedStart, WedEnd, ThursStart, ThursEnd, FriStart,FriEnd, phoneNumber, Location;
    titleName = view.findViewById(R.id.dr_name_textView);
    Specialty = view.findViewById(R.id.speciality_textView);
    mondayStart = view.findViewById(R.id.Monday_Start_Hour);
    mondayEnd = view.findViewById(R.id.Monday_End_Hour);
    tuesStart = view.findViewById(R.id.Tuesday_Start_Hour);
    tuesEnd = view.findViewById(R.id.Tuesday_End_Hour);
    WedStart = view.findViewById(R.id.Wednesday_Start_Hour);
    WedEnd = view.findViewById(R.id.Wednesday_End_Hour);
    ThursStart = view.findViewById(R.id.Thursday_Start_Hour);
    ThursEnd = view.findViewById(R.id.Thursday_End_Hour);
    FriStart = view.findViewById(R.id.Friday_Start_Hour);
    FriEnd = view.findViewById(R.id.Friday_End_Hour);
    phoneNumber = view.findViewById(R.id.Phone_Number);
    Location = view.findViewById(R.id.Location_Address);

    String hours[] = null;

    okhttp ok = new okhttp();
    ok.appendUrl("all_doctors_did=1");
    try{
      String data[] = ok.run_request_and_handle_response(null);
      JSONArray jsonArray = new JSONArray(data[0]);
      titleName.setText(jsonArray.getJSONObject(0).getString("name"));
      Specialty.setText(jsonArray.getJSONObject(0).getString("specialty"));
      phoneNumber.setText(jsonArray.getJSONObject(0).getString("phone"));
      Location.setText(jsonArray.getJSONObject(0).getString("location"));
      hours = jsonArray.getJSONObject(0).getString("hours").split(",");

    }
    catch (InterruptedException | JSONException e){
      e.printStackTrace();
    }


    mondayStart.setText(CheckHour(hours[0]));
    mondayEnd.setText(CheckHour(hours[1]));
    tuesStart.setText(CheckHour(hours[2]));
    tuesEnd.setText(CheckHour(hours[3]));
    WedStart.setText(CheckHour(hours[4]));
    WedEnd.setText(CheckHour(hours[5]));
    ThursStart.setText(CheckHour(hours[6]));
    ThursEnd.setText(CheckHour(hours[7]));
    FriStart.setText(CheckHour(hours[8]));
    FriEnd.setText(CheckHour(hours[9]));


  }

  String CheckHour(String hour){
    boolean is_Am = true;
    int number = Integer.parseInt(String.valueOf(hour));

    if(number > 12){
      number = number - 12;
      is_Am = false;
    }
    String result = Integer.toString(number);
    if(is_Am){
      result = result + " AM";
    }
    else{
      result = result + " PM";
    }
    return result;
  }



}
