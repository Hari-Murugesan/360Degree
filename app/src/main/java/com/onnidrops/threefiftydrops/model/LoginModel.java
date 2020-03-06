package com.onnidrops.threefiftydrops.model;

import android.text.TextUtils;

public class LoginModel   {
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    private String mobileNo;

    public boolean isOtp() {
        return isOtp;
    }

    public void setOtp(boolean otp) {
        isOtp = otp;
    }

    private boolean isOtp;

    public LoginModel(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean isMobileNo() {
        if(getMobileNo()!=null){
            return getMobileNo().length() > 1;
        }else{
            return false;
        }

    }

}
