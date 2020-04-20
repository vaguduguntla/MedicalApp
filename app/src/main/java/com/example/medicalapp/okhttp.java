package com.example.medicalapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public String[] run_request_and_handle_response(TreeMap<String, String> post_body) throws InterruptedException {

        final String[] myResponse = new String[1];

        Request request = null;
        if (post_body == null) {
            request = new Request.Builder()
                .url(url)
                .build();
        } else {
            FormBody.Builder formBody = new FormBody.Builder();
            for(TreeMap.Entry<String,String> entry : post_body.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
            RequestBody finished_body = formBody.build();
            //RequestBody finished_body = new FormBody.Builder()
            //        .add("username", "test")
            //        .add("password", "test")
            //        .build();

            request = new Request.Builder()
                    .url(url)
                    .post(finished_body)
                    .build();
        }

        client.newCall(request).

                enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        synchronized (myResponse) {
                            e.printStackTrace();
                            myResponse[0] = "FAIL";
                            myResponse.notify();
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        synchronized (myResponse) {
                            if (response.isSuccessful()) {
                                myResponse[0] = response.body().string();
                                Log.d("response", myResponse[0]);
                            } else {
                                myResponse[0] = "RESPONSE_FAIL";
                            }
                            myResponse.notify();
                        }
                    }
                });

        synchronized (myResponse) {
            while (myResponse[0] == null) {
                myResponse.wait();
            }
            return myResponse;
        }
    }
}