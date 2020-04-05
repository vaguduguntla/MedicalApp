package com.example.medicalapp;

import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class connect extends ViewModel {
    MutableLiveData<String> _response;
    static String query;
    String BASE_URL = "https://amh3vre134.execute-api.us-east-2.amazonaws.com/default/";

    LiveData<String> response;
    MutableLiveData<String> getResponse() {return _response;}

    public interface UserService {
        @GET("foo?{id}")
        public Call<UserApiResponse> getUser(@Query(query) long id);

    }

}