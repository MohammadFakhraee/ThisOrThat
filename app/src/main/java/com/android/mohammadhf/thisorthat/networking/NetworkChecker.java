package com.android.mohammadhf.thisorthat.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker {

    public static final int DISCONNECTED = 0;
    public static final int WIFI = 1;
    public static final int MOBILE = 2;

    public static int readNetworkStatus(Context _context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        try {
            boolean isConnected = networkInfo.isConnected();

            boolean isConnectedOrConnecting = networkInfo.isConnectedOrConnecting();

            int networkType = networkInfo.getType();

            if (isConnected) {
                if (networkType == ConnectivityManager.TYPE_WIFI) {
                    return WIFI;
                } else if (networkType == ConnectivityManager.TYPE_WIMAX) {
                    //strPro.append(" Network IS WIMAX");
                    return WIFI;
                } else if (networkType == ConnectivityManager.TYPE_MOBILE) {
                    return MOBILE;
                }
            } else if (isConnectedOrConnecting) {
                //strPro.append("IS Connecting  ");
                return DISCONNECTED;
            } else
                return DISCONNECTED;
            //strPro.append("No Network Access");

        } catch (Exception ee) {
            return DISCONNECTED;
            //Toast.makeText(context, "Error Access WiFi", Toast.LENGTH_LONG).show();
        }

        return DISCONNECTED;
    }
}
