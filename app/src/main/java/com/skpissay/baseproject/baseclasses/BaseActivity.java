package com.skpissay.baseproject.baseclasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.skpissay.baseproject.essentials.ForceUpdateActivity;
import com.skpissay.baseproject.utils.NetworkChangeReceiver;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by skpissay on 25/06/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkStateChangeListener, View.OnClickListener {
    protected Context mContext;

    public BaseFragment m_cObjFragmentBase;

    protected int m_cDialogID;
    protected AlertDialog m_cObjDialog;
    protected ProgressDialog m_cObjProgressBar;
    protected Snackbar m_cSnackBar;
    protected Dialog m_cObjAnimDialog;
    public UIHandler m_cObjUIHandler;
    public SwipeRefreshLayout swipeView;

    public boolean pTakePicture;
    public boolean pSavePicture;

    public String m_cImageGUID;
    public String m_cAudioGUID;

    public HashMap<Integer, String> mFragmentTags = new HashMap<Integer, String>();

    protected abstract void handleUIMessage(Message pObjMessage);

    private BroadcastReceiver mForceUpdateListener = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.startActivity(ForceUpdateActivity.getForceUpdateListener(context));
            BaseActivity.this.finish();
        }
    };

    public BaseActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        m_cObjUIHandler = new UIHandler();
        this.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mForceUpdateListener, new IntentFilter("force_update"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mForceUpdateListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {}

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            if (null != m_cObjFragmentBase && m_cObjFragmentBase.m_cIsActivityAttached) {
                m_cObjFragmentBase.handleUIhandler(pObjMessage);
            }
            handleUIMessage(pObjMessage);
        }
    }

    public void setTitle(String title) {
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(title);
        }

    }

    public void showBackPressOnActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 16908332) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
