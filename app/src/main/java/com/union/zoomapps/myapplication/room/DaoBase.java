package com.union.zoomapps.myapplication.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Asus on 26.03.2018.
 */
@android.arch.persistence.room.Dao
public interface DaoBase {
    @Insert
    void insertCountry(List<CountryModel> country);

    @Insert
    void insertCity(List<CityModel> city);

    @Query("DELETE FROM country")
    void deleteAllCountry();

//   @Query("SELECT * FROM country")
//    public abstract List<CountryModel> selectCountry();

     @Query("SELECT * FROM country")
     Flowable <List<CountryModel>> selectCountry();


    @Query("SELECT * FROM city")
    public abstract List<CityModel> selectCity();
}
