package com.example.medicalapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class Connect : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    private lateinit var query: String

    private val BASE_URL = "https://amh3vre134.execute-api.us-east-2.amazonaws.com/default/"

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
    get() = _response

    interface MedApiService {
        @GET("foo?" + query)
        fun getProperties():
                Call<String>
    }

    object MedApi {
        val retrofitService : MedApiService by lazy {
            retrofit.create(MedApiService::class.java)
        }
    }

     /**
     * Call getRecords() on init so we can display status immediately.
     */
    init {
        getRecords()
    }

    fun setQuery(q: String) {
        query = q
    }

    fun getRecords() {
        MedApi.retrofitService.getProperties().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
            _response.value = response.body()
            }
        })
    }
}