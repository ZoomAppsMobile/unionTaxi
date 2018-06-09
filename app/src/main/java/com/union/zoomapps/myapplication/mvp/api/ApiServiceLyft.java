package com.union.zoomapps.myapplication.mvp.api;

import com.lyft.networking.apiObjects.CostEstimateResponse;
import com.union.zoomapps.myapplication.mvp.models.LyftModel;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Asus on 12.03.2018.
 */

public interface ApiServiceLyft {
    @GET("/v1/cost")
    Observable<CostEstimateResponse> getCosts(@Query("start_lat") Double startLat, @Query("start_lng") Double startLng,
                                              @Query("ride_type") String rideType, @Query("end_lat") Double endLat, @Query("end_lng") Double endLng);
}
