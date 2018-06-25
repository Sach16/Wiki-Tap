package com.skpissay.baseproject.di.modules;

import android.app.Application;
import android.content.Context;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.skpissay.baseproject.essentials.utils.AppBuilderConfig;
import com.skpissay.baseproject.utils.AppPreferences;
import com.skpissay.baseproject.utils.NetworkChangeReceiver;
import com.skpissay.baseproject.utils.NetworkUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class EssentialModules {

    private Application mApplication;
    private String mBaseUrl;
    private String mSource;
    private String mKey;
    private String mVersionName;
    private int mVersionCode;
    private String mAnalyticsToken;

    public EssentialModules(Application application, AppBuilderConfig appBuilderConfig) {
        this.mApplication = application;
        this.mBaseUrl = appBuilderConfig.baseUrl;
        this.mKey = appBuilderConfig.apiKey;
        this.mSource = appBuilderConfig.apiSource;
        this.mVersionName = appBuilderConfig.versionName;
        this.mVersionCode = appBuilderConfig.versionCode;
        this.mAnalyticsToken = appBuilderConfig.mixPanelToken;
    }

    @Provides
    @Named("baseUrl")
    public String provideBaseUrl() {
        return this.mBaseUrl;
    }

    @Provides
    @Named("source")
    public String provideSource() {
        return this.mSource;
    }

    @Provides
    public AppPreferences appPreferences(Context context) {
        return new AppPreferences(context);
    }

    @Provides
    @Named("key")
    public String provideKey() {
        return this.mKey;
    }

    @Provides
    @Named("versionName")
    public String provideVersionName() {
        return this.mVersionName;
    }

    @Provides
    @Named("versionCode")
    public int provideVersionCode() {
        return this.mVersionCode;
    }

    @Provides
    @Named("analyticsToken")
    public String provideAnalyticsToken() {
        return this.mAnalyticsToken;
    }

    @Provides
    public NetworkChangeReceiver provideNetworkChangeReceiver() {
        return new NetworkChangeReceiver();
    }

    @Provides
    public NetworkUtil provideNetworkUtil() {
        return new NetworkUtil();
    }

    @Provides
    public ColorGenerator provideColorGenerator() {
        return ColorGenerator.MATERIAL;
    }

    @Provides
    public Context provideContext() {
        return this.mApplication;
    }

    @Provides
    public Application provideApplication() {
        return this.mApplication;
    }
}

