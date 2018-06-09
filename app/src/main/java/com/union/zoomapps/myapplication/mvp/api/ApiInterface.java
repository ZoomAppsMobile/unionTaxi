package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.AboutApplicationModel;
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Asus on 01.03.2018.
 */

public interface ApiInterface {
    @GET("api/test2")
    Observable<List<UnionFiterModel>> getUnionFilter(@QueryMap Map<String, String> getQuery);

    @GET("api/countries")
    Observable<List<CountryModel>> getCountru(@Query("language") String language);

    @GET("api/cities")
    Observable<List<CityModel>> getCity(@Query("language") String language,
                                        @Query("country") int countryId
    );

    @GET("api/content/1")
    Observable<List<AboutApplicationModel>> getAboutApplication();

    @GET("api/latlon")
    Observable<List<UnionFiterModel>> getHomeCatalog(
            @Query("lon") double lot,
            @Query("lat") double lat,
            @Query("language") String language
    );

}
