package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.MaximModel;

/**
 * Created by Asus on 13.03.2018.
 */

public interface GetPriceMaximView extends MvpView {
    void getPriceMaxim(MaximModel maximModel);
    void getErrorMaxim(String error);
}
