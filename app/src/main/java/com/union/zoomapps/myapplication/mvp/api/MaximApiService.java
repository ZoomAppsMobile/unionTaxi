package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.MaximModel;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Asus on 13.03.2018.
 */

public interface MaximApiService {
    @GET("")
    Observable<MaximModel> getPriceMaxim();
}
