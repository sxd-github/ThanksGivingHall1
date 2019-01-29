package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 基类
 * Created by sxd on 2018/2/8.
 */

public class BasicEntity<data>{
    private String statusCode;
    private String statusMessage;
    private String success;
    private List<data> data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<data> getData() {
        return data;
    }

    public void setData(List<data> data) {
        this.data = data;
    }
}
