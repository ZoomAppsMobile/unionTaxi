package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 02.03.2018.
 */

public class YandexJson {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("zone_name")
    @Expose
    private String zoneName;
    @SerializedName("supports_forced_surge")
    @Expose
    private Boolean supportsForcedSurge;
    @SerializedName("parks")
    @Expose
    private List<Object> parks = null;
    @SerializedName("requirements")
    @Expose
    private Requirements requirements;
    @SerializedName("route")
    @Expose
    private List<List<Double>> route = null;
    @SerializedName("skip_estimated_waiting")
    @Expose
    private Boolean skipEstimatedWaiting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Boolean getSupportsForcedSurge() {
        return supportsForcedSurge;
    }

    public void setSupportsForcedSurge(Boolean supportsForcedSurge) {
        this.supportsForcedSurge = supportsForcedSurge;
    }

    public List<Object> getParks() {
        return parks;
    }

    public void setParks(List<Object> parks) {
        this.parks = parks;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public List<List<Double>> getRoute() {
        return route;
    }

    public void setRoute(List<List<Double>> route) {
        this.route = route;
    }

    public Boolean getSkipEstimatedWaiting() {
        return skipEstimatedWaiting;
    }

    public void setSkipEstimatedWaiting(Boolean skipEstimatedWaiting) {
        this.skipEstimatedWaiting = skipEstimatedWaiting;
    }
}
