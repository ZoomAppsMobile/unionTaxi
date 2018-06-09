package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 02.03.2018.
 */

public class ModelYandex {
    @SerializedName("calc")
    @Expose
    private List<Object> calc = null;
    @SerializedName("center_tab")
    @Expose
    private Boolean centerTab;
    @SerializedName("notify")
    @Expose
    private Object notify;
    @SerializedName("service_levels")
    @Expose
    private List<ServiceLevel> serviceLevels = null;

    public List<Object> getCalc() {
        return calc;
    }

    public void setCalc(List<Object> calc) {
        this.calc = calc;
    }

    public Boolean getCenterTab() {
        return centerTab;
    }

    public void setCenterTab(Boolean centerTab) {
        this.centerTab = centerTab;
    }

    public Object getNotify() {
        return notify;
    }

    public void setNotify(Object notify) {
        this.notify = notify;
    }

    public List<ServiceLevel> getServiceLevels() {
        return serviceLevels;
    }

    public void setServiceLevels(List<ServiceLevel> serviceLevels) {
        this.serviceLevels = serviceLevels;
    }
}
