package com.boonya.sbsqlite.entity;

import java.util.Date;

public class XhBusiness {

    private int id ;//自增ID
    private String num;
    private String json;
    private String key;
    private int uploadStatus ;
    private Double gpsx;
    private Double gpsy;
    private Date updateDate;
    private Date insertDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Double getGpsx() {
        return gpsx;
    }

    public void setGpsx(Double gpsx) {
        this.gpsx = gpsx;
    }

    public Double getGpsy() {
        return gpsy;
    }

    public void setGpsy(Double gpsy) {
        this.gpsy = gpsy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
