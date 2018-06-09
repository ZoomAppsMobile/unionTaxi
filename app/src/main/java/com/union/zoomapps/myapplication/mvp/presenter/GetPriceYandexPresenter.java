package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.api.YandexApiService;
import com.union.zoomapps.myapplication.mvp.models.ModelYandex;
import com.union.zoomapps.myapplication.mvp.models.YandexJson;
import com.union.zoomapps.myapplication.mvp.views.GetPriceYandexView;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 02.03.2018.
 */
@InjectViewState
public class GetPriceYandexPresenter extends BasePresenter<GetPriceYandexView> {
    @Inject
    YandexApiService apiInterface;
    Disposable disposable;

    public GetPriceYandexPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getPriceYandex(YandexJson json){
        final Observable<ModelYandex> getYandexModel = apiInterface.getYandexPrice(json);
        disposable = getYandexModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse ->{
                   getViewState().getPriceYandex(apiResponse);
                }, error ->{
                    getViewState().getPriceYandexError(error.getMessage().toString());
                });
        if(disposable != null){
            unsubscribeOnDestroy(disposable);
        }
    }



}
