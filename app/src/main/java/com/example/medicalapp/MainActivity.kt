package com.example.medicalapp

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    var bottomNavigation: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_my_profile -> {
                        openFragment(HomeFragment.newInstance("", ""))
                        return true
                    }
                    R.id.navigation_my_patients -> {
                        openFragment(SmsFragment.newInstance("", ""))
                        return true
                    }
                    R.id.navigation_my_network -> {
                        openFragment(NotificationFragment.newInstance("", ""))
                        return true
                    }
                }
                return false
            }
        }



}
