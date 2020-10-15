
package com.boonya.sbsqlite.entity;


public class XhWorkLog {
    private long id;//ID 编号自增

    private String xh_work_log_data;//

    private String xh_work_log_content;//

    private String xh_work_log_send_time;// 上报时间

    private String xh_work_log_type;// 日志类型

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXh_work_log_data() {
        return xh_work_log_data;
    }

    public void setXh_work_log_data(String xh_work_log_data) {
        this.xh_work_log_data = xh_work_log_data;
    }

    public String getXh_work_log_content() {
        return xh_work_log_content;
    }

    public void setXh_work_log_content(String xh_work_log_content) {
        this.xh_work_log_content = xh_work_log_content;
    }

    public String getXh_work_log_send_time() {
        return xh_work_log_send_time;
    }

    public void setXh_work_log_send_time(String xh_work_log_send_time) {
        this.xh_work_log_send_time = xh_work_log_send_time;
    }

    public String getXh_work_log_type() {
        return xh_work_log_type;
    }

    public void setXh_work_log_type(String xh_work_log_type) {
        this.xh_work_log_type = xh_work_log_type;
    }


}
