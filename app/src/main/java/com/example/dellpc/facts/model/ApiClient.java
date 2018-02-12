package com.example.dellpc.facts.model;

import com.example.dellpc.facts.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Balamithra on 2/11/2018.
 */
public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Builder().baseUrl(Constants.BASE_URL)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .build()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
