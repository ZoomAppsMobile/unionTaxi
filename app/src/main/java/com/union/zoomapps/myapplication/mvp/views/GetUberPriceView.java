package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.UberModel;

/**
 * Created by Asus on 02.03.2018.
 */

public interface GetUberPriceView  extends MvpView{
    void getUberPrices(UberModel uberModel);
    void getUberPricesError(String error);
}
