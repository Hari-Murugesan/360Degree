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
