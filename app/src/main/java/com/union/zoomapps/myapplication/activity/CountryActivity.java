package com.union.zoomapps.myapplication.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.adapter.ListCountryAdapter;
import com.union.zoomapps.myapplication.custom.RecyclerItemClickListener;
import com.union.zoomapps.myapplication.eventBus.PostCityId;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCountryPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCountryView;
import com.union.zoomapps.myapplication.room.DataBase;
import com.union.zoomapps.myapplication.room.presenterRoom.GetCountruRoomPresenter;
import com.union.zoomapps.myapplication.room.viewRoom.ViewCountruRoom;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 28.02.2018.
 */

public class CountryActivity extends MvpAppCompatActivity implements ViewCountruRoom {

    private List<CountryModel> modelCountrListList = new ArrayList<>();
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.back_button)
    ImageView back_button;

    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @BindView(R.id.openFragment)
    FrameLayout openFragment;

    @BindView(R.id.toolbarSearch)
    android.support.v7.widget.Toolbar toolbarSearch;


    RecyclerView.LayoutManager mLayoutManager;
    ListCountryAdapter mAdapter;

    //    @InjectPresenter
//    GetCountryPresenter getCountryPresenter;
    @InjectPresenter
    GetCountruRoomPresenter getCountryPresenter;
    SharedPreferences sharedPreferences;
    Intent intent;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.list_country_layout);
        ButterKnife.bind(this);
        getCountryPresenter.getCountryLocal(this);
        setSupportActionBar(toolbarSearch);
        toolbar_text.setText(getString(R.string.country));
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // getCountryPresenter.getCounry(sharedPreferences.getString("language", Locale.getDefault().getLanguage()));
        back_button.setOnClickListener(v -> onBackPressed());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView textView = view.findViewById(R.id.id_country);
                        EventBus.getDefault().post(new PostCityId(Integer.parseInt(textView.getText().toString())));
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
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    //    @Override
//    public void getCountruView(List<CountryModel> countryModels) {
//        mAdapter = new ListCountryAdapter(countryModels);
//        recyclerView.setAdapter(mAdapter);
//
//    }
//
//    @Override
//    public void getCountryError(String error) {
//        Log.e("getCountryError", error);
//        Toast.makeText(this, "Ошибка: " + error + ", попробуйте еще раз", Toast.LENGTH_LONG).show();
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setPostCityId(PostCityId postCityId) {
        intent = new Intent(CountryActivity.this, CityActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("cityId", postCityId.id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // getCountryPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getCountruRoom(List<CountryModel> countryModels) {
        mAdapter = new ListCountryAdapter(countryModels);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getErrorCountryRoom(String error) {
        Log.e("getCountryError", error);
        Toast.makeText(this, "Ошибка: " + error + ", попробуйте еще раз", Toast.LENGTH_LONG).show();
    }
}
