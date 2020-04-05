package com.example.medicalapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Connect : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getRecords() on init so we can display status immediately.
     */
    init {
        getRecords()
    }

    /**
     * Sets the value of the status LiveData to the Med API status.
     */
    private fun getRecords() {
        MedApi.retrofitService.getProperties().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("response", "responding")
                _response.value = response.body()
            }
        })
    }
}