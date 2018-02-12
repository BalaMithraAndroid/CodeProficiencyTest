package com.example.dellpc.facts.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.util.Log;

public class Networkutils {
    private Context _context;

    public Networkutils(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this._context.getSystemService("connectivity");
        if (VERSION.SDK_INT >= 21) {
            for (Network mNetwork : connectivityManager.getAllNetworks()) {
                if (connectivityManager.getNetworkInfo(mNetwork).getState().equals(State.CONNECTED)) {
                    return true;
                }
            }
        } else if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == State.CONNECTED) {
                        Log.d("Network", "NETWORKNAME: " + anInfo.getTypeName());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
