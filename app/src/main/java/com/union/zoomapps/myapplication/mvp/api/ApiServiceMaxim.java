package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.MaximModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Asus on 19.03.2018.
 */

public interface ApiServiceMaxim {
    @GET("Services/Public.svc/CalculateByCoords")
    Observable<MaximModel> getPreceMaxim(@Query("startLatitude") double startLatitude,
                                         @Query("startLongitude") double startLongitude,
                                         @Query("endLatitude") double endLatitude,
                                         @Query("endLongitude") double endLongitude
                                         );
}
