package com.union.zoomapps.myapplication.base.baseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.activity.AboutApplicationActivity;
import com.union.zoomapps.myapplication.activity.CatalogActivity;
import com.union.zoomapps.myapplication.activity.MainActivity;
import com.union.zoomapps.myapplication.activity.ProfileActivity;
import com.union.zoomapps.myapplication.activity.SettingActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Asus on 28.02.2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    @Nullable
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    Intent intent;
    Activity context;
    protected Unbinder binder;
    private MenuItem home, setting, catalog, aboutApp;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:

                intent = new Intent(this, SettingActivity.class);
                break;
            case R.id.catalog:
                intent = new Intent(this, CatalogActivity.class);
                break;
            case R.id.apps_description:
                intent = new Intent(this, AboutApplicationActivity.class);
                break;
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void getNawigationDrower(Menu menu, Context context, Activity activity) {
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_navigation, R.string.open_navigation){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


            }

        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        menu = navigationView.getMenu();
        home = menu.findItem(R.id.home);
        setting = menu.findItem(R.id.setting);
        catalog = menu.findItem(R.id.home);
        aboutApp = menu.findItem(R.id.home);
        ImageView avatar = (ImageView) headerview.findViewById(R.id.placeholder);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent = new Intent(context, ProfileActivity.class);
//                startActivity(intent);
            }
        });

    }



    protected abstract @LayoutRes
    int getLayoutResourceId();

    public String getLaunguage() {
        sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(getString(R.string.language), "");
    }
}
