package com.geren.caozhi.myapplication.bean;

/**
 * Created by neil on 16/9/12.
 */
public class UpdateBean {

    //最新版本号
    private String versionCode;
    //消息
    private String message;
    //状态
    private int status;
    //下载地址
    private String downloadUrl;





    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
