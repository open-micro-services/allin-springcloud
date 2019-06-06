package com.zmn.xht.dataservice.zmndataservice.service;

import java.util.Date;
/**
 * 数据服务层
 */
public interface DataService {
    /**
     * 根据单位编号获取单位内设机构人员树
     * @param dwCode
     * @return
     */
    String getNSJGTreeByDwCode(String dwCode);

    /**
     * 根据单位编号获取内设机构
     * @param dwCode
     * @return
     */
    String getNSJGByDwCode(String dwCode);

    /**
     * 根据单位编号获护林员
     * @param dwCode
     * @return
     */
    String getHlyByDwCode(String dwCode);

    /**
     * 根据单位编码和时间获取护林员轨迹统计数据
     * @param dwCode
     * @param start
     * @param end
     * @return
     */
    String getHlyTrackFactorsByDwCode(String dwCode, Date start,Date end);

    /**
     * 根据单位编码和时间获取护林员事件统计数据
     * @param dwCode
     * @param start
     * @param end
     * @return
     */
    String getHlyEventFactorsByDwCode(String dwCode, Date start,Date end);


    /**
     * 保存单位轨迹里程统计结果
     * @param type
     * @param year
     * @param month
     * @param week
     * @param day
     * @param dwCode
     * @param jsonData
     * @return
     */
    boolean saveDwTrackFactorsTree(int type,int year,int month,int week,Date day,String dwCode,String jsonData);

    /**
     * 保存单位事件统计结果
     * @param type
     * @param year
     * @param month
     * @param week
     * @param day
     * @param dwCode
     * @param jsonData
     * @return
     */
    boolean saveDwEventFactorsTree(int type,int year,int month,int week,Date day,String dwCode,String jsonData);

    /**
     * 更新单位轨迹里程统计结果
     * @param objectId
     * @param jsonData
     * @return
     */
    boolean updateDwTrackFactorsTree(String objectId, String jsonData);

    /**
     *更新单位事件数量统计结果
     * @param objectId
     * @param jsonData
     * @return
     */
    boolean updateDwEventFactorsTree(String objectId, String jsonData);

    /**
     * 获取轨迹统计结果树
     * @param type
     * @param year
     * @param month
     * @param week
     * @param day
     * @param dwCode
     * @return
     */
    String getDwTrackFactorsTree(int type,int year,int month,int week,Date day,String dwCode);

    /**
     * 获取事件数量统计结果树
     * @param type
     * @param year
     * @param month
     * @param week
     * @param day
     * @param dwCode
     * @return
     */
    String getDwEventFactorsTree(int type,int year,int month,int week,Date day,String dwCode);
}
