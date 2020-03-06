package com.onnidrops.threefiftydrops.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.onnidrops.threefiftydrops.MainActivity;
import com.onnidrops.threefiftydrops.R;
import com.onnidrops.threefiftydrops.databinding.ActivityLoginBinding;
import com.onnidrops.threefiftydrops.model.LoginModel;
import com.onnidrops.threefiftydrops.util.RippleView;
import com.onnidrops.threefiftydrops.view_model.LoginViewModel;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class Login extends AppCompatActivity {
    TextView mTxtTerms, mTxtSeconds, mTxtLoginName;
    LinearLayout mLlMobile, mLlOtp;
    EditText mEdtMobile;
    RippleView mRvSubmit, mRvResend;
    Boolean isLogin = true;


    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(Login.this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

//        Initialization();
//        InitializationViews();
//        SetClickEvents();
//        changeColor();

        loginViewModel.getLogin().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
            if (!loginModel.isMobileNo()) {
                    binding.edtMobile.setError("Enter at least 6 Digit password");
                    binding.edtMobile.requestFocus();
                }
                else {
                    binding.edtMobile.setText(loginModel.getMobileNo());
                    System.out.println(loginModel.getMobileNo());
                }
            }
        });

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
