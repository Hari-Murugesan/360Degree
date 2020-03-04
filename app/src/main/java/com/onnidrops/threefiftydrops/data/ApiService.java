package com.onnidrops.threefiftydrops.data;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onnidrops.threefiftydrops.BuildConfig;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService instance;

    private ApiService() {
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
    }

    public static ApiService getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    private String BASE_URL = "";
    private ApiList mApiList;
    private Retrofit mRetrofit;

    private void logInfo(String msg) {
        if (BuildConfig.DEBUG) {
            System.out.println(msg);
        }
    }

    private HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor(this::logInfo);

    private Interceptor getRetry() {
        return chain -> {
            Request request = chain.request();
            Response response = null;
            try {
                response = chain.proceed(request);
                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 2) {
                    logInfo("Request is not successful - " + tryCount);
                    tryCount++;
                    response = chain.proceed(request);
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            if (response != null) {
                if (response.code() == 500) {
                    return response.newBuilder()
                            .code(500)
                            .message("Internal Server Error")
                            .build();
                }
                return response;
            } else {
                return chain.proceed(request);
            }
        };
    }

    private void createClient() {
        if (mRetrofit == null) {
            mLogging.level(HttpLoggingInterceptor.Level.BODY);

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(2);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(200, TimeUnit.SECONDS);
            builder.readTimeout(300, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(mLogging);
            }
            builder.addInterceptor(getRetry());
            builder.dispatcher(dispatcher);

            OkHttpClient httpClient = builder.build();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.disableHtmlEscaping().create();

            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .build();
        }
    }

    public Retrofit getClient() {
        if (mRetrofit == null) createClient();
        return mRetrofit;
    }

    public ApiList getApiList() {
        if (mApiList == null) {
            mApiList = getClient().create(ApiList.class);
        }
        return mApiList;
    }
}
