package com.boonya.sbsqlite.service;

import java.util.List;
import java.util.Map;

public interface XhHlyService {

    public Map<String,Object> select(long id);

    /**
     * 查询护林员主表记录
     * @param number
     * @return
     */
    public Map<String,Object> select(String number) ;

    /**
     * 查询护林员子表记录
     * @param number
     * @param dwCode
     * @return
     */
    public Map<String,Object> select(String number, String dwCode);

    /**
     * 查询所有重复护林员数据（需要处理的数据原始表）
     * @return
     */
    public List< Map<String,Object>> selectAll() ;

    /**
     * 查询所有重复护林员数据（需要处理的数据中间表）
     * @return
     */
    public List<Map<String,Object>> selectRepairList();

    /**
     * 是否手机号已处理
     * @return
     */
    public boolean hasHandledNumber(String number);

    /**
     * 更新序列自增表记录
     * @return
     */
    public boolean updateSequenceHlyId(String newHlyId) throws Exception;

    /**
     * 更新映射表记录
     * @return
     */
    public boolean updateRepairHlyId(String number, String newHlyId) throws Exception;

    /**
     * 更新护林员主表记录
     * @return
     */
    public boolean updateHlyZbId(String number, String newHlyId) throws Exception;

    /**
     * 更新护林员分表记录
     * @return
     */
    public boolean updateHlyFbId(String number, String dwCode, String newHlyId) throws Exception;

    /**
     * 记录已处理的手机号防止二次处理
     * @return
     */
    public boolean saveHandledNumber(String number) throws Exception;


    /**
     * 修复护林员主表重复ID--主表中重复的记录
     * @return
     */
    public  Map<String,Object> repairZbHlyIds();

    /**
     * 修复护林员分表重复ID--在主表中不存在的记录
     * @return
     */
    public  Map<String,Object> repairFbHlyIds();

    /**
     * 查询是否修复业务数据状态
     * @param number
     * @return
     */
    public boolean hasRepairedNumber(String number);

    /**
     * 更新否修复业务数据状态
     * @param number
     * @return
     */
    public boolean updateRepairedStatus(String number);

}