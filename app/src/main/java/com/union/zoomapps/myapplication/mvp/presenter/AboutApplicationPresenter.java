package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.models.AboutApplicationModel;
import com.union.zoomapps.myapplication.mvp.views.AboutApplicationView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 20.03.2018.
 */
@InjectViewState
public class AboutApplicationPresenter extends BasePresenter<AboutApplicationView> {
    @Inject
    ApiInterface apiInterface;
    Disposable disposable;

    public AboutApplicationPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getAboutApplication() {
        Observable<List<AboutApplicationModel>> getAboutApplicationModel = apiInterface.getAboutApplication();
        getAboutApplicationModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                  getViewState().getAboutActicationContent(apiResponse);
                }, error -> {
                    getViewState().getAbountAplicationContentError(error.getMessage().toString());
                });
        if (disposable != null)
            unsubscribeOnDestroy(disposable);

    }
}
