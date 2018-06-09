package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.ModelYandex;
import com.union.zoomapps.myapplication.mvp.models.YandexJson;
import com.union.zoomapps.myapplication.mvp.models.YandexTransliteModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Asus on 26.03.2018.
 */

public interface YandexTransliteApi {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("api/v1.5/tr.json/translate")
    Observable<YandexTransliteModel> getYandexTransite(
            @Query("key") String key,
            @Query("text") String text,
            @Query("lang") String lang
    );
}
