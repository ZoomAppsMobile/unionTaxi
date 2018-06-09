package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 02.03.2018.
 */

public class ServiceLevel {
    @SerializedName("cars")
    @Expose
    private List<String> cars = null;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_parts")
    @Expose
    private DescriptionParts descriptionParts;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("details_tariff")
    @Expose
    private List<DetailsTariff> detailsTariff = null;
    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("service_level")
    @Expose
    private Integer serviceLevel;

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DescriptionParts getDescriptionParts() {
        return descriptionParts;
    }

    public void setDescriptionParts(DescriptionParts descriptionParts) {
        this.descriptionParts = descriptionParts;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public List<DetailsTariff> getDetailsTariff() {
        return detailsTariff;
    }

    public void setDetailsTariff(List<DetailsTariff> detailsTariff) {
        this.detailsTariff = detailsTariff;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}
