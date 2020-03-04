package com.onnidrops.threefiftydrops.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppUtil {

    private static AppUtil instance;

    private AppUtil() {
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AppUtil();
        }
    }

    public static AppUtil getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    public boolean isNetworkAvailable(Context context) {
        boolean status = false;
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    status = true;
                }
            }
        }
        return status;
    }
}
