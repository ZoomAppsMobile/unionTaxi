package com.union.zoomapps.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.activity.CatalogActivity;
import com.union.zoomapps.myapplication.adapter.CityAdapter;
import com.union.zoomapps.myapplication.custom.RecyclerItemClickListener;
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetCityPresenter;
import com.union.zoomapps.myapplication.mvp.views.GetCityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 01.03.2018.
 */

public class ListCityActivity extends MvpAppCompatActivity implements GetCityView {

    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @BindView(R.id.recycler_list_city)
    RecyclerView recycler_list_city;

    @BindView(R.id.back_button)
    ImageView back_button;
    
    @InjectPresenter
    GetCityPresenter getCityPresenter;

    CityAdapter cityAdapter;
    RecyclerView.LayoutManager layoutManager;
    Intent intent;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_city_fragment);
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        toolbar_text.setText(getString(R.string.city));
        layoutManager = new LinearLayoutManager(this);
        recycler_list_city.setLayoutManager(layoutManager);
        recycler_list_city.setItemAnimator(new DefaultItemAnimator());
         intent = getIntent();
        if (intent != null) {
            getCityPresenter.getCity(sharedPreferences.getString("language", "en"),intent.getIntExtra("country_id", 0));
        }
        back_button.setOnClickListener(v -> onBackPressed());
        recycler_list_city.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler_list_city ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView city_id = view.findViewById(R.id.id_city);
                        TextView city_name = view.findViewById(R.id.name_city);
                        intent = new Intent(ListCityActivity.this, CatalogActivity.class);
                        intent.putExtra("city_id", city_id.getText().toString());
                        intent.putExtra("city_name", city_name.getText().toString());;
                        startActivity(intent);
//                        getActivity().finish();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_city_fragment, container, false);
//        ButterKnife.bind(this, view);
//        toolbar_text.setText(getString(R.string.city));
//        layoutManager = new LinearLayoutManager(getContext());
//        recycler_list_city.setLayoutManager(layoutManager);
//        recycler_list_city.setItemAnimator(new DefaultItemAnimator());
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            getCityPresenter.getCity(bundle.getInt("country_id", 0));
//        }
//        back_button.setOnClickListener(v -> getActivity().getFragmentManager().popBackStack());
//        recycler_list_city.addOnItemTouchListener(
//                new RecyclerItemClickListener(getContext(), recycler_list_city ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        TextView city_id = view.findViewById(R.id.id_city);
//                        TextView city_name = view.findViewById(R.id.name_city);
//                        intent = new Intent(getContext(), CatalogActivity.class);
//                        intent.putExtra("city_id", city_id.getText().toString());
//                        intent.putExtra("city_name", city_name.getText().toString());;
//                        startActivity(intent);
////                        getActivity().finish();
//
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
//                    }
//                })
//        );
//
//
//        return view;
//
//    }




    @Override
    public void getCityView(List<CityModel> cityModels) {
        cityAdapter = new CityAdapter(cityModels);
        recycler_list_city.setAdapter(cityAdapter);
    }

    @Override
    public void getCityError(String error) {
        Toast.makeText(this, "Ошибка: " + error+ ", попробуйте еще раз", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCityPresenter.onDestroy();
    }
}
