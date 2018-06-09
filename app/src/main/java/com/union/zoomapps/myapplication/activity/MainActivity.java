package com.union.zoomapps.myapplication.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.lyft.deeplink.DeepLink;
import com.lyft.deeplink.DeepLinkParams;
import com.lyft.networking.apiObjects.CostEstimate;
import com.lyft.networking.apiObjects.CostEstimateResponse;
import com.rengwuxian.materialedittext.MaterialEditText;

import com.uber.sdk.android.core.Deeplink;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestDeeplink;
import com.uber.sdk.core.client.SessionConfiguration;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.adapter.AdapterFilterPhone;
import com.union.zoomapps.myapplication.adapter.PriceAdapter;
import com.union.zoomapps.myapplication.adapter.UberAdapter;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.eventBus.model.CurrentLocation;
import com.union.zoomapps.myapplication.mvp.models.MaximModel;
import com.union.zoomapps.myapplication.mvp.models.ModelYandex;
import com.union.zoomapps.myapplication.mvp.models.Requirements;
import com.union.zoomapps.myapplication.mvp.models.UberModel;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;
import com.union.zoomapps.myapplication.mvp.models.YandexJson;
import com.union.zoomapps.myapplication.mvp.models.YandexTransliteModel;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceLyftPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceMaximPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceUberPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.GetPriceYandexPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.UnionFilterPresenter;
import com.union.zoomapps.myapplication.mvp.presenter.YandexTranslitePresenter;
import com.union.zoomapps.myapplication.mvp.views.GetPriceLyftView;
import com.union.zoomapps.myapplication.mvp.views.GetPriceMaximView;
import com.union.zoomapps.myapplication.mvp.views.GetPriceYandexView;
import com.union.zoomapps.myapplication.mvp.views.GetUberPriceView;
import com.union.zoomapps.myapplication.mvp.views.UnionFilterView;
import com.union.zoomapps.myapplication.mvp.views.YandexTransliteView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener, GetPriceYandexView, GetUberPriceView, UnionFilterView, GetPriceLyftView, GetPriceMaximView, YandexTransliteView {
    @BindView(R.id.start_route)
    MaterialEditText start_route;
    @BindView(R.id.end_route)
    MaterialEditText end_route;
    // API key AIzaSyDsEUeVNee-l16oJ1mq6yaOejPT_P6gPI0
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.back_button)
    ImageView back_button;
    @BindView(R.id.phone_list)
    LinearLayout phone_list;
    @BindView(R.id.mWebView)
    WebView mWebView;
    @BindView(R.id.frameLayoutCareem)
    FrameLayout frameLayoutCareem;
    @BindView(R.id.progressBarCareem)
    ProgressBar progressBarCareem;

    @BindView(R.id.name_city_toolbar)
    TextView name_city_toolbar;


    @InjectPresenter
    GetPriceYandexPresenter getPriceYandexPresenter;
    @InjectPresenter
    GetPriceUberPresenter getPriceUberPresenter;
    @InjectPresenter
    UnionFilterPresenter unionFilterPresenter;
    @InjectPresenter
    GetPriceMaximPresenter getPriceMaximPresenter;
    @InjectPresenter
    GetPriceLyftPresenter getPriceLyftPresenter;
    @InjectPresenter
    YandexTranslitePresenter yandexTranslitePresenter;
    View headerview;
    private PriceAdapter priceAdapter;
    private UberAdapter uberAdapter;
    private RecyclerView.LayoutManager priceLayoutManager;
    private RecyclerView.LayoutManager uberLayoutManager;
    private int PLACE_PICKER_REQUEST = 1;
    private PlacePicker.IntentBuilder builder;
    private int PLACE_PICKER_REQUEST_ONE = 2;
    GoogleApiClient mGoogleApiClient;
    LatLng location;
    private double Start_latitude;
    private double Strat_longitude;
    private double End_latitude;
    private double End_longitude;
    private SharedPreferences sharedPreferences;
    private Button send;
    private String geoCoderStart, geoCoderEnd;
    private ActionBarDrawerToggle toggle;
    HashMap hashMap = new HashMap();
    Map googleDiraction = new HashMap();
    double lat, lot;
    ArrayList accounts = new ArrayList();
    List<List<Double>> coordinateYX = new ArrayList<>();
    YandexJson yandexJson = new YandexJson();
    List<Double> paramStart = new ArrayList<>();
    List<Double> paramEnd = new ArrayList<>();
    private FusedLocationProviderClient mFusedLocationClient;
    private ImageView placeholder;
    private Place place;
    private AdapterFilterPhone adapterFilterPhone;
    private RecyclerView.LayoutManager filterPhoneManager;
    private LayoutInflater ltInflater;
    private View view;
    List<UnionFiterModel> unionFiterModels = new ArrayList<>();
    Map<String, String> filterMap = new HashMap<>();
    Intent intent;
    Bundle extras;
    private String X_CSRF_TOKEN;
    private String COOKIE_CAREEM;
    SharedPreferences sPref;
    Menu menu;
    View alertView;
    double googleStartlLat, googleStartlLng, googleEndLat, googleEndLng;
    int googleDuration, googleDistance, currentMonth;
    int carTypeID = 121;
    int paymentType = 0;
    String pickUpTime = "March 19, 2018 9:59";
    String bookingType = "UNCONFIRMED";
    String serviceType = "NORMAL";
    String cashCollection = null;
    String repeat = null;
    boolean needARideAfterCashCollection = false;
    String repeatSurgeToken = null;
    String bookingServiceArea = null;
    String recurringCode = null;
    String cancelBookingIds = null;
    String locationSourceType = null;
    String modeEnd = null;
    String userFixedPackageId = null;
    MaterialEditText emailUser, numberUser, nameUser;
    // Определить клиентский id
    int clientID,
            locationSourceTypeStart,
            locationSourceTypeEnd,
            payment,
            customerCarTypeId,
            useCredit;
    String type98LocationDescription,
            streetAddress, saveAs,
            sourceUuid, sourceUuidEnd,
            googleplaceIdStart,
            googleplaceIdEnd,
            referenceChargeCode,
            notesToDriver,
            email,
            name,
            phoneNumber,
            surgeToken,
            promoCode,
            pickupTimeStart,
            cookies;
    String mode = "RECENT";
    List<Object> pickUpTimes = new ArrayList<>();
    List<Object> pickUpTimesStarts = new ArrayList<>();
    List<Object> setWaypoints = new ArrayList<>();
    Calendar calendar;
    DateFormat dateFormat;
    LocationRequest mLocationRequest;
    LocationCallback mLocationCallback;
    String language = null;
    Boolean checkCustomApi = false, checkUberApi = false, checkYandexApi = false, checkMaximApi = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        createLocationRequest();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mLocationRequest = LocationRequest.create();

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

            Locale locale = new Locale(language);


        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(locale); // API 17+ only.
        }
        res.updateConfiguration(conf, dm);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        try {
            name_city_toolbar.setOnClickListener(v -> {
                intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("openActivity", 1);
                editor.commit();
                startActivity(intent);
            });
        } catch (Exception ex) {

        }


        calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        dateFormat = new SimpleDateFormat("dd , yyyy HH:mm");


        getNawigationDrower(menu,
                this,
                this
        );
        filterPhoneManager = new LinearLayoutManager(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // обновление местоположения
                    if (location != null)
                        EventBus.getDefault().post(new CurrentLocation(location.getLatitude(), location.getLongitude()));


                    try {
                        getCity(location.getLatitude(), location.getLongitude());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        if (sharedPreferences.getString("city_lat", "") == "" && sharedPreferences.getString("city_lot", "") == "") {
            yandexTranslitePresenter.getUandexTranslitePresenter(
                    "trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08",
                    "Не найденно",
                    sharedPreferences.getString("language", language));

        } else {
            EventBus.getDefault().post(new CurrentLocation(Double.parseDouble(sharedPreferences.getString("city_lat", "")), Double.parseDouble(sharedPreferences.getString("city_lot", ""))));
            try {
                start_route.setText(getGeoCoder(Double.parseDouble(sharedPreferences.getString("city_lat", "")), Double.parseDouble(sharedPreferences.getString("city_lot", ""))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CurrentLocation currentLocation) {

        try {
            if (sharedPreferences.getString("city_lat", "") != "" && sharedPreferences.getString("city_lot", "") != "") {
                Start_latitude = Double.parseDouble(sharedPreferences.getString("city_lat", ""));
                Strat_longitude = Double.parseDouble(sharedPreferences.getString("city_lot", ""));
                yandexTranslitePresenter.getUandexTranslitePresenter(
                        "trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08",
                        getCity(Double.parseDouble(sharedPreferences.getString("city_lat", "")), Double.parseDouble(sharedPreferences.getString("city_lot", ""))),
                        sharedPreferences.getString("language", language));
                unionFilterPresenter.getHomeCatalog(Start_latitude,Strat_longitude, sharedPreferences.getString("language", Locale.getDefault().getLanguage()));
            } else {
                Start_latitude = currentLocation.lat;
                Strat_longitude = currentLocation.lng;
                yandexTranslitePresenter.getUandexTranslitePresenter(
                        "trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08",
                        getCity(Start_latitude, Strat_longitude),
                        sharedPreferences.getString("language", language));
                //start_route.setText(getString(R.string.currentLocation));
                start_route.setText(R.string.currentLocation);
                unionFilterPresenter.getHomeCatalog(Start_latitude, Strat_longitude, sharedPreferences.getString("language", language));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }


    public String getGeoCoder(double Lat, double Lot) throws IOException {
        Geocoder geocoderStart, geocoderEnd;
        List<Address> addressesStart, addressesEnd = null;
        geocoderStart = new Geocoder(this, Locale.getDefault());
        geocoderEnd = new Geocoder(this);
        addressesStart = geocoderStart.getFromLocation(Lat, Lot, 1);
        try {
            addressesEnd = geocoderEnd.getFromLocation(Lat, Lot, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String addressStart = addressesStart.get(0).getAddressLine(0);
        String addressEnd = addressesEnd.get(0).getAddressLine(0);
        return addressStart;

    }

    public String getCity(double Lat, double Lot) throws IOException {
        Geocoder geocoderStart, geocoderEnd;
        List<Address> addressesStart, addressesEnd = null;
        geocoderStart = new Geocoder(this, Locale.getDefault());
        geocoderEnd = new Geocoder(this);

        addressesStart = geocoderStart.getFromLocation(Lat, Lot, 1);
        try {
            addressesEnd = geocoderEnd.getFromLocation(Lat, Lot, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String addressStart = addressesStart.get(0).getLocality();
        return addressStart;

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
        }
    }


    @OnClick({R.id.start_route, R.id.end_route})
    public void pickDoor(View view) {
        switch (view.getId()) {
            case R.id.start_route:
                startMaps(PLACE_PICKER_REQUEST);
                break;
            case R.id.end_route:
                startMaps(PLACE_PICKER_REQUEST_ONE);
                break;
        }
    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    public void startMaps(int code) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
                    new LatLng(Double.parseDouble(sharedPreferences.getString("city_lat", "")), Double.parseDouble(sharedPreferences.getString("city_lot", ""))), new LatLng(Double.parseDouble(sharedPreferences.getString("city_lat", "")), Double.parseDouble(sharedPreferences.getString("city_lot", ""))));
            builder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
        } catch (Exception ex) {

        }

        try {
            startActivityForResult(builder.build(this), code);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getLatLng());
                location = place.getLatLng();
                Start_latitude = location.latitude;
                Strat_longitude = location.longitude;
                start_route.setText(place.getAddress());
            } else if (requestCode == PLACE_PICKER_REQUEST_ONE) {
                place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getLatLng());
                location = place.getLatLng();
                End_latitude = location.latitude;
                End_longitude = location.longitude;
                end_route.setText(place.getAddress());

            }
            // запрос на сервер uber
            if (start_route.length() > 1 && end_route.length() > 1) {
                progressBar.setVisibility(View.VISIBLE);
                Long tsLong = System.currentTimeMillis() / 1000;
                String timeStamp = tsLong.toString();
                paramStart.add(Strat_longitude);
                paramStart.add(Start_latitude);
                paramEnd.add(End_longitude);
                paramEnd.add(End_latitude);
                yandexJson.setId(timeStamp);
                yandexJson.setZoneName(geoCoderStart);
                yandexJson.setSupportsForcedSurge(true);
                yandexJson.setParks(accounts);
                yandexJson.setRequirements(new Requirements());
                coordinateYX.add(paramStart);
                coordinateYX.add(paramEnd);
                yandexJson.setRoute(coordinateYX);
                yandexJson.setSkipEstimatedWaiting(true);
                googleDiraction.put("key", "AIzaSyD309u0aebrxgpkk0WCmspN0EvYkRWGml8");
                googleDiraction.put("origin", Start_latitude + "," + Strat_longitude);
                googleDiraction.put("destination", End_latitude + "," + End_longitude);
                Geocoder geocoderStart, geocoderEnd;
                List<Address> addressesStart, addressesEnd;
                geocoderStart = new Geocoder(this, Locale.getDefault());
                geocoderEnd = new Geocoder(this);
                addressesStart = geocoderStart.getFromLocation(Start_latitude, Strat_longitude, 1);
                addressesEnd = geocoderEnd.getFromLocation(End_latitude, End_longitude, 1);
                String addressStart = addressesStart.get(0).getAddressLine(0);
                String addressEnd = addressesEnd.get(0).getAddressLine(0);
                geoCoderStart = addressStart;
                geoCoderEnd = addressEnd;
                yandexTranslitePresenter.getUandexTranslitePresenter(
                        "trnsl.1.1.20180326T091216Z.e0dc0dd76459095c.632cff27ae1cc6924a7058a46e41d048df0e7d08",
                        getCity(Start_latitude, Strat_longitude),
                        sharedPreferences.getString("language", language));
                //start_route.setText(getString(R.string.currentLocation));
                unionFilterPresenter.getHomeCatalog(Strat_longitude, Start_latitude, sharedPreferences.getString("language", language));
                getPriceYandexPresenter.getPriceYandex(yandexJson);
                getPriceUberPresenter.getUberPrices("Token r_MIHiUPLD8FIvp94ON51KxRbGYzURqdLog2ep0X", Start_latitude, Strat_longitude, End_latitude, End_longitude);
                getPriceLyftPresenter.getPriceLyft(Start_latitude, Strat_longitude, "lyft", End_latitude, End_longitude);
                getPriceMaximPresenter.getPriceMaxim(Start_latitude, Strat_longitude, End_latitude, End_longitude);
                phone_list.removeAllViews();
            }
        } catch (Exception ex) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPriceYandexPresenter.onDestroy();
        unionFilterPresenter.onDestroy();
        getPriceUberPresenter.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void getPriceYandex(ModelYandex modelYandex) {
        checkYandexApi = true;
        ltInflater = getLayoutInflater();
        view = ltInflater.inflate(R.layout.price_list, null, false);
        RelativeLayout parent_layout = view.findViewById(R.id.parent_layout);
        ImageView price_icon = view.findViewById(R.id.price_icon);
        TextView nameTaxtPrice = view.findViewById(R.id.nameTaxtPrice);
        TextView priceTaxi = view.findViewById(R.id.priceTaxi);
        nameTaxtPrice.setText(modelYandex.getServiceLevels().get(0).getName().toString());
        priceTaxi.setText("~ " + modelYandex.getServiceLevels().get(0).getPrice().toString());
        price_icon.setImageDrawable(getResources().getDrawable(R.drawable.yandex));
        phone_list.addView(view, 0);
        paramStart.clear();
        paramEnd.clear();
        coordinateYX.clear();
        parent_layout.setOnClickListener(v -> {
            try {
                Uri mUri = Uri.parse("yandextaxi://route/?start-lat=" + Start_latitude + "&start-lon=" + Strat_longitude + "&end-lat=" + End_latitude + "&end-lon=" + End_longitude);
                Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
                startActivity(mIntent);
            } catch (Exception ex) {
                mWebView.setVisibility(View.VISIBLE);
                String addressFrom = geoCoderStart;
                String addressTo = geoCoderEnd;
                Log.e("geoCoderStart", geoCoderStart);
                Log.e("geoCoderEnd", geoCoderEnd);
                mWebView.loadUrl("https://m.taxi.yandex.kz/");
                WebSettings settings = mWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                mWebView.requestFocus(View.FOCUS_DOWN);
                final String js = "javascript:document.getElementById('addressFrom').focus();" + "javascript:document.getElementById('addressFrom').value='" + addressFrom + "';" + "javascript:document.getElementById('addressFrom').blur();" + "javascript:document.getElementById('addressTo').focus();" + "javascript:document.getElementById('addressTo').value='" + addressTo + "';" + "javascript:document.getElementById('addressTo').blur();";
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (Build.VERSION.SDK_INT >= 19) {
                            view.evaluateJavascript(js, new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String s) {

                                }
                            });
                        } else {
                            view.loadUrl(js);
                        }
                    }
                });
            }
        });
        checkLoading();

    }

    @Override
    public void getPriceYandexError(String error) {
        Log.e("yandex", error);
        checkYandexApi = true;
        checkLoading();
    }

    @Override
    public void getUberPrices(UberModel uberModel) {
        checkUberApi = true;
        ltInflater = getLayoutInflater();
        view = ltInflater.inflate(R.layout.price_list, null, false);
        RelativeLayout parent_layout = view.findViewById(R.id.parent_layout);
        ImageView price_icon = view.findViewById(R.id.price_icon);
        TextView nameTaxtPrice = view.findViewById(R.id.nameTaxtPrice);
        TextView priceTaxi = view.findViewById(R.id.priceTaxi);
        nameTaxtPrice.setText(uberModel.getPrices().get(0).getDisplayName().toString());
        int integetUber = (int) uberModel.getPrices().get(0).getHighEstimate() + (int) uberModel.getPrices().get(0).getLowEstimate();
        Log.e("integer", integetUber / 2 + "");
        priceTaxi.setText(integetUber / 2 + " " + uberModel.getPrices().get(0).getCurrencyCode().toString()

        );
        price_icon.setImageDrawable(getResources().getDrawable(R.drawable.uber_icon));
        phone_list.addView(view, 0);

        parent_layout.setOnClickListener(v -> {


            try {
                Uri mUri = Uri.parse("uber://?client_id=lOJWWi__NNz9_tGST2mRDrQkZxLg0OGd&action=setPickup&pickup[latitude]=" + Start_latitude + "&pickup[longitude]=" + Strat_longitude + "&pickup[nickname]=" + geoCoderStart + "[formatted_address]=1455%20Market%20St%2C%20San%20Francisco%2C%20CA%2094103&dropoff[latitude]=" + End_latitude + "&dropoff[longitude]=" + End_longitude + "&dropoff[nickname]=" + geoCoderEnd + "&dropoff[formatted_address]=1%20Telegraph%20Hill%20Blvd%2C%20San%20Francisco%2C%20CA%2094133&product_id=3133457d-e4cf-4442-83b8-b857cd493e3d&link_text=View%20team%20roster&partner_deeplink=partner%3A%2F%2Fteam%2F9383");
                Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
                startActivity(mIntent);
            } catch (Exception ex) {
                SessionConfiguration config = new SessionConfiguration.Builder()
                        .setClientId("lOJWWi__NNz9_tGST2mRDrQkZxLg0OGd")
                        .setClientSecret("KA.eyJ2ZXJzaW9uIjoyLCJpZCI6InByMkRNWHE5UVlteWlnUHdRNy82R2c9PSIsImV4cGlyZXNfYXQiOjE1MjI4MjUyNzcsInBpcGVsaW5lX2tleV9pZCI6Ik1RPT0iLCJwaXBlbGluZV9pZCI6MX0.U6gyU-zGeF8Ooi10NCoZwYh4lLL3JDzq53HKZHP8h9U")
                        .setServerToken("r_MIHiUPLD8FIvp94ON51KxRbGYzURqdLog2ep0X")
                        .build();
                UberSdk.initialize(config);
                RideParameters rideParams = new RideParameters.Builder()
                        .setProductId("3133457d-e4cf-4442-83b8-b857cd493e3d")
                        .setPickupLocation(Start_latitude, Strat_longitude, geoCoderStart, "")
                        .setDropoffLocation(End_latitude, End_longitude, geoCoderEnd, "")
                        .build();

                RideRequestDeeplink deeplink = new RideRequestDeeplink.Builder(MainActivity.this)
                        .setSessionConfiguration(config)
                        .setFallback(Deeplink.Fallback.MOBILE_WEB)
                        .setRideParameters(rideParams)
                        .build();

                deeplink.execute();
                String uri = deeplink.getUri().toString();
                Log.e("uriUber", uri);
            }


        });
        checkLoading();

    }

    @Override
    public void getUberPricesError(String error) {
        Log.e("uber", error);
        checkUberApi = true;
        checkLoading();
    }


    @Override
    public void getUnionFilter(List<UnionFiterModel> getUnionFilter) {

    }

    @Override
    public void getError(String error) {
        Log.e("error", error);
    }

    @Override
    public void getHomeCatalog(List<UnionFiterModel> gethomeCatalog) {
        //phone_list.removeAllViews();
        checkCustomApi = true;
        for (int i = 0; i < gethomeCatalog.size(); i++) {
            ltInflater = getLayoutInflater();
            view = ltInflater.inflate(R.layout.list_filter_phone, null, false);
            TextView nameTaxi = (TextView) view.findViewById(R.id.nameTaxi);
            TextView adres_taxi = (TextView) view.findViewById(R.id.adres_taxi);
            TextView numbers_taxi = (TextView) view.findViewById(R.id.numbers_taxi);
            ImageView image_phone = view.findViewById(R.id.image_phone);
            try {
                nameTaxi.setText(gethomeCatalog.get(i).getAlias().toString());
                adres_taxi.setText(gethomeCatalog.get(i).getAdress().toString());
                numbers_taxi.setText(gethomeCatalog.get(i).getPhone().toString());
                Log.e("getUnionFilter", gethomeCatalog.get(i).getPhone().toString());
            } catch (Exception ex) {
                Log.e("nameTaxi", ex.getMessage().toString());
            }
            phone_list.addView(view);
            image_phone.setOnClickListener(v -> {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                builderSingle.setIcon(R.drawable.icon_phone_svg);
                builderSingle.setTitle("Выбирите номер телефона");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.phone_list_catalog);
                List<String> list = Arrays.asList(numbers_taxi.getText().toString().split(","));
                for (int ph = 0; ph < list.size(); ph++) {
                    arrayAdapter.add(list.get(ph));
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
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Позвонить?");
                        builderInner.setPositiveButton("Позвонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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

            });
        }
        checkLoading();

    }

    @Override
    public void getErrorHomeCatalog(String error) {
        Log.e("getErrorHomeCatalog", error);
        checkCustomApi = true;
        checkLoading();
    }


    @Override
    public void getPrice(CostEstimateResponse costEstimateResponse) {
        CostEstimate eta = costEstimateResponse.cost_estimates.get(0);
        if (eta != null) {
            ltInflater = getLayoutInflater();
            view = ltInflater.inflate(R.layout.price_list, null, false);
            RelativeLayout parent_layout = view.findViewById(R.id.parent_layout);
            ImageView price_icon = view.findViewById(R.id.price_icon);
            TextView nameTaxtPrice = view.findViewById(R.id.nameTaxtPrice);
            TextView priceTaxi = view.findViewById(R.id.priceTaxi);
            price_icon.setImageDrawable(getResources().getDrawable(R.drawable.lyft_icon));
            nameTaxtPrice.setText(eta.ride_type.toString());
            int priceLyft = eta.estimated_cost_cents_max;
            priceTaxi.setText("~" + new BigDecimal(priceLyft).movePointLeft(2) + " " + eta.currency.toString());
            phone_list.addView(view, 0);
            parent_layout.setOnClickListener(v -> {
                DeepLinkParams deepLinkParams = new DeepLinkParams.Builder()
                        .setClientId("KUX56aAZyCbi")
                        .setRideType("lyft")
                        .setPickupLocation(Start_latitude, Strat_longitude)
                        .setDropoffLocation(End_latitude, End_longitude)
                        .build();
                DeepLink.launchLyftApp(MainActivity.this, deepLinkParams);
            });
        }

    }

    @Override
    public void getErrorLyft(String error) {
        Log.e("estimate_lyft", error);
    }

    @Override
    public void getPriceMaxim(MaximModel maximModel) {
        checkMaximApi = true;
        ltInflater = getLayoutInflater();
        view = ltInflater.inflate(R.layout.price_list, null, false);
        RelativeLayout parent_layout = view.findViewById(R.id.parent_layout);
        ImageView price_icon = view.findViewById(R.id.price_icon);
        TextView nameTaxtPrice = view.findViewById(R.id.nameTaxtPrice);
        TextView priceTaxi = view.findViewById(R.id.priceTaxi);
        nameTaxtPrice.setText("Maxim");
        priceTaxi.setText(maximModel.getPrice() + " " + maximModel.getCurrencyCode());
        price_icon.setImageDrawable(getResources().getDrawable(R.drawable.maxim_icom));
        phone_list.addView(view, 0);
        phone_list.setOnClickListener(v -> {

            try {
                if (geoCoderStart != null && geoCoderEnd != null) {
                    String maximDeepLink = "maximzakaz://order?startAddressName=" + geoCoderStart + "&startLatitude=" + Start_latitude + "&startLongitude=" + Strat_longitude + "&endAddressName=" + geoCoderEnd + "&endLatitude=" + End_latitude + "&endLongitude=" + End_longitude;
                    //String url = URLEncoder.encode(maximDeepLink, "UTF-8");
                    Uri mUri = Uri.parse(maximDeepLink);
                    Intent mIntent = new Intent("maximzakaz.action.ORDER", mUri);
                    for (int i = 0; i <= 2; i++) {
                        startActivity(mIntent);
                    }

                }
            } catch (Exception ex) {
                String maximDeepLink = "market://details?id=com.taxsee.taxsee";
                //String url = URLEncoder.encode(maximDeepLink, "UTF-8");
                Uri mUri = Uri.parse(maximDeepLink);
                Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
                startActivity(mIntent);
            }
        });
        checkLoading();
    }

    @Override
    public void getErrorMaxim(String error) {
        Toast.makeText(this, "error Maxim", Toast.LENGTH_SHORT).show();
        Log.e("getErrorMaxim", error);
        checkLoading();
    }


    @Override
    public void getTransliteYandex(YandexTransliteModel yandexTransliteModel) {
        name_city_toolbar.setVisibility(View.VISIBLE);
        name_city_toolbar.setText(yandexTransliteModel.getText().get(0).toString());
    }

    @Override
    public void getTransliteYandexError(String error) {
        Log.e("getTransliteYandexError", error);
    }

    public void checkLoading(){
        if(checkCustomApi == false || checkUberApi == false || checkYandexApi == false || checkMaximApi == false){
            progressBar.setVisibility(View.VISIBLE);
            phone_list.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            phone_list.setVisibility(View.VISIBLE);
        }



    }
}