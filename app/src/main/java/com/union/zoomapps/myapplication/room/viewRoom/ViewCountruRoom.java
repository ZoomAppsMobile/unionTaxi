package com.union.zoomapps.myapplication.room.viewRoom;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;

import java.util.List;

/**
 * Created by Asus on 28.03.2018.
 */

public interface ViewCountruRoom  extends MvpView{
    void getCountruRoom(List<CountryModel> countryModels);
    void getErrorCountryRoom(String error);
}
