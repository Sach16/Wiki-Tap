package com.skpissay.baseproject.di.modules;

import android.app.Application;
import android.content.Context;

import com.skpissay.baseproject.di.ApplicationScope;
import com.skpissay.baseproject.utils.IntentHelper;
import com.skpissay.baseproject.utils.PreferenceUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }


    @Provides
    @ApplicationScope
    PreferenceUtil providePreferenceUtil(Context context) {
        return new PreferenceUtil(context);
    }

    @Provides
    @ApplicationScope
    IntentHelper provideIntentHelper(Context context) {
        return new IntentHelper(context);
    }


    /* @Provides
     PreferenceUtil providePreferenceUtil() {
         return new PreferenceUtil(mApplication);
     }

     @Provides
     IntentHelper provideIntentHelper() {
         return new IntentHelper(mApplication);
     }

     @Provides
     NetworkUtil provideNetworkUtil() {
         return new NetworkUtil();
     }

     @Provides
     NetworkChangeReceiver provideNetworkChangeReceiver(NetworkUtil networkUtil) {
         return new NetworkChangeReceiver();
     }

     @Provides
     ColorGenerator provideColorGenerator() {
         return ColorGenerator.MATERIAL;
     }
 */

}
