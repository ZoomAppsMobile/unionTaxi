package com.union.zoomapps.myapplication.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 19.03.2018.
 */

public class SucseesCareem {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("operationMessage")
    @Expose
    private String operationMessage;
    @SerializedName("errorCode")
    @Expose
    private Object errorCode;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("trace")
    @Expose
    private Object trace;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getOperationMessage() {
        return operationMessage;
    }

    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getTrace() {
        return trace;
    }

    public void setTrace(Object trace) {
        this.trace = trace;
    }
}
