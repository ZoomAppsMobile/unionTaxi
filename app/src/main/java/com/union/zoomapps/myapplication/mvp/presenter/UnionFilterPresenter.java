package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;
import com.union.zoomapps.myapplication.mvp.views.UnionFilterView;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 01.03.2018.
 */
@InjectViewState
public class UnionFilterPresenter extends BasePresenter<UnionFilterView> {
    @Inject
    ApiInterface apiInterface;
    Disposable disposable;

    public UnionFilterPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getFilterData(Map<String, String> filterMap) {
        final Observable<List<UnionFiterModel>> getfilterModel = apiInterface.getUnionFilter(filterMap);
        disposable = getfilterModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    getViewState().getUnionFilter(apiResponse);
                }, error -> {
                    getViewState().getError(error.getMessage().toString());
                });

        if (disposable != null) {
            unsubscribeOnDestroy(disposable);
        }

    }

    public void  getHomeCatalog(double lot, double lat, String language){
        final Observable<List<UnionFiterModel>> getfilterModel = apiInterface.getHomeCatalog(lot, lat, language);
        disposable = getfilterModel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    getViewState().getHomeCatalog(apiResponse);
                }, error -> {
                    getViewState().getErrorHomeCatalog(error.getMessage().toString());
                });

        if (disposable != null) {
            unsubscribeOnDestroy(disposable);
        }


    }


}
