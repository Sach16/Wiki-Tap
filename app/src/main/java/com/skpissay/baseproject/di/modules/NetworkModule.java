package com.skpissay.baseproject.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.skpissay.baseproject.essentials.HttpInterceptor;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class NetworkModule {

    public NetworkModule() {
    }

    @Provides
    public Cache provideCache(Context context) {
        File file = new File(context.getCacheDir(), "okhttp-app");
        file.mkdirs();
        return new Cache(file, 102400L);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache, HttpInterceptor interceptor, StethoInterceptor stethoInterceptor) {
        return (new OkHttpClient.Builder()).addInterceptor(interceptor).addInterceptor(httpLoggingInterceptor).cache(cache).addNetworkInterceptor(stethoInterceptor).build();
    }

    @Provides
    public StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    public synchronized Retrofit provideAdapter(OkHttpClient client, GsonConverterFactory gsonFactory, RxJavaCallAdapterFactory adapterFactory, @Named("baseUrl") String baseUrl) {
        return (new retrofit2.Retrofit.Builder()).client(client).baseUrl(baseUrl).addConverterFactory(gsonFactory).addCallAdapterFactory(adapterFactory).build();
    }

    @Provides
    @NonNull
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @NonNull
    public GsonConverterFactory provideGsonConverter() {
        Gson gson = new Gson();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @NonNull
    public RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }
}

