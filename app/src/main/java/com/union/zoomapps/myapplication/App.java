package com.union.zoomapps.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.union.zoomapps.myapplication.di.components.AppComponent;
import com.union.zoomapps.myapplication.di.components.DaggerAppComponent;
import com.union.zoomapps.myapplication.di.modules.AppModule;


import java.util.Locale;

/**
 * Created by Asus on 01.03.2018.
 */

public class App extends Application {
    private static AppComponent appComponent = null;
    private  Resources resources;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


}
