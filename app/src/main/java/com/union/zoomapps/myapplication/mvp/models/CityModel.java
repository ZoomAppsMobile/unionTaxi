package com.union.zoomapps.myapplication.mvp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 01.03.2018.
 */
@Entity(tableName = "city")
public class CityModel {

    @PrimaryKey(autoGenerate = true)
    private int primary_id;

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("name_ru")
    @Expose
    @ColumnInfo(name = "name_ru")
    private String nameRu;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("lat")
    @Expose
    @ColumnInfo(name = "lat")
    private Double lat;
    @SerializedName("lon")
    @Expose
    @ColumnInfo(name = "lon")
    private Double lon;

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public int getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(int primary_id) {
        this.primary_id = primary_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
