package com.skpissay.baseproject.utils;

import android.content.Context;

import com.skpissay.baseproject.assist.Constants;

/**
 * Created by skpissay on 25/06/18.
 */

public class PreferenceUtil extends AppPreferences {
    private final static String TAG = PreferenceUtil.class.getSimpleName();
    private static final String USER_ID = "user_id";
    private static final String IS_DEVICE_REGISTERED = "is_device_registered";
    private static final String DEVICE_TOKEN = "device_token";

    public PreferenceUtil(Context context) {
        super(context);
    }

    public boolean isConfirmationToBeAsked() {
        return getBoolean(Constants.PreferenceKeys.IS_EXIT_CONFIRMATION_TO_BE_ASKED, true);
    }

    public boolean isUserLoggedIn() {
        return getBoolean(Constants.PreferenceKeys.USER_LOGGED_IN, false);
    }

    public Long getUserId() {
        return getLong(USER_ID, -1L);
    }

    public void setUserId(Long id) {
        addPreference(USER_ID, id);
    }


    public boolean isDeviceTokenRegistered() {
        return getBoolean(IS_DEVICE_REGISTERED, false);

    }

    public void setDeviceToken(String token) {
        addPreference(DEVICE_TOKEN, token);
    }

    public String getDeviceToken() {
        return getString(DEVICE_TOKEN, null);
    }

    public void setDeviceTokenRegistered() {
        addPreference(IS_DEVICE_REGISTERED, true);
    }

    public void resetDeviceTokenRegistered() {
        addPreference(IS_DEVICE_REGISTERED, false);
    }

    public int getRetailerId() {
        return getInt(Constants.PreferenceKeys.RETAILER_ID, -1);
    }
}
