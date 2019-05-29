package com.dataservice.websocket.dpwebsocket.util;

import com.dataservice.websocket.dpwebsocket.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.io.IOException;

/**
 * 简单JSON工具类
 */
public class JSONUtil {

   private static  ObjectMapper mapper = new JacksonConfig().jacksonObjectMapper(Jackson2ObjectMapperBuilder.json());

    /**
     * 获取对象的json字符串
     *
     * @param obj
     * @return
     */
    public static String getJsonFromObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    /**
     * 获取泛型对象
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getObjectFromJson(String jsonStr, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonStr,clazz);
    }
}
