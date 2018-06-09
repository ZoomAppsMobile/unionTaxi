package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.CityModel;

import java.util.List;

/**
 * Created by Asus on 01.03.2018.
 */

public interface GetCityView extends MvpView {
    void getCityView (List<CityModel> cityModels);
    void getCityError(String error);
}
