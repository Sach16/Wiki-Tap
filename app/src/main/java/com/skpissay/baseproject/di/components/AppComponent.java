package com.skpissay.baseproject.di.components;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.skpissay.baseproject.di.ApplicationScope;
import com.skpissay.baseproject.di.modules.AnalyticsModule;
import com.skpissay.baseproject.di.modules.AppModule;
import com.skpissay.baseproject.di.modules.AppNetworkModule;
import com.skpissay.baseproject.di.modules.EssentialModules;
import com.skpissay.baseproject.di.modules.NetworkModule;
import com.skpissay.baseproject.rest.ApiInterface;
import com.skpissay.baseproject.utils.PreferenceUtil;

import dagger.Component;

/**
 * Created by skpissay on 25/06/18.
 */
@ApplicationScope
@Component(modules = {AppModule.class,
        AppNetworkModule.class,
        NetworkModule.class,
        EssentialModules.class,
        AnalyticsModule.class
}
)
public interface AppComponent extends EssentialComponents {

    ApiInterface apiInterface();

    PreferenceUtil preferenceUtil();

    ColorGenerator colorGenerator();

}
