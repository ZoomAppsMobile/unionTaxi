package com.union.zoomapps.myapplication.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.lyft.networking.ApiConfig;
import com.lyft.networking.LyftApiFactory;
import com.lyft.networking.apiObjects.CostEstimateResponse;
import com.lyft.networking.apis.LyftPublicApi;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.views.GetPriceLyftView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus on 12.03.2018.
 */
@InjectViewState
public class GetPriceLyftPresenter extends BasePresenter<GetPriceLyftView> {
   public void getPriceLyft(double start_lat, double start_lng, String ride_type, double end_lat, double end_lng){
       ApiConfig apiConfig = new ApiConfig.Builder()
               .setClientId("KUX56aAZyCbi")
               .setClientToken("1WzzPWIGxZKbX9wiQgtT1CIZ/OFjeIAHpqPNxXW9wOZzpCMV+G4Auih4V8A2W02FRtRguyRMxnx0VTPPZuI8I+VmCP901Qacoq6eDOk5wKBnQt0Khf6/wqc=")
               .build();
       LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
       Call<CostEstimateResponse> etaCall = lyftPublicApi.getCosts(start_lat, start_lng, ride_type, end_lat, end_lng);
       etaCall.enqueue(new Callback<CostEstimateResponse>() {
           @Override
           public void onResponse(Call<CostEstimateResponse> call, Response<CostEstimateResponse> response) {
               CostEstimateResponse etaEstimateResponse = response.body();
               if(etaEstimateResponse != null)
                   getViewState().getPrice(etaEstimateResponse);
           }
           @Override
           public void onFailure(Call<CostEstimateResponse> call, Throwable t) {
                getViewState().getErrorLyft(t.getMessage().toString());
           }
       });





//        final Observable<CostEstimateResponse> getLyftModel = lyftPublicApiRx.getCosts(start_lat, start_lng, ride_type, end_lat, end_lng);
//        getLyftModel.observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(apiResponse ->{
//                   getViewState().getPrice(apiResponse);
//                }, error ->{
//                     getViewState().getErrorLyft(error.getMessage().toString());
//                });



    }


}
