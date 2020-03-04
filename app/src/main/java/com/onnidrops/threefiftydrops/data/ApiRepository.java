package com.onnidrops.threefiftydrops.data;

import android.app.Activity;
import android.content.Intent;

import com.onnidrops.threefiftydrops.util.AppUtil;

import androidx.lifecycle.MutableLiveData;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ApiRepository {

    private static ApiRepository instance;
    private ApiService mApiService = ApiService.getInstance();
    private AppUtil mAppUtil = AppUtil.getInstance();
    private ApiList mApiList;

    private ApiRepository() {
        mApiList = mApiService.getApiList();
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
    }

    public static ApiRepository getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    public MutableLiveData<String> getData(String source, HashMap<String, String> key) {
        MutableLiveData<String> data = new MutableLiveData<>();

        Call<ResponseBody> call = mApiList.apiFormCall(source, key);
        call.enqueue(new EnQueue(data));
        return data;
    }

    private class EnQueue implements Callback<ResponseBody> {

        private MutableLiveData<String> data;

        EnQueue(MutableLiveData<String> data) {
            this.data = data;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                if (response.isSuccessful()) {
                    String bodyValue = null;
                    if (response.body() != null) {
                        bodyValue = response.body().string();
                    }
                    if (bodyValue != null && isJSONValid(bodyValue)) {
                        data.setValue(bodyValue.trim());
                    } else {
                        data.setValue("Internal Server Error");
                    }
                } else {
                    data.setValue(parseError(response));
                }
            } catch (Exception e) {
                setException(e);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            try {
                if (t instanceof ConnectException || t instanceof SocketTimeoutException) {
                    data.setValue("No Internet Connection");
                } else {
                    if (t.getLocalizedMessage() != null) {
                        data.setValue(t.getLocalizedMessage().trim());
                    } else {
                        data.setValue(t.toString());
                    }
                }
            } catch (Exception e) {
                setException(e);
            }
        }

        private void setException(Exception e) {
            if (e.getLocalizedMessage() != null) {
                data.setValue(e.getLocalizedMessage().trim());
            } else {
                data.setValue(e.toString());
            }
        }
    }

    private boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    private String parseError(Response<?> response) {
        Converter<ResponseBody, String> converter = mApiService.getClient().responseBodyConverter(String.class, new Annotation[0]);
        String error;
        try {
            error = converter.convert(response.errorBody());
        } catch (Exception e1) {
            try {
                error = response.message();
            } catch (Exception e) {
                error = e.getLocalizedMessage();
            }
        }
        return error;
    }
}
