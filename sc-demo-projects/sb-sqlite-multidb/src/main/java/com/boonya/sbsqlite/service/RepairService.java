package com.boonya.sbsqlite.service;

import java.util.List;
import java.util.Map;

public interface RepairService {

    /*------------------------------用户管理---------------------*/
    public Map<String,Object> selectUser(String dbUrl, String number);
    public List<Map<String,Object>> selectUserAll(String dbUrl) ;
    /*------------------------------事件管理---------------------*/
    public List<Map<String,Object>> selectBusinessAll(String dbUrl) ;
    public List<Map<String,Object>> matchBusinessUniqueRecords(String dbUrl, String hlyId);
    public boolean updateBusinessForNewHlyId(String dbUrl, String hlyId);
    /*------------------------------通知公告---------------------*/
    public List<Map<String,Object>> selectNoticeAll(String dbUrl) ;
    public List<Map<String,Object>> matchNoticeUniqueRecords(String dbUrl, String hlyId);
    public boolean updateNoticeForNewHlyId(String dbUrl, String hlyId);
    /*------------------------------轨迹点数据---------------------*/
    public List<Map<String,Object>> selectTrackAll(String dbUrl) ;
    public List<Map<String,Object>> matchTrackUniqueRecords(String dbUrl, String hlyId);
    public boolean updateTrackForNewHlyId(String dbUrl, String hlyId);
    /*------------------------------工作日志---------------------*/
    public List<Map<String,Object>> selectWorkLogAll(String dbUrl) ;
    public List<Map<String,Object>> matchWorkLogUniqueRecords(String dbUrl, String hlyId);
    public boolean updateWorkLogForNewHlyId(String dbUrl, String hlyId);
    /*------------------------------修复处理---------------------*/
    public boolean repairServiceDataForNumber(String dbUrl, String number);
    public Map<String,Object> repairServiceDataForAll(List<String> connections);

}