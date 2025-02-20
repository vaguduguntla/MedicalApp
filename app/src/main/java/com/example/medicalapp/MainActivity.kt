package com.example.medicalapp



import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.medicalapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var did = "1"
    lateinit var rootDoctor:Doctor
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootDoctor = Doctor(did, "Dr Richard")
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        openFragment(MyProfileFragmentDoctor.newInstance("",""))
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        var currentFragment: Fragment? = supportFragmentManager.fragments.lastOrNull()
        if (currentFragment == null) {
            return super.onPrepareOptionsMenu(menu)
        }

        if (currentFragment is AddDoctorFragment) {

            val inflater: MenuInflater = menuInflater
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            inflater.inflate(R.menu.search_menu, menu)
            val searchItem: MenuItem = menu!!.findItem(R.id.search_bar)
            val searchBar: SearchView = searchItem.actionView as SearchView
            searchBar.isIconifiedByDefault = false
            searchBar.queryHint = "Search for Doctor by Name"

            val menuItem: MenuItem = menu.findItem(R.id.search_bar)
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(currentFragment)
        }

        if (currentFragment is MyNetworkFragment) {

            val inflater: MenuInflater = menuInflater
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            inflater.inflate(R.menu.search_menu, menu)
            val searchItem: MenuItem = menu!!.findItem(R.id.search_bar)
            val searchBar: SearchView = searchItem.actionView as SearchView
            searchBar.isIconifiedByDefault = false
            searchBar.queryHint = "Search for Doctor by Name"

            val menuItem: MenuItem = menu.findItem(R.id.search_bar)
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(currentFragment)
        }

        if (currentFragment is MyPatientsFragment) {

            val inflater: MenuInflater = menuInflater
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            inflater.inflate(R.menu.search_menu, menu)
            val searchItem: MenuItem = menu!!.findItem(R.id.search_bar)
            val searchBar: SearchView = searchItem.actionView as SearchView
            searchBar.isIconifiedByDefault = false
            searchBar.queryHint = "Search for Patient by Name"

            val menuItem: MenuItem = menu.findItem(R.id.search_bar)
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(currentFragment)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    fun openFragment(fragment: Fragment) {
        Log.d("GOT HERE", "HI")
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                Log.d("GOT HERE", "HI")
                when (item.getItemId()) {
                    R.id.fragment_my_profile -> {
                        openFragment(MyProfileFragmentDoctor.newInstance(did,""))

                        return true
                    }
                    R.id.fragment_my_patients -> {
                        openFragment(MyPatientsFragment.newInstance("",""))

                        return true
                    }
                    R.id.fragment_my_network -> {
                        openFragment(MyNetworkFragment.newInstance(did,""))

                        return true
                    }
                }
                return false
            }
        }
}



