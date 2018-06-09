package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.UberModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Asus on 02.03.2018.
 */

public interface UberApiService {

    @GET("v1.2/estimates/price")
    Observable<UberModel> updateUser(
            @Header("Authorization") String token,
            @Query("start_latitude") double start_latitude,
            @Query("start_longitude") double start_longitude,
            @Query("end_latitude") double end_latitude,
            @Query("end_longitude") double end_longitude
    );
}
