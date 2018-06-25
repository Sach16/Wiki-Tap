package com.skpissay.baseproject.screens.presenters;

import com.skpissay.baseproject.essentials.BasePresenter;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.screens.views.HomeView;

import retrofit2.Response;
import rx.Observer;

/**
 * Created by skpissay on 25/06/18.
 */

public class HomePresenter extends BasePresenter implements Observer<Response<BasicResponse>> {
    private HomeView mHomeActivityView;

    public HomePresenter(HomeView homeActivityView) {
        mHomeActivityView = homeActivityView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onCompleted() {
        mHomeActivityView.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mHomeActivityView.onError(e.getMessage());
    }

    @Override
    public void onNext(Response<BasicResponse> response) {
        mHomeActivityView.onPlaces(response);
    }

    /*private Observer<Response<PushResponse>> pushListener = new Observer<Response<PushResponse>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.d("onError", e.getMessage());
        }

        @Override
        public void onNext(Response<PushResponse> responseResponse) {
            mHomeActivityView.onDeviceRegisterSuccess(responseResponse);
        }
    };

    void closeDeviceToken(long omsUserId, PushCloseRequest request) {
        unSubscribeMain();
        subscribe(mHomeActivityView.getDeviceDeRegistered(omsUserId, request), deviceDeReg);
    }

    void sendDeviceToken(long omsUserId, PushRequest pushRequest) {
        unSubscribeSecondary();
        subscribeForSecondary(mHomeActivityView.getDeviceRegistered(omsUserId, pushRequest), pushListener);
    }*/


    void getPlaces(String accessToken, Long omsUserId) {
        unSubscribeMain();
        subscribe(mHomeActivityView.getPlaces(accessToken, omsUserId), this);
    }

    @Override
    public void onDestroy() {
        unSubscribeMain();
        unSubscribeSecondary();
        unSubscribeSearch();
    }
}
