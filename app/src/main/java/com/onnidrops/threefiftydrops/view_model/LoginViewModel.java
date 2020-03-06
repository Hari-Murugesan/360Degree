package com.onnidrops.threefiftydrops.view_model;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.onnidrops.threefiftydrops.model.LoginModel;


import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import javax.inject.Inject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> mobileNo = new MutableLiveData<>();

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
    }

    private String toastMessage = null;
    private MutableLiveData<LoginModel> userMutableLiveData;

    public MutableLiveData<LoginModel> getLogin() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void onClick(View view) {
        LoginModel mLoginModel = new LoginModel(mobileNo.getValue());
        userMutableLiveData.setValue(mLoginModel);

    }

}