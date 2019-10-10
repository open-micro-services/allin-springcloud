package com.boonya.springcloud.messages.rocketmq.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtil2 {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转为json
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(InputStream stream, Class<T> clazz) {
        try {
            return MAPPER.readValue(stream, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
