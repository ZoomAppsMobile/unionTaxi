package com.union.zoomapps.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.base.baseActivity.BaseActivity;
import com.union.zoomapps.myapplication.mvp.models.AboutApplicationModel;
import com.union.zoomapps.myapplication.mvp.presenter.AboutApplicationPresenter;
import com.union.zoomapps.myapplication.mvp.views.AboutApplicationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 28.02.2018.
 */

public class AboutApplicationActivity extends BaseActivity implements AboutApplicationView {
    @InjectPresenter
    AboutApplicationPresenter aboutApplicationPresenter;

    @BindView(R.id.aboutAplicationText)
    TextView aboutAplicationText;
    @BindView(R.id.aboutAplicationTitle)
    TextView aboutAplicationTitle;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        getNawigationDrower(menu,
                this,
                this
        );
        aboutApplicationPresenter.getAboutApplication();
    }


    @Override
    public void getAboutActicationContent(List<AboutApplicationModel> aboutApplicationModel) {
        aboutAplicationTitle.setText(aboutApplicationModel.get(0).getContent().toString());
        aboutAplicationText.setText(aboutApplicationModel.get(0).getAlias().toString());

    }

    @Override
    public void getAbountAplicationContentError(String error) {
        Log.e("AbountAplicationError", error);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.about_application_activity;
    }
}
