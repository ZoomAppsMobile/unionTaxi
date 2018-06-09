package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.UberApiService;
import com.union.zoomapps.myapplication.mvp.models.UberModel;
import com.union.zoomapps.myapplication.mvp.views.GetUberPriceView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 02.03.2018.
 */
@InjectViewState
public class GetPriceUberPresenter extends BasePresenter<GetUberPriceView> {

    @Inject
    UberApiService uberApiService;
    Disposable disposable;

    public GetPriceUberPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getUberPrices(String token, double start_latitude, double start_longitude, double end_latitude, double end_longitude) {
        final Observable<UberModel> getUberModel = uberApiService.updateUser(token, start_latitude, start_longitude, end_latitude, end_longitude);
        disposable = getUberModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse ->{
                   getViewState().getUberPrices(apiResponse);
                }, error ->{
                    getViewState().getUberPricesError(error.getMessage().toString());
                });

        if(disposable != null) {
            unsubscribeOnDestroy(disposable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null) {
            unsubscribeOnDestroy(disposable);
        }
    }
}
