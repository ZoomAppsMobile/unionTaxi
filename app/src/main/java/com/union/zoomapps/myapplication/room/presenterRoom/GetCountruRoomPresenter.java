package com.union.zoomapps.myapplication.room.presenterRoom;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.union.zoomapps.myapplication.App;
import com.union.zoomapps.myapplication.base.basePresenter.BasePresenter;
import com.union.zoomapps.myapplication.mvp.models.CountryModel;
import com.union.zoomapps.myapplication.room.DaoBase;
import com.union.zoomapps.myapplication.room.DataBase;
import com.union.zoomapps.myapplication.room.viewRoom.ViewCountruRoom;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 28.03.2018.
 */
@InjectViewState
public class GetCountruRoomPresenter extends BasePresenter<ViewCountruRoom> {
    Disposable disposable;
    public void getCountryLocal(Context context) {
        Flowable<List<CountryModel>> listObservable = DataBase.getAppDatabase(context, "country").daos().selectCountry();
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    getViewState().getCountruRoom(apiResponse);
                }, error -> {
                    getViewState().getErrorCountryRoom(error.getMessage().toString());
                });
        if (disposable != null)
            unsubscribeOnDestroy(disposable);

    }
}
