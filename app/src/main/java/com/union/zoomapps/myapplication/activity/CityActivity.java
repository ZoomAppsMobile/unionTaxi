package com.union.zoomapps.myapplication.activity;


import android.app.ActivityManager;
import android.app.SearchManager;
import android.arch.persistence.room.Entity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.adapter.CityAdapter;
import com.union.zoomapps.myapplication.constants.Constants;
import com.union.zoomapps.myapplication.custom.RecyclerItemClickListener;
import com.union.zoomapps.myapplication.eventBus.PostCityId;
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCityPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCityView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.union.zoomapps.myapplication.constants.Constants.ACTIVITY;
import static com.union.zoomapps.myapplication.constants.Constants.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

/**
 * Created by Asus on 01.03.2018.
 */

public class CityActivity extends MvpAppCompatActivity implements GetCityView {

    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @BindView(R.id.recycler_list_city)
    RecyclerView recycler_list_city;

    @BindView(R.id.back_button)
    ImageView back_button;

    @BindView(R.id.progress_bar_city)
    ProgressBar progress_bar_city;

    @BindView(R.id.constraint_progress_bar_city)
    ConstraintLayout constraint_progress_bar_city;

    @BindView(R.id.toolbarSearch)
    android.support.v7.widget.Toolbar toolbarSearch;
    @InjectPresenter
    GetCityPresenter getCityPresenter;

    CityAdapter cityAdapter;
    RecyclerView.LayoutManager layoutManager;
    Intent intent;
    SharedPreferences sharedPreferences;
    SearchView searchView;
    String language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_city_fragment);
        ButterKnife.bind(this);
        toolbar_text.setText(getString(R.string.city));
        progress_bar_city.setVisibility(View.VISIBLE);
        constraint_progress_bar_city.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        recycler_list_city.setLayoutManager(layoutManager);
        recycler_list_city.setItemAnimator(new DefaultItemAnimator());
        setSupportActionBar(toolbarSearch);
        intent = getIntent();
        if (intent != null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            switch (Locale.getDefault().getLanguage()) {
                case "ru":
                    language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
                    break;
                case "kk":
                    language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
                    break;
                case "en":
                    language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
                    break;
                case "zh":
                    language = sharedPreferences.getString("language", Locale.getDefault().getLanguage());
                    break;
                default:
                    language = sharedPreferences.getString("language", "en");
                    break;
            }
            getCityPresenter.getCity(sharedPreferences.getString("language", language), intent.getIntExtra("cityId", 0));
        }
        back_button.setOnClickListener(v -> onBackPressed());
        recycler_list_city.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler_list_city, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView city_id = view.findViewById(R.id.id_city);
                        TextView city_name = view.findViewById(R.id.name_city);
                        TextView city_lat = view.findViewById(R.id.city_lat);
                        TextView city_lot = view.findViewById(R.id.city_lot);
                        intent.putExtra("city_id", city_id.getText().toString());
                        intent.putExtra("city_name", city_name.getText().toString());
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("city_name_toolbar", city_name.getText().toString());
                        editor.putString("city_id", city_id.getText().toString());
                        editor.putString("city_lat", city_lat.getText().toString());
                        editor.putString("city_lot", city_lot.getText().toString());
                        editor.commit();
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        switch (sharedPreferences.getInt("openActivity", 0)){
                            case 0:
                                intent = new Intent(CityActivity.this, CatalogActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(CityActivity.this, MainActivity.class);
                                startActivity(intent);
                                break;
                        }

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_city, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cityAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                cityAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override
    public void getCityView(List<CityModel> cityModels) {
        cityAdapter = new CityAdapter(cityModels);
        recycler_list_city.setAdapter(cityAdapter);
        progress_bar_city.setVisibility(View.GONE);
        constraint_progress_bar_city.setVisibility(View.GONE);
    }
    @Override
    public void getCityError(String error) {
        Toast.makeText(this, "Ошибка: " + error + ", попробуйте еще раз", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCityPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
