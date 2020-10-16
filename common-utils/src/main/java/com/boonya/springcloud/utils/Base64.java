package com.boonya.springcloud.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Copyright: 2019-2021
 * @FileName: Base64.java
 * @Author: PJL
 * @Date: 2020/5/19 15:47
 * @Description: Base64工具
 */
@Slf4j
public class Base64 {

    /**
     * JDK BASE64 编码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String BASE64Encoder(String str) {
        try {
            String base64str = new BASE64Encoder().encode(str.getBytes("utf-8"));
            return base64str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK BASE64 解码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String BASE64Decoder(String str) {
        try {
            byte[] decodeBytes = new BASE64Decoder().decodeBuffer(new String(str.getBytes("utf-8")));
            return new String(decodeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 工具测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "CHINA";
        str = Base64.BASE64Encoder(str);
        log.info("编码:%s", str);

        str = Base64.BASE64Decoder(str);
        log.info("解码:%s", str);

    }
}
