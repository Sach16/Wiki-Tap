package com.skpissay.baseproject.di.components;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.skpissay.baseproject.di.ApplicationScope;
import com.skpissay.baseproject.di.modules.EssentialModules;
import com.skpissay.baseproject.di.modules.NetworkModule;
import com.skpissay.baseproject.utils.AppPreferences;
import com.skpissay.baseproject.utils.NetworkChangeReceiver;
import com.skpissay.baseproject.utils.NetworkUtil;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by skpissay on 25/06/18.
 */

@Component(
        modules = {NetworkModule.class, EssentialModules.class}
)
@ApplicationScope
public interface EssentialComponents {
    NetworkUtil networkUtil();

    NetworkChangeReceiver networkChangeReceiver();

    ColorGenerator colorGenerator();

    AppPreferences appPreferences();

    @Named("baseUrl")
    String baseUrl();

    @Named("source")
    String source();

    @Named("key")
    String key();

    @Named("versionName")
    String versionName();

    @Named("versionCode")
    int versionCode();

    @Named("analyticsToken")
    String analyticsToken();
}