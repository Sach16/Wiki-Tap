package com.skpissay.baseproject.screens.activities;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.skpissay.baseproject.BaseApp;
import com.skpissay.baseproject.R;
import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.di.components.DaggerHomeActivityComponent;
import com.skpissay.baseproject.di.components.HomeActivityComponent;
import com.skpissay.baseproject.di.modules.HomeActivityModule;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.screens.ui.assist.FragmentHelper;
import com.skpissay.baseproject.screens.views.HomeView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    FragmentHelper mFragmentHelper;

    private HomeActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mComponent = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .appComponent(((BaseApp) getApplication()).getComponent())
                .build();

        mComponent.inject(this);

        mFragmentHelper.switchUi(FragmentHelper.LANDING, null);
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    public HomeActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onPlaces(Response<BasicResponse> response) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onDeviceDeregistrationFailed() {

    }

    @Override
    public Observable<Response<BasicResponse>> getPlaces(String pAccessToken, Long pPlaceId) {
        return null;
    }
}
