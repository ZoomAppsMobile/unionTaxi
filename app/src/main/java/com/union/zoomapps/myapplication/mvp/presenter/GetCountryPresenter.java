package com.union.zoomapps.myapplication.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.views.GetCountryView;

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
public class GetCountryPresenter extends BasePresenter<GetCountryView> {

    @Inject
    ApiInterface apiInterface;
    Disposable disposable;



    public GetCountryPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getCounry(String language){
        final Observable<List<CountryModel>> getCountruModel = apiInterface.getCountru(language);

        disposable = getCountruModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse ->{
                   getViewState().getCountruView(apiResponse);
                }, error ->{
                    getViewState().getCountryError(error.getMessage().toString());
                });

        if(disposable != null){
            unsubscribeOnDestroy(disposable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            unsubscribeOnDestroy(disposable);
        }

    }
}
