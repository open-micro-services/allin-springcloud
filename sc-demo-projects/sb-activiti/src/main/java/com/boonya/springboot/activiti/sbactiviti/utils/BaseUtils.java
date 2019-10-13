package com.boonya.springboot.activiti.sbactiviti.utils;

import java.util.Map;

/**
 * @ClassName: BaseUtils
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019/10/13 22:39
 */
public class BaseUtils {

    public static boolean isNullOrEmpty(String path) {
        if(null==path||"".equals(path)||path.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(Object path) {
        if(null==path){
            return true;
        }
        return false;
    }

    public static Map<String, Object> object2ConditionMap(Object item) {
        return (Map<String, Object>)item;
    }
}
