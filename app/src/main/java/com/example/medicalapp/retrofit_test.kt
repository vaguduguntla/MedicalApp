package com.example.medicalapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://amh3vre134.execute-api.us-east-2.amazonaws.com/default/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MedApiService {
    @GET("foo?users")
    fun getProperties():
            Call<String>
}

object MedApi {
    val retrofitService : MedApiService by lazy {
        retrofit.create(MedApiService::class.java)
    }
}
