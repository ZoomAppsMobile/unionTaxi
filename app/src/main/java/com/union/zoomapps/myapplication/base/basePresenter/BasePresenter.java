package com.union.zoomapps.myapplication.base.basePresenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Asus on 28.02.2018.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    protected void unsubscribeOnDestroy(@NonNull Disposable subscription) {
        if (subscription != null) {
            compositeSubscription.add(subscription);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
        compositeSubscription.dispose();
    }
}
