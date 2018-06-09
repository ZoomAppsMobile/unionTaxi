package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;

import java.util.List;

/**
 * Created by Asus on 01.03.2018.
 */

public interface GetCountryView extends MvpView {
    void getCountruView(List<CountryModel> countryModels);
    void getCountryError(String error);
}
