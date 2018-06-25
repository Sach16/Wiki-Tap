package com.skpissay.baseproject.essentials;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.utils.AppPreferences;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by skpissay on 25/06/18.
 */

public class HttpInterceptor  implements Interceptor {
    private Context mContext;
    private AppPreferences mPreferenceUtil;
    String mApiKey;
    String mApiSource;
    String mVersionName;
    int mCode;

    @Inject
    public HttpInterceptor(Context context, AppPreferences preferenceUtil, @Named("key") String apiKey, @Named("source") String apiSource, @Named("versionName") String versionName, @Named("versionCode") int versionCode) {
        this.mContext = context;
        this.mPreferenceUtil = preferenceUtil;
        this.mApiKey = apiKey;
        this.mApiSource = apiSource;
        this.mVersionName = versionName;
        this.mCode = versionCode;
    }

    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder().addHeader(this.mContext.getString(R.string.key_accept), this.mContext.getString(R.string.mime_json)).addHeader(this.mContext.getString(R.string.key_source), this.mApiSource).addHeader(this.mContext.getString(R.string.sfc_version), this.mVersionName).addHeader(this.mContext.getString(R.string.key), this.mApiKey);
        String accessToken = this.mPreferenceUtil.getAccessToken();
        if(accessToken != null) {
            builder.addHeader(this.mContext.getString(R.string.authorization_key), accessToken);
        }

        builder.method(original.method(), original.body());
        Response response = chain.proceed(builder.build());
        if(response.code() != 491 && response.code() == 492) {
            Intent forceUpdateIntent = new Intent("force_update");
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(forceUpdateIntent);
        }

        return response;
    }
}
