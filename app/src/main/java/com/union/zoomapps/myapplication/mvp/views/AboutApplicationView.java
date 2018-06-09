package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.AboutApplicationModel;

import java.util.List;

/**
 * Created by Asus on 20.03.2018.
 */

public interface AboutApplicationView extends MvpView {
    void getAboutActicationContent(List<AboutApplicationModel> aboutApplicationModel);
    void getAbountAplicationContentError(String error);
}
