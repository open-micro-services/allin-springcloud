package com.boonya.sbsqlite.entity;

public class XhTrack {

    private long id;
    private String xh_data;
    private int   xh_distance;
    private String  xh_end_time;
    private String  xh_start_time;
    private int    xh_time_sum;
    private int    xh_is_commited;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXh_data() {
        return xh_data;
    }

    public void setXh_data(String xh_data) {
        this.xh_data = xh_data;
    }

    public int getXh_distance() {
        return xh_distance;
    }

    public void setXh_distance(int xh_distance) {
        this.xh_distance = xh_distance;
    }

    public String getXh_end_time() {
        return xh_end_time;
    }

    public void setXh_end_time(String xh_end_time) {
        this.xh_end_time = xh_end_time;
    }

    public String getXh_start_time() {
        return xh_start_time;
    }

    public void setXh_start_time(String xh_start_time) {
        this.xh_start_time = xh_start_time;
    }

    public int getXh_time_sum() {
        return xh_time_sum;
    }

    public void setXh_time_sum(int xh_time_sum) {
        this.xh_time_sum = xh_time_sum;
    }

    public int getXh_is_commited() {
        return xh_is_commited;
    }

    public void setXh_is_commited(int xh_is_commited) {
        this.xh_is_commited = xh_is_commited;
    }
}
