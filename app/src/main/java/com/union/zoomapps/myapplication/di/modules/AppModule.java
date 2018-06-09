package com.union.zoomapps.myapplication.di.modules;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.google.gson.GsonBuilder;
import com.union.zoomapps.myapplication.base.baseUrl.BaseUrl;
import com.union.zoomapps.myapplication.mvp.api.ApiInterface;
import com.union.zoomapps.myapplication.mvp.api.ApiServiceMaxim;
import com.union.zoomapps.myapplication.mvp.api.UberApiService;
import com.union.zoomapps.myapplication.mvp.api.YandexApiService;
import com.union.zoomapps.myapplication.mvp.api.YandexTransliteApi;
import com.union.zoomapps.myapplication.room.DataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 01.03.2018.
 */
@Module
public class AppModule {

    Context context;
    final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ApiInterface getApiRervice() {

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }


    @Provides
    @Singleton
    YandexApiService getYandexInterface(){

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.yandexUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YandexApiService.class);
    }

    @Provides
    @Singleton
    UberApiService getUberApiResvice(){

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.uberUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UberApiService.class);
    }



    @Provides
    @Singleton
    ApiServiceMaxim apiServiceMaxim(){
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.maximUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServiceMaxim.class);
    }


    @Provides
    @Singleton
    YandexTransliteApi yandexTranslite(){
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.yandexTransliteApi)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YandexTransliteApi.class);
    }




}
