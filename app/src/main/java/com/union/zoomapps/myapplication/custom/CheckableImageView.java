package com.union.zoomapps.myapplication.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;


/**
 * Created by Asus on 01.03.2018.
 */

public class CheckableImageView extends android.support.v7.widget.AppCompatImageView implements Checkable {
    private boolean mChecked;
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };
    public CheckableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
    public void toggle() {
        setChecked(mChecked);
    }
    public boolean isChecked() {
        return mChecked;
    }
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }
}
