package com.example.sxd.thanksgivinghall.bean;

/**
 * Created by sxd on 2018/3/16.
 */

public class UpLoadFile {
    private String errcode;//请求成功0
    private String picname;//上传文件名
    private String path;//文件url

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
