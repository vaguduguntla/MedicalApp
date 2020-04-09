package com.example.medicalapp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.util.Log;

public class okhttp {
    //OkHttpClient client = new OkHttpClient();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    String url = "https://amh3vre134.execute-api.us-east-2.amazonaws.com/default/foo?";

    public void appendUrl(String parameters) {
        this.url += parameters;
    }

    Request request = new Request.Builder()
            .url(url)
            .build();

    public void run_request_and_handle_response() {
        client.newCall(request).

                enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponse = response.body().string();
                            Log.d("response", myResponse);
                        }
                    }
                });
    }
}