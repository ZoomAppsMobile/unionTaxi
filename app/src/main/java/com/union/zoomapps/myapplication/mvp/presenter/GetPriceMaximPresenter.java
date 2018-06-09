package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiServiceMaxim;
import com.union.zoomapps.myapplication.mvp.models.MaximModel;
import com.union.zoomapps.myapplication.mvp.views.GetPriceMaximView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 13.03.2018.
 */
@InjectViewState
public class GetPriceMaximPresenter extends BasePresenter<GetPriceMaximView> {
    @Inject
    ApiServiceMaxim apiServiceMaxim;
    Disposable disposable;

    public GetPriceMaximPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getPriceMaxim(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        Observable<MaximModel> getPriceMaximModel = apiServiceMaxim.getPreceMaxim(startLatitude, startLongitude, endLatitude, endLongitude);
        disposable = getPriceMaximModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse ->{
                   getViewState().getPriceMaxim(apiResponse);
                }, error ->{
                   getViewState().getErrorMaxim(error.getMessage().toString());
                });
        if(disposable != null)
            unsubscribeOnDestroy(disposable);
    }
}
