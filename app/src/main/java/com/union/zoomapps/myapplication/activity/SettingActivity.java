package com.union.zoomapps.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCountryPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCountryView;
import com.union.zoomapps.myapplication.room.DataBase;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 28.02.2018.
 */

public class SettingActivity extends BaseActivity implements GetCountryView {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.english_language)
    ConstraintLayout english_language;

    @BindView(R.id.russian_language)
    ConstraintLayout russian_language;

    @BindView(R.id.china_language)
    ConstraintLayout china_language;

    @BindView(R.id.qazak_language)
    ConstraintLayout qazak_language;

    @InjectPresenter
    GetCountryPresenter getCountryPresenter;

    private ActionBarDrawerToggle toggle;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        getNawigationDrower(menu,
                SettingActivity.this,
                this
        );
        intent = getIntent();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        switch (sharedPreferences.getString(getString(R.string.language), "").toString()){
            case "ru":
                russian_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                break;
            case "en":
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                break;
            case "zh": // 	chi/zho	zho
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                break;
            case "kk":
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                break;
        }

    }

    @OnClick({R.id.english_language, R.id.russian_language, R.id.qazak_language, R.id.china_language})
    public void onClickLanguage(View view) {
        switch (view.getId()) {
            case R.id.russian_language:
                setSharedPreferences("ru");
                russian_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                Toast.makeText(this, getString(R.string.installation_language), Toast.LENGTH_SHORT).show();
                break;
            case R.id.english_language:
                setSharedPreferences("en");
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                Toast.makeText(this, getString(R.string.installation_language), Toast.LENGTH_SHORT).show();
                break;
            case R.id.china_language:
                setSharedPreferences("zh");
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                Toast.makeText(this, getString(R.string.installation_language), Toast.LENGTH_SHORT).show();
                break;

            case R.id.qazak_language:
                setSharedPreferences("kk");
                russian_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                english_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                qazak_language.setBackground(getResources().getDrawable(R.drawable.active_language));
                china_language.setBackground(getResources().getDrawable(R.drawable.no_active_language));
                Toast.makeText(this, getString(R.string.installation_language), Toast.LENGTH_SHORT).show();
                break;
        }

    }
// trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08

    @Override
    protected int getLayoutResourceId() {
        return R.layout.setting_activity;
    }

    public void setSharedPreferences(String value) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getCountryPresenter.getCounry(value);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.language), value);
        editor.commit();

    }

    @Override
    public void getCountruView(List<CountryModel> countryModels) {
        DataBase.getAppDatabase(this, "country").daos().deleteAllCountry();
        DataBase.getAppDatabase(this, "country").daos().insertCountry(countryModels);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void getCountryError(String error) {

    }
}
