package com.skpissay.baseproject.screens.views;

import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.models.Wikipedia;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public interface LandingView {

    void onCompleted();

    void onError(String message);

    Observable<Response<Wikipedia>> getWikiList(String format,
                                                String prop,
                                                String generator,
                                                int redirects,
                                                int formatversion,
                                                String piprop,
                                                int pithumbsize,
                                                int pilimit,
                                                String wbptterms,
                                                String gpssearch,
                                                int gpslimit,
                                                String action);

    void onWikiList(Response<Wikipedia> response);
}
