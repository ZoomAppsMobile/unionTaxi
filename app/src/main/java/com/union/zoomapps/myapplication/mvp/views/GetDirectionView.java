package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.GoogleDirectionModel;

/**
 * Created by Asus on 07.03.2018.
 */

public interface GetDirectionView extends MvpView {
    void getDirection(GoogleDirectionModel googleDirectionModel);
    void directionError(String error);
}
