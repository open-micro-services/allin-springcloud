package com.boonya.springboot.websocket.sbwebsocket.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 简单JSON工具类
 */
public class JSONUtil {

    /**
     * 获取对象的json字符串
     *
     * @param obj
     * @return
     */
    public static String getJsonFromObject(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
