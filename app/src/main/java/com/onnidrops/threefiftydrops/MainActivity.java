package com.onnidrops.threefiftydrops;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.onnidrops.threefiftydrops.view_model.DataViewModel;


public class MainActivity extends AppCompatActivity {

    private DataViewModel mDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        mDataViewModel.init();

    }
}
