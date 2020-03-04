package com.onnidrops.threefiftydrops.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onnidrops.threefiftydrops.R;
import com.onnidrops.threefiftydrops.util.RippleView;

import androidx.annotation.Nullable;

public class Login extends Activity {
    TextView mTxtTerms, mTxtSeconds, mTxtLoginName;
    LinearLayout mLlMobile, mLlOtp;
    EditText mEdtMobile;
    RippleView mRvSubmit, mRvResend;
    Boolean isLogin = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialization();
        InitializationViews();
        SetClickEvents();
        changeColor();
    }

    void changeColor() {
        String first = "By Signinig up, you agree to 350degrees   ";
        String next = "<font color='#2B2B2B'>Terms & Condition</font>";
        mTxtTerms.setText(Html.fromHtml(first + next));
    }

    void Initialization() {


    }

    void InitializationViews() {
        mTxtLoginName = (TextView) findViewById(R.id.txt_login_name);
        mTxtTerms = (TextView) findViewById(R.id.txt_terms);
        mTxtSeconds = (TextView) findViewById(R.id.txt_seconds);
        mRvResend = (RippleView) findViewById(R.id.rv_resend);
        mRvSubmit = (RippleView) findViewById(R.id.rv_next);
        mEdtMobile = (EditText) findViewById(R.id.edt_mobile);
        mLlMobile = (LinearLayout) findViewById(R.id.ll_mobile_number);
        mLlOtp = (LinearLayout) findViewById(R.id.ll_otp);
    }

    void SetClickEvents() {
        mRvSubmit.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (isLogin) {
                    mLlMobile.setVisibility(View.GONE);
                    mLlOtp.setVisibility(View.VISIBLE);
                    mRvResend.setVisibility(View.VISIBLE);
                    mTxtSeconds.setVisibility(View.VISIBLE);
                    mTxtTerms.setVisibility(View.GONE);
                    mTxtLoginName.setText("Enter OTP");
                    isLogin = false;
                } else {
                    Intent mIntent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(mIntent);
                    finish();
                }

            }
        });
    }


}
