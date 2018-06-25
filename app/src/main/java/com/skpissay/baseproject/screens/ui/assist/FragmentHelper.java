package com.skpissay.baseproject.screens.ui.assist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.screens.fragments.LandingFragment;

/**
 * Created by skpissay on 25/06/18.
 */

public class FragmentHelper {

    private int FRAME = R.id.frame;
    public static final int LANDING = 100;
    public static final int MENU = 101;
    public static final int TRANSACTION = 102;
    public static final int HISTORY = 103;
    public static final int HISTORY_DETAIL = 104;
    public static final int MAPPER = 105;
    public static final int PAYMENTS = 106;
    public static final int MAKE_PAYMENT_INVOICES = 107;
    public static final int VERIFY = 108;

    private Context mContext;

    private LandingFragment mLandingFragment;
    private int mUiState = -1;

    public FragmentHelper(BaseActivity context) {
        this.mContext = context;
        init();
    }

    private void init() {
    }

    private FragmentManager getFragmentManager() {
        return ((BaseActivity) mContext).getSupportFragmentManager();
    }

    public void loadLandingFragment() {
        mLandingFragment = new LandingFragment();
        loadFragment(mLandingFragment);
    }

    private void loadFragment(android.support.v4.app.Fragment f) {
        loadFragment(f, false);
    }

    public void loadFragment(android.support.v4.app.Fragment f, boolean addToBackStack) {
        String tag = getFragmentTag(mUiState);

        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.fade_in, 0);
        ft.replace(FRAME, f, tag);
        if (addToBackStack)
            ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    private void addFragment(android.support.v4.app.Fragment f) {
        String tag = getFragmentTag(mUiState);

        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.fade_in, 0);
        ft.add(FRAME, f, tag);
        ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    private String getFragmentTag(int uiState) {
        return "uistate-" + uiState;
    }

    public boolean onBackPressed() {
        boolean handled = false;
        if (mLandingFragment != null && mLandingFragment.isVisible())
            handled = mLandingFragment.onBackPressed();
        return handled;
    }

    public int getBackStackCount() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            mUiState = -1;
        return getFragmentManager().getBackStackEntryCount();
    }

    public void switchUi(int state, Object pObj) {
        if (state != mUiState) {
            mUiState = state;
            switch (state) {
                case LANDING:
                    loadLandingFragment();
                    break;
            }
        }
    }
}
