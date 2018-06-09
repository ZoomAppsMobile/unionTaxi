package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 12.03.2018.
 */

public class LyftModel {
    @SerializedName("cost_estimates")
    @Expose
    private List<CostEstimateLyft> costEstimates = null;

    public List<CostEstimateLyft> getCostEstimates() {
        return costEstimates;
    }

    public void setCostEstimates(List<CostEstimateLyft> costEstimates) {
        this.costEstimates = costEstimates;
    }
}
