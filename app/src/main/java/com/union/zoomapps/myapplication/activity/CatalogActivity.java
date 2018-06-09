package com.union.zoomapps.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.adapter.AdapterFilterPhone;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.custom.CheckableImageView;
import com.union.zoomapps.myapplication.custom.OpenClickFragment;
import com.union.zoomapps.myapplication.custom.OpenFragment;
import com.union.zoomapps.myapplication.custom.RecyclerItemClickListener;
import com.union.zoomapps.myapplication.eventBus.PostCityId;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;
import com.union.zoomapps.myapplication.mvp.models.YandexTransliteModel;
import com.union.zoomapps.myapplication.mvp.presenter.UnionFilterPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.YandexTranslitePresenter;
import com.union.zoomapps.myapplication.mvp.views.UnionFilterView;
import com.union.zoomapps.myapplication.mvp.views.YandexTransliteView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatalogActivity extends BaseActivity implements UnionFilterView, YandexTransliteView {
    @BindView(R.id.recycler_list_filter)
    RecyclerView recycler_list_filter;
    private AdapterFilterPhone adapterFilterPhone;
    private RecyclerView.LayoutManager filterPhoneManager;
    List<UnionFiterModel> unionFiterModels = new ArrayList<>();
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.clock_layout)
    LinearLayout clock_layout;
    @BindView(R.id.card_layout)
    LinearLayout card_layout;
    @BindView(R.id.car_ecomom_layout)
    LinearLayout car_ecomom_layout;
    @BindView(R.id.car_comfort_layout)
    LinearLayout car_comfort_layout;
    @BindView(R.id.car_minivan_layout)
    LinearLayout car_minivan_layout;
    @BindView(R.id.start_fragment)
    FrameLayout start_fragment;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.imageClock)
    ImageView imageClock;
    @BindView(R.id.imageCard)
    ImageView imageCard;
    @BindView(R.id.imageEconomCar)
    ImageView imageEconomCar;
    @BindView(R.id.imageComfort)
    ImageView imageComfort;
    @BindView(R.id.imageMinivan)
    ImageView imageMinivan;
    @BindView(R.id.prigressBar_catalog)
    ProgressBar prigressBar_catalog;
    @BindView(R.id.countru)
    TextView countru;
    Map<String, String> filterMap = new HashMap<>();
    Intent intent;
    Bundle extras;
    @InjectPresenter
    UnionFilterPresenter unionFilterPresenter;
    @InjectPresenter
    YandexTranslitePresenter yandexTranslitePresenter;
    SharedPreferences sPref;
    Menu menu;
    SharedPreferences sharedPreferences;


    @Override
    protected void onStart() {
        super.onStart();
        // EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        yandexTranslitePresenter.getUandexTranslitePresenter(
                "trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08",
                sharedPreferences.getString("city_name_toolbar", "Не найденно"),
                sharedPreferences.getString("language", Locale.getDefault().getLanguage())
        );
        getNawigationDrower(menu,
                this,
                this
        );
        recycler_list_filter.setHasFixedSize(true);
        filterPhoneManager = new LinearLayoutManager(this);
        recycler_list_filter.
                setLayoutManager(filterPhoneManager);
        extras = getIntent().getExtras();
        prigressBar_catalog.setVisibility(View.VISIBLE);

        if (sharedPreferences.getString("city_id", "") == "")
            unionFilterPresenter.getFilterData(filterMap);
        else {
            filterMap.put("city", sharedPreferences.getString("city_id", ""));
            unionFilterPresenter.getFilterData(filterMap);
        }
        recycler_list_filter.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler_list_filter, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView textView = view.findViewById(R.id.numbers_taxi);
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(CatalogActivity.this);
                        builderSingle.setIcon(R.drawable.icon_phone_svg);
                        builderSingle.setTitle("Выбирите номер телефона");
                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CatalogActivity.this, R.layout.phone_list_catalog);
                        List<String> list = Arrays.asList(textView.getText().toString().split(","));
                        for (int i = 0; i < list.size(); i++) {
                            arrayAdapter.add(list.get(i));
                        }
                        builderSingle.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(CatalogActivity.this);
                                builderInner.setMessage(strName);
                                builderInner.setTitle("Позвонить?");
                                builderInner.setPositiveButton("Позвонить", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strName));
//
//                                                if (ActivityCompat.checkSelfPermission(CatalogActivity.this,
//                                                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                                                    return;
//                                                }

                                        try {
                                            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strName));
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            //TODO smth
                                        }


                                        startActivity(intent);

                                        dialog.dismiss();
                                    }
                                });
                                builderInner.show();
                            }
                        });
                        builderSingle.show();


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void setPostCityId(PostCityId postCityId) {
//        intent = new Intent(CatalogActivity.this, CityActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("cityId", postCityId.id);
//        startActivity(intent);
//    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.catalog_activity;
    }

    @OnClick(R.id.countru)
    public void onClickCountry() {
        intent = new Intent(CatalogActivity.this, CountryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("openActivity", 0);
        editor.commit();

    }


    @Override
    public void getUnionFilter(List<UnionFiterModel> getUnionFilter) {
        adapterFilterPhone = new AdapterFilterPhone(getUnionFilter);
        recycler_list_filter.setAdapter(adapterFilterPhone);
        prigressBar_catalog.setVisibility(View.GONE);

    }

    @OnClick({R.id.clock_layout, R.id.card_layout, R.id.car_ecomom_layout, R.id.car_comfort_layout, R.id.car_minivan_layout})
    public void CheckedImage(View view) {

        switch (view.getId()) {
            case R.id.clock_layout:

                if (extras != null) {
                    sharedCheck(clock_layout,imageClock, textView4, filterMap, "quick", "", "city", String.valueOf(extras.getString("city_id")));
                } else {
                    sharedCheck(clock_layout,imageClock, textView4, filterMap, "quick", "", "", "");
                }

                break;
            case R.id.card_layout:

                if (extras != null) {
                    sharedCheck(card_layout,imageCard, textView5, filterMap, "card", "", "city", String.valueOf(extras.getString("city_id")));
                } else {
                    sharedCheck(card_layout,imageCard, textView5, filterMap, "card", "", "", "");
                }

                break;
            case R.id.car_ecomom_layout:
                if (extras != null) {
                    sharedCheck(car_ecomom_layout, imageEconomCar, textView6, filterMap, "economy", "", "city", String.valueOf(extras.getString("city_id")));
                } else {
                    sharedCheck(car_ecomom_layout, imageEconomCar, textView6, filterMap, "economy", "", "", "");
                }
                break;

            case R.id.car_comfort_layout:
                if (extras != null) {;
                    sharedCheck(car_comfort_layout, imageComfort, textView7, filterMap, "comfort", "", "city", String.valueOf(extras.getString("city_id")));
                } else {
                    sharedCheck(car_comfort_layout, imageComfort, textView7, filterMap, "comfort", "", "", "");
                }

                break;
            case R.id.car_minivan_layout:
                if (extras != null) {
                    sharedCheck(car_minivan_layout, imageMinivan, textView8, filterMap, "minivan", "", "city", String.valueOf(extras.getString("city_id")));
                } else {
                    sharedCheck(car_minivan_layout, imageMinivan, textView8, filterMap, "minivan", "", "", "");
                }

                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unionFilterPresenter.onDestroy();
//        OpenClickFragment.openClickFragment = null;

    }


    private void sharedCheck(LinearLayout linearLayout, ImageView imageView, TextView textView, Map map, String key, String value, String cityKey, String cityValue) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.contains(key)) {
            Toast.makeText(this, "check", Toast.LENGTH_SHORT).show();
            linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_active));
             switch (imageView.getId()){
                 case R.id.imageClock:
                     imageView.setBackground(getResources().getDrawable(R.drawable.clock_icon));
                     break;
                 case R.id.imageCard:
                     imageView.setBackground(getResources().getDrawable(R.drawable.credit_cars_icon));
                     break;
                 case R.id.imageEconomCar:
                     imageView.setBackground(getResources().getDrawable(R.drawable.econom_car_icon));
                     break;
                 case R.id.imageComfort:
                     imageView.setBackground(getResources().getDrawable(R.drawable.comfort_icon));
                     break;
                 case R.id.imageMinivan:
                     imageView.setBackground(getResources().getDrawable(R.drawable.minivan_icon));
                     break;
             }
            map.put(key, value);
            if (cityKey != "" && cityValue != "") {
                map.put(cityKey, cityValue);
            }
            textView.setTextColor(Color.parseColor("#000000"));
            editor.putString(key, value);
            editor.commit();
        } else {
            editor.remove(key);
            editor.apply();




//            editor.putString(key, value);
//            editor.commit();
            Toast.makeText(this, "noCheck", Toast.LENGTH_SHORT).show();
            linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_no_active));
            switch (imageView.getId()){
                case R.id.imageClock:
                    imageView.setBackground(getResources().getDrawable(R.drawable.clock_icon_bit));
                    break;
                case R.id.imageCard:
                    imageView.setBackground(getResources().getDrawable(R.drawable.credit_cars_icon_bit));
                    break;
                case R.id.imageEconomCar:
                    imageView.setBackground(getResources().getDrawable(R.drawable.econom_car_icon_bit));
                    break;
                case R.id.imageComfort:
                    imageView.setBackground(getResources().getDrawable(R.drawable.comfort_icon_bit));
                    break;
                case R.id.imageMinivan:
                    imageView.setBackground(getResources().getDrawable(R.drawable.minivan_icon_bit));
                    break;
            }
            textView.setTextColor(Color.parseColor("#f6be00"));
            map.remove(key);
        }

        if (cityKey != "" && cityValue != "") {
            map.put(cityKey, cityValue);
        }
        prigressBar_catalog.setVisibility(View.VISIBLE);
        unionFilterPresenter.getFilterData(filterMap);

    }

    public void isChek(LinearLayout linearLayout, CheckBox checkBox, TextView textView, Map map, String key, String value, String cityKey, String cityValue) {
        if (checkBox != null) {
            if (checkBox.isChecked()) {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_active));
                textView.setTextColor(Color.parseColor("#000000"));
                map.put(key, value);
                if (cityKey != "" && cityValue != "") {
                    map.put(cityKey, cityValue);
                }
            } else {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_no_active));
                map.remove(key);
                textView.setTextColor(Color.parseColor("#f6be00"));
            }
        }

        if (cityKey != "" && cityValue != "") {
            map.put(cityKey, cityValue);
        }
        prigressBar_catalog.setVisibility(View.VISIBLE);
        unionFilterPresenter.getFilterData(filterMap);
    }


    public void addParamFilter(TextView textView, LinearLayout linearLayout, CheckableImageView checkableImageView, Map map, String key, String value, String cityKey, String cityValue) {
        checkableImageView.setSelected(!checkableImageView.isSelected());
        Log.e("active", checkableImageView.isSelected() + "");
        if (checkableImageView.isSelected()) {
            if (linearLayout != null) {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_active));
            }
            textView.setTextColor(Color.parseColor("#000000"));
            map.put(key, key);
            if (cityKey != "" && cityValue != "") {
                map.put(cityKey, cityValue);
            }
        } else {
            map.remove(key);

            if (textView != null) {
                textView.setTextColor(Color.parseColor("#f6be00"));
            }
            linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_no_active));
        }
        unionFilterPresenter.getFilterData(filterMap);
        Log.e("check", checkableImageView.isSelected() + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void getError(String error) {
        Log.e("error", error);
    }

    @Override
    public void getHomeCatalog(List<UnionFiterModel> getHomeCatalog) {

    }

    @Override
    public void getErrorHomeCatalog(String error) {

    }


    @Override
    public void getTransliteYandex(YandexTransliteModel yandexTransliteModel) {
        //countru.setText(sharedPreferences.getString("city_name_toolbar", ""));
        countru.setText(yandexTransliteModel.getText().get(0).toString());
    }

    @Override
    public void getTransliteYandexError(String error) {

    }
}
