package com.skpissay.baseproject;

import android.app.Application;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.amplitude.api.Amplitude;
import com.facebook.stetho.Stetho;
import com.mobsandgeeks.saripaar.Validator;
import com.skpissay.baseproject.di.components.AppComponent;
import com.skpissay.baseproject.di.components.DaggerAppComponent;
import com.skpissay.baseproject.di.modules.AppModule;
import com.skpissay.baseproject.di.modules.EssentialModules;
import com.skpissay.baseproject.essentials.IResultListener;
import com.skpissay.baseproject.essentials.utils.AppBuilderConfig;

import timber.log.Timber;

/**
 * Created by skpissay on 25/06/18.
 */

public class BaseApp extends Application{

    public static final String TAG = "BASE_APP_TAG";
    public static final String BASE_URL = "https://en.wikipedia.org/";

    private AppComponent mComponent;
    private int mSecondsLeft = 0;

    private CountDownTimer mCountDownTimer = new CountDownTimer(60000, 100) {
        @Override
        public void onTick(long l) {
            if (Math.round((float) l / 1000.0f) != mSecondsLeft) {
                mSecondsLeft = Math.round((float) l / 1000.0f);
                if (mTimerListener != null)
                    mTimerListener.onResult(mSecondsLeft);
            }

        }

        @Override
        public void onFinish() {
            if (mTimerListener != null)
                mTimerListener.onResult(0);
        }
    };

    private IResultListener<Integer> mTimerListener;

    @Override
    public void onCreate() {
        super.onCreate();
        /*Amplitude.getInstance().initialize(this, BuildConfig.AMPLITUDE_KEY);

        if (BuildConfig.IS_FABRIC_REQUIRED) {
            Fabric.with(this, new Crashlytics());
        }*/

        Stetho.initializeWithDefaults(this);

        /*Validator.registerAnnotation(PhoneNumber.class, TextView.class, new TextViewLongAdapter());
        Validator.registerAnnotation(OTP.class, TextView.class, new TextViewLongAdapter());*/

        //init logger for debug builds
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag(TAG);
        }

        AppBuilderConfig appBuilderConfig =
                new AppBuilderConfig(BASE_URL, "", "", BuildConfig.VERSION_NAME,
                        BuildConfig.VERSION_CODE, "!@#");
        // init dagger Application component
        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .essentialModules(new EssentialModules(this, appBuilderConfig))
                .build();
    }

    public void startTimer(IResultListener<Integer> listener) {
        mTimerListener = listener;
        mCountDownTimer.start();
    }

    public void stopTimer() {
        mTimerListener = null;
        mCountDownTimer.cancel();
    }

    /**
     * Helper method to get ApplicationComponent
     *
     * @return
     */
    public AppComponent getComponent() {
        return mComponent;
    }
}

