package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    Intent incomingIntent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;

                //Intent intent = new Intent(this, getIntent().getBundleExtra("AddPatientfragmentBundle"));
               // intent.putExtra("date", date);
                //startActivity(intent);

            }
        });

    }
}
