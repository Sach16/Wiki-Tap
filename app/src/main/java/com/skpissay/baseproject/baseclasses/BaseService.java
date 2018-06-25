package com.skpissay.baseproject.baseclasses;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;

/**
 * Created by skpissay on 25/06/18.
 */

public abstract class BaseService extends Service{

    protected Context mContext;
    protected UIHandler m_cObjUIHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        m_cObjUIHandler = new UIHandler();
        this.mContext = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }

    protected abstract void handleUIMessage(Message pObjMessage);
}
