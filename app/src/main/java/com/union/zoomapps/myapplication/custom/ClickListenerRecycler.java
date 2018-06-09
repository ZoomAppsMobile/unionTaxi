package com.union.zoomapps.myapplication.custom;

import android.view.View;

/**
 * Created by Asus on 01.03.2018.
 */

public interface ClickListenerRecycler {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
