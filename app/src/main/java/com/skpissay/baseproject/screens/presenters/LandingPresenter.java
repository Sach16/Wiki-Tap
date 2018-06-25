package com.skpissay.baseproject.screens.presenters;

import com.skpissay.baseproject.essentials.BasePresenter;
import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.models.Wikipedia;
import com.skpissay.baseproject.screens.views.LandingView;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;

/**
 * Created by skpissay on 25/06/18.
 */

public class LandingPresenter extends BasePresenter {

    final LandingView mLandingView;

    public LandingPresenter(LandingView mLandingView) {
        this.mLandingView = mLandingView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    Observer<Response<Wikipedia>> mObserverWikiList = new Observer<Response<Wikipedia>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response<Wikipedia> wikipediaResponse) {
            mLandingView.onWikiList(wikipediaResponse);
        }
    };

    public void getWikiList(String format,
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
                            String action) {
        unSubscribeMain();
        subscribe(mLandingView.getWikiList(format,
                prop,
                generator,
                redirects,
                formatversion,
                piprop,
                pithumbsize,
                pilimit,
                wbptterms,
                gpssearch,
                gpslimit,
                action), mObserverWikiList);
    }

    @Override
    public void onDestroy() {
        unSubscribeMain();
        unSubscribeSecondary();
        unSubscribeSearch();
    }
}
