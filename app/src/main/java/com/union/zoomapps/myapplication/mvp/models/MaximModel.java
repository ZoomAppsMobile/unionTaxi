package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 13.03.2018.
 */

public class MaximModel {
    @SerializedName("ButtonText")
    @Expose
    private Object buttonText;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("PaymentMethods")
    @Expose
    private Object paymentMethods;
    @SerializedName("Prices")
    @Expose
    private Object prices;
    @SerializedName("ShowFrom")
    @Expose
    private Boolean showFrom;
    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("TypeId")
    @Expose
    private Integer typeId;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("FeedTime")
    @Expose
    private Integer feedTime;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("PriceString")
    @Expose
    private String priceString;

    public Object getButtonText() {
        return buttonText;
    }

    public void setButtonText(Object buttonText) {
        this.buttonText = buttonText;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Object paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Object getPrices() {
        return prices;
    }

    public void setPrices(Object prices) {
        this.prices = prices;
    }

    public Boolean getShowFrom() {
        return showFrom;
    }

    public void setShowFrom(Boolean showFrom) {
        this.showFrom = showFrom;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(Integer feedTime) {
        this.feedTime = feedTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

}
