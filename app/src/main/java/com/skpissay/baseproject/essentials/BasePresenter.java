package com.skpissay.baseproject.essentials;

import com.skpissay.baseproject.essentials.utils.Presenter;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by skpissay on 25/06/18.
 */

public class BasePresenter implements Presenter {
    private CompositeSubscription mMainSubscription;
    private CompositeSubscription mSearchSubscription;
    private CompositeSubscription mSecondarySubscription;

    public BasePresenter() {
    }

    public void onCreate() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        this.unSubscribeAll();
    }

    protected void unSubscribeAll() {
        if(this.mMainSubscription != null && this.mMainSubscription.hasSubscriptions()) {
            this.mMainSubscription.unsubscribe();
            this.mMainSubscription.clear();
        }

        if(this.mSearchSubscription != null && this.mSearchSubscription.hasSubscriptions()) {
            this.mSearchSubscription.unsubscribe();
            this.mSearchSubscription.clear();
        }

        if(this.mSecondarySubscription != null && this.mSecondarySubscription.hasSubscriptions()) {
            this.mSecondarySubscription.unsubscribe();
            this.mSecondarySubscription.clear();
        }

    }

    protected void unSubscribeMain() {
        if(this.mMainSubscription != null && this.mMainSubscription.hasSubscriptions()) {
            this.mMainSubscription.unsubscribe();
            this.mMainSubscription.clear();
        }

    }

    protected void unSubscribeSearch() {
        if(this.mSearchSubscription != null && this.mSearchSubscription.hasSubscriptions()) {
            this.mSearchSubscription.unsubscribe();
            this.mSearchSubscription.clear();
        }

    }

    protected void unSubscribeSecondary() {
        if(this.mSecondarySubscription != null && this.mSecondarySubscription.hasSubscriptions()) {
            this.mSecondarySubscription.unsubscribe();
            this.mSecondarySubscription.clear();
        }

    }

    private CompositeSubscription configureSubscription() {
        if(this.mMainSubscription == null || this.mMainSubscription.isUnsubscribed()) {
            this.mMainSubscription = new CompositeSubscription();
        }

        return this.mMainSubscription;
    }

    private CompositeSubscription configureSearchSubscription() {
        if(this.mSearchSubscription == null || this.mSearchSubscription.isUnsubscribed()) {
            this.mSearchSubscription = new CompositeSubscription();
        }

        return this.mSearchSubscription;
    }

    private CompositeSubscription configureSecondarySubscription() {
        if(this.mSecondarySubscription == null || this.mSecondarySubscription.isUnsubscribed()) {
            this.mSecondarySubscription = new CompositeSubscription();
        }

        return this.mSecondarySubscription;
    }

    protected <T> void subscribe(Observable<Response<T>> observable, Observer<Response<T>> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        this.configureSubscription().add(subscription);
    }

    protected <T> void subscribeForSecondary(Observable<Response<T>> observable, Observer<Response<T>> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        this.configureSecondarySubscription().add(subscription);
    }

    protected <T> void subscribeForSearch(Observable<Response<T>> observable, Observer<Response<T>> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        this.configureSearchSubscription().add(subscription);
    }

    protected String errorMessage(Throwable e) {
        return e.getMessage().contains("resolve host")?"Please check your internet connection and try again":e.getMessage();
    }

    protected <T> void subscribeForDBOperations(Observable<T> observable, Observer<T> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        this.configureSubscription().add(subscription);
    }
}
