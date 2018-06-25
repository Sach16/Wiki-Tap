package com.skpissay.baseproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by skpissay on 25/06/18.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    protected List<NetworkStateChangeListener> listeners = new ArrayList();
    protected boolean mConnected;

    public NetworkChangeReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        this.mConnected = NetworkUtil.getConnectivityStatus(context);
        this.notifyAllListeners();
    }

    private void notifyAllListeners() {
        Iterator var1 = this.listeners.iterator();

        while(var1.hasNext()) {
            NetworkChangeReceiver.NetworkStateChangeListener listener = (NetworkChangeReceiver.NetworkStateChangeListener)var1.next();
            this.notifyState(listener);
        }

    }

    private void notifyState(NetworkChangeReceiver.NetworkStateChangeListener listener) {
        if(listener != null) {
            if(this.mConnected) {
                listener.onConnected();
            } else {
                listener.onDisconnected();
            }

        }
    }

    public void addNetworkChangeListener(NetworkChangeReceiver.NetworkStateChangeListener l) {
        this.listeners.add(l);
        this.notifyState(l);
    }

    public void removeNetworkChangeListener(NetworkChangeReceiver.NetworkStateChangeListener l) {
        this.listeners.remove(l);
    }

    public interface NetworkStateChangeListener {
        void onConnected();

        void onDisconnected();
    }
}
