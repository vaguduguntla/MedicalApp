package com.example.medicalapp



import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.medicalapp.fragments.MyNetworkFragment
import com.example.medicalapp.fragments.MyPatientsFragment
import com.example.medicalapp.fragments.MyProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    var bottomNavigation: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.fragment_my_profile -> {
                        openFragment(MyProfileFragment.newInstance("", ""))
                        return true
                    }
                    R.id.fragment_my_patients -> {
                        openFragment(MyPatientsFragment.newInstance("", ""))
                        return true
                    }
                    R.id.fragment_my_network -> {
                        openFragment(MyNetworkFragment.newInstance("", ""))
                        return true
                    }
                }
                return false
            }
        }
}



