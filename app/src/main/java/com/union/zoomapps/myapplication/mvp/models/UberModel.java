package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 02.03.2018.
 */

public class UberModel {

    @SerializedName("prices")
    @Expose
    private List<UberPrices> prices = null;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UberPrices> getPrices() {
        return prices;
    }

    public void setPrices(List<UberPrices> prices) {
        this.prices = prices;
    }
}
