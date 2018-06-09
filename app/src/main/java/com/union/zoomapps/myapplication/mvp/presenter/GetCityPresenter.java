package com.union.zoomapps.myapplication.mvp.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.views.GetCityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 01.03.2018.
 */
@InjectViewState
public class GetCityPresenter extends BasePresenter<GetCityView> {

    @Inject
    ApiInterface apiInterface;
    Disposable disposable;
    public GetCityPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getCity(String language, int countryId) {
        final Observable<List<CityModel>> cityModel = apiInterface.getCity(language, countryId);
        disposable = cityModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    getViewState().getCityView(apiResponse);
                }, error -> {
                    getViewState().getCityError(error.getMessage().toString());
                });

        if (disposable != null) {
            unsubscribeOnDestroy(disposable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            unsubscribeOnDestroy(disposable);
        }
    }
}
