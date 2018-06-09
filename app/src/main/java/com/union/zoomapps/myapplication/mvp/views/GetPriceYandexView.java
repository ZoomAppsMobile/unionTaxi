package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.ModelYandex;

/**
 * Created by Asus on 02.03.2018.
 */

public interface GetPriceYandexView  extends MvpView{
    void getPriceYandex(ModelYandex modelYandex);
    void getPriceYandexError(String error);
}
