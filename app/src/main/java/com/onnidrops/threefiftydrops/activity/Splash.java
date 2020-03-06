package com.onnidrops.threefiftydrops.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.onnidrops.threefiftydrops.R;

import androidx.annotation.Nullable;

public class Splash extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(mIntent);
                finish();
            }
        },1000);
    }
}



//    BACKGROUND GREY => #f6f4f4
//        TEXT GREY => #606060
//        Grey Divider Line => #b3b3b3
//
//        Dark Text = > #000000
//        input text => #606060
//        product description Grey text => #b3b3b3
//        yellow text => #faba1a
//
//        Price strike line => #87121f
//        Radio button Grey => #b3b3b3
//        Radio Button select Gradient =>
//        bottom yellow
//        #faba19
//        top yellow
//        #fee06b