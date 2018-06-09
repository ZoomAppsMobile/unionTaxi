package com.union.zoomapps.myapplication.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;

/**
 * Created by Asus on 26.03.2018.
 */
@Database(entities = {CountryModel.class, CityModel.class}, version = 2, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract DaoBase daos();
    private static DataBase INSTENCE;

    public static DataBase getAppDatabase(Context context, String tablename) {
        if (INSTENCE == null) {
            INSTENCE =
                     Room.databaseBuilder(context, DataBase.class, tablename)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTENCE;
    }

    public static void destroyInstance() {
        INSTENCE = null;
    }


}
