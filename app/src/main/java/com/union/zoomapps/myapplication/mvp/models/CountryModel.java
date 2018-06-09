package com.union.zoomapps.myapplication.mvp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 01.03.2018.
 */
@Entity(tableName = "country")
public class CountryModel {
    @PrimaryKey(autoGenerate = true)
    private int primary_id;
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private Integer id;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("deleted_at")
    @Expose
    @ColumnInfo(name = "deletedAt")
    private String deletedAt;
    @SerializedName("created_at")
    @Expose
    @ColumnInfo(name = "createdAt")
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    @ColumnInfo(name = "updatedAt")
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(int primary_id) {
        this.primary_id = primary_id;
    }


}
