package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.api.YandexTransliteApi;
import com.union.zoomapps.myapplication.mvp.models.YandexTransliteModel;
import com.union.zoomapps.myapplication.mvp.views.YandexTransliteView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 26.03.2018.
 */
@InjectViewState
public class YandexTranslitePresenter extends BasePresenter<YandexTransliteView> {
    @Inject
    YandexTransliteApi yandexTransliteApi;
    Disposable disposable;

    public YandexTranslitePresenter() {
        App.getAppComponent().inject(this);
    }

    public void getUandexTranslitePresenter(String key, String text, String lang ){
        Observable<YandexTransliteModel> yandexTransliteModelObservable = yandexTransliteApi.getYandexTransite(key, text, lang);
       disposable = yandexTransliteModelObservable.observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(apiRespinse ->{
                 getViewState().getTransliteYandex(apiRespinse);
               },error ->{
                   getViewState().getTransliteYandexError(error.getMessage().toString());
               });
      if(disposable != null)
          unsubscribeOnDestroy(disposable);
    }

}
