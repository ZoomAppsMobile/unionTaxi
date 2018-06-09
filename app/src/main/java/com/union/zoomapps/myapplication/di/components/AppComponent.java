package com.union.zoomapps.myapplication.di.components;

import android.content.Context;
import android.support.annotation.NonNull;

import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.di.modules.AppModule;
import com.union.zoomapps.myapplication.mvp.presenter.AboutApplicationPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetCityPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetCountryPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceMaximPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceUberPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceYandexPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.UnionFilterPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.YandexTranslitePresenter;
import com.union.zoomapps.myapplication.room.presenterRoom.GetCountruRoomPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Asus on 28.02.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public abstract class  AppComponent {
    public static AppComponent from(@NonNull Context context){
        return ((App) context.getApplicationContext()).getAppComponent();
    }
    public abstract  void  inject(UnionFilterPresenter unionFilterPresenter);
    public abstract  void  inject(App app);
    public abstract void  inject(GetCountryPresenter getCountryPresenter);
    public abstract void inject(GetCityPresenter getCityPresenter);
    public abstract void  inject(GetPriceYandexPresenter getPriceYandexPresenter);
    public abstract void  inject(GetPriceUberPresenter getPriceUberPresenter);
    public abstract void inject(GetPriceMaximPresenter getPriceMaximPresenter);
    public abstract void inject(AboutApplicationPresenter aboutSplicationPresenter);
    public abstract void  inject(YandexTranslitePresenter yandexTranslitePresenter);
   // public abstract void inject(GetCountruRoomPresenter getCountruRoomPresenter);
}
