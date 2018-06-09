package com.union.zoomapps.myapplication.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCityPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetCountryPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCityView;
import com.union.zoomapps.myapplication.mvp.views.GetCountryView;
import com.union.zoomapps.myapplication.room.DataBase;
import com.union.zoomapps.myapplication.room.viewRoom.ViewCountruRoom;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Asus on 28.02.2018.
 */
@RuntimePermissions
public class SplashActivity extends BaseActivity implements GetCountryView, GetCityView {
    SharedPreferences sharedPreferences;
    @InjectPresenter
    GetCityPresenter getCityPresenter;
    @InjectPresenter
    GetCountryPresenter getCountryPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        switch (Locale.getDefault().getLanguage()){
            case "ru":
                getCountryPresenter.getCounry(Locale.getDefault().getLanguage());
                break;
            case "kk":
                getCountryPresenter.getCounry(Locale.getDefault().getLanguage());
                break;
            case "en":
                getCountryPresenter.getCounry(Locale.getDefault().getLanguage());
                break;
            case "zh":
                getCountryPresenter.getCounry(Locale.getDefault().getLanguage());
                break;
                default:
                    getCountryPresenter.getCounry("en");
                    break;
        }
    }

    @Override
    public void getCountruView(List<CountryModel> countryModels) {
        DataBase.getAppDatabase(this, "country").daos().deleteAllCountry();
        DataBase.getAppDatabase(this, "country").daos().insertCountry(countryModels);
        SplashActivityPermissionsDispatcher.showActivityMapsWithPermissionCheck(this);
    }


    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void showActivityMaps() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                edit().clear().apply();


    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.locationAsk)
                .setPositiveButton(R.string.pirmisson, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.nopermission, (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void showDeniedForCamera() {
        startActivity(new Intent(SplashActivity.this, CountryActivity.class));
        EventBus.getDefault().post(1);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.profile_activity;
    }


    @Override
    public void getCountryError(String error) {
     Log.e("getCountryError", error);
    }

    @Override
    public void getCityView(List<CityModel> cityModels) {

    }

    @Override
    public void getCityError(String error) {
        Log.e("getCityError", error);
    }


}
