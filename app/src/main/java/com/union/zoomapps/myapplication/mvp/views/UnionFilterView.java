package com.union.zoomapps.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;

import java.util.List;

/**
 * Created by Asus on 01.03.2018.
 */

public interface UnionFilterView extends MvpView {
    void getUnionFilter(List<UnionFiterModel> getUnionFilter);
    void getError(String error);
    void getHomeCatalog(List<UnionFiterModel> getHomeCatalog);
    void getErrorHomeCatalog(String error);
}
