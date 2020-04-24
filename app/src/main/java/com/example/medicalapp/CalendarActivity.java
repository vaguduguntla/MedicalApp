package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.fragments.AddNewRecordFragment;

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
                Intent i = new Intent();
                i.putExtra("date", date);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
