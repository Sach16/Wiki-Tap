package com.skpissay.baseproject.screens.views;

import com.skpissay.baseproject.rest.response.BasicResponse;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public interface HomeView {

    void onPlaces(Response<BasicResponse> response);

    void onError(String message);

    void onCompleted();

    void onDeviceDeregistrationFailed();

    Observable<Response<BasicResponse>> getPlaces(String pAccessToken, Long pPlaceId);

}

