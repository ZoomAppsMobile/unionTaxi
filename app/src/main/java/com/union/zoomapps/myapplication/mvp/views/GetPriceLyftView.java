package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.lyft.networking.apiObjects.CostEstimateResponse;
import com.union.zoomapps.myapplication.mvp.models.LyftModel;

/**
 * Created by Asus on 12.03.2018.
 */

public interface GetPriceLyftView extends MvpView {
    void getPrice(CostEstimateResponse lyftModel);
    void getErrorLyft(String error);
}
