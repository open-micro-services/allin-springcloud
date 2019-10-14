
package com.boonya.sbsqlite.entity;

public class XhTrackPoint  {

    public static final int POINT_DISCARD = 0; // 放弃：距离小，时间短，不上传，不记录
    public static final int POINT_RECORD = 1; // 记录本地不上传：超过最小记录距离
    public static final int POINT_UPLOAD = 2; // 记录本地且上传：超过最小上传距离或超过最长时间间隔

    // *****************************************属性*************************************//
    private long id;//ID 编号自增

    private int xh_point_id;// 巡护点id 实例：1、2、3、4（在一个巡护xh_data的xh_segment_id中累增）

    private String xh_data;// 巡护ID- 巡护日期（ID）-20150716

    private int xh_segment_id;// 巡护片段ID-外键 当前巡护点所属的巡护片段ID-1、2、3

    private double xh_point_lon;// 巡护点X坐标-116.249845

    private double xh_point_lat;// 巡护点Y坐标-39.906479

    private String xh_point_time;// 巡护点创建时间-2015-07-15_14:45:22

    private int xh_point_valid;// 1-保存不上传 2、保存并上传

    private long xh_point_gpstime;// gps实际时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getXh_point_gpstime() {
        return xh_point_gpstime;
    }

    public void setXh_point_gpstime(long xh_point_gpstime) {
        this.xh_point_gpstime = xh_point_gpstime;
    }

    public int getXh_point_valid() {
        return xh_point_valid;
    }

    public void setXh_point_valid(int xh_point_valid) {
        this.xh_point_valid = xh_point_valid;
    }

    public int getXh_point_id() {
        return xh_point_id;
    }

    public void setXh_point_id(int xh_point_id) {
        this.xh_point_id = xh_point_id;
    }

    public String getXh_data() {
        return xh_data;
    }

    public void setXh_data(String xh_data) {
        this.xh_data = xh_data;
    }

    public int getXh_segment_id() {
        return xh_segment_id;
    }

    public void setXh_segment_id(int xh_segment_id) {
        this.xh_segment_id = xh_segment_id;
    }

    public double getXh_point_lon() {
        return xh_point_lon;
    }

    public void setXh_point_lon(double xh_point_lon) {
        this.xh_point_lon = xh_point_lon;
    }

    public double getXh_point_lat() {
        return xh_point_lat;
    }

    public void setXh_point_lat(double xh_point_lat) {
        this.xh_point_lat = xh_point_lat;
    }

    public String getXh_point_time() {
        return xh_point_time;
    }

    public void setXh_point_time(String xh_point_time) {
        this.xh_point_time = xh_point_time;
    }

  
}
