package com.skpissay.baseproject.di.modules;

import com.skpissay.baseproject.di.ApplicationScope;
import com.skpissay.baseproject.rest.ApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class AppNetworkModule {

    @Provides
    @ApplicationScope
    synchronized ApiInterface provideApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}

