package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.YandexTransliteModel;

/**
 * Created by Asus on 26.03.2018.
 */

public interface YandexTransliteView extends MvpView {
    void getTransliteYandex(YandexTransliteModel yandexTransliteModel);
    void getTransliteYandexError(String error);
}
