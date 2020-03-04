package com.onnidrops.threefiftydrops.view_model;

import com.onnidrops.threefiftydrops.data.ApiRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.HashMap;

public class DataViewModel extends ViewModel {

    private MutableLiveData<String> mutableLiveData;
    private ApiRepository mApiRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        mApiRepository = ApiRepository.getInstance();
        mutableLiveData = mApiRepository.getData("google-news", new HashMap<String, String>());
    }

    public LiveData<String> getDataRepository() {
        return mutableLiveData;
    }

}
