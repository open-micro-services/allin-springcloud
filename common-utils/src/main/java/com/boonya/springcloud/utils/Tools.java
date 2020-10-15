package com.boonya.springcloud.utils;

import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: Tools
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-14
 */
public class Tools {

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim()) || "null".equals(s);
    }

    public static boolean isEmpty(Collection<?> c) {
        return (c == null || c.size() == 0) ? true : false;
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将JSON字符串反序列化为java对象
     *
     * @throws Exception
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为List<Object>对象
     *
     * @param jsonStr
     * @return
     */
    public static List<Object> jsonToList(String jsonStr) {
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        Object[] obj = jsonArray.toArray();
        List<Object> list = Arrays.asList(obj);
        return list;
    }

    /**
     * 将JSON字符串反序列化为List<T>对象
     *
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

    /**
     * 将对象序列化成json
     */
    public static String toJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
