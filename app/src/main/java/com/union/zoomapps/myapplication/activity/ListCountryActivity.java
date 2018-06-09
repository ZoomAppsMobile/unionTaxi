package com.union.zoomapps.myapplication.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.adapter.ListCountryAdapter;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.custom.OpenClickFragment;
import com.union.zoomapps.myapplication.custom.RecyclerItemClickListener;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCountryPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCountryView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 28.02.2018.
 */

public class ListCountryActivity extends MvpAppCompatActivity implements GetCountryView {

    private List<CountryModel> modelCountrListList = new ArrayList<>();
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.back_button)
    ImageView back_button;

    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @BindView(R.id.openFragment)
    FrameLayout openFragment;


    RecyclerView.LayoutManager mLayoutManager;
    ListCountryAdapter mAdapter;

    @InjectPresenter
    GetCountryPresenter getCountryPresenter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_country_layout);
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        toolbar_text.setText(getString(R.string.country));
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getCountryPresenter.getCounry(sharedPreferences.getString("language", "en"));
        back_button.setOnClickListener(v -> onBackPressed());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView textView = view.findViewById(R.id.id_country);
                        OpenClickFragment.openClickFragment.openCity(Integer.parseInt(textView.getText().toString()));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }



    @Override
    public void getCountruView(List<CountryModel> countryModels) {
        mAdapter = new ListCountryAdapter(countryModels);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getCountryError(String error) {
        Toast.makeText(this, "Ошибка: " + error + ", попробуйте еще раз", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCountryPresenter.onDestroy();
    }

}
