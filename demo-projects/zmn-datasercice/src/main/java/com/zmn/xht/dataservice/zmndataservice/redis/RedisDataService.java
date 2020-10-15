package com.zmn.xht.dataservice.zmndataservice.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RedisDataService {

    @Autowired
    RedisService redisService;

    /**
     * 干净的单位组织结构树hset key  dwcode jsondata
     */
    private static  final String ZMN_DW_CLEAN_TREE="zmn:xht:clean:tree";

    /**
     * 轨迹里程统计数据的树hset key  dwcode jsondata
     */
    private static  final String ZMN_DW_TRACK_TREE="zmn:xht:lctj:tree";

    /**
     * 事件数量统计数据的树hset key  dwcode jsondata
     */
    private static  final String ZMN_DW_EVENT_TREE="zmn:xht:sjtj:tree";


    /**
     * 设置缓存所有单位的树结构
     * @param dwCode
     * @param jsonData
     */
    public void setDwTreeData(String dwCode,String jsonData){
        redisService.hset(ZMN_DW_CLEAN_TREE,dwCode,jsonData);
    }

    /**
     * 设置单位轨迹数据缓存
     * @param dwCode
     * @param dimension
     * @param jsonData
     */
    public void setDwTrackTreeData(String dwCode,String dimension,String jsonData){
        redisService.hset(ZMN_DW_TRACK_TREE+":"+dimension,dwCode,jsonData);
    }

    /**
     * 设置单位事件数据缓存
     * @param dwCode
     * @param dimension
     * @param jsonData
     */
    public void setDwEventTreeData(String dwCode,String dimension,String jsonData){
        redisService.hset(ZMN_DW_EVENT_TREE+":"+dimension,dwCode,jsonData);
    }

    /**
     * 删除单位机构树缓存
     * @param dwCode
     */
    public void removeDwTreeData(String dwCode){
        redisService.hdelete(ZMN_DW_CLEAN_TREE,dwCode);
    }

    /**
     * 删除单位轨迹数据缓存
     * @param dwCode
     * @param dimension
     */
    public void removeDwTrackTreeData(String dwCode,String dimension){
        redisService.hdelete(ZMN_DW_TRACK_TREE+":"+dimension,dwCode);
    }

    /**
     * 删除单位事件数据缓存
     * @param dwCode
     * @param dimension
     */
    public void removeDwEventTreeData(String dwCode,String dimension){
        redisService.hdelete(ZMN_DW_EVENT_TREE+":"+dimension,dwCode);
    }

}
