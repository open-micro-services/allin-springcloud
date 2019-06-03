package com.boonya.springboot.redis.sbredis.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    public static String getConfig(String key) {
        String config = new String();
        //该方法读取文件会有缓存问题,开发时建议别使用，部署的时候再使用
        InputStream is = Utils.class.getResourceAsStream("/redis.properties");
//		String path = Utils.class.getResource("/ywpt.properties").getPath();
//		FileInputStream is;
        Properties p = new Properties();
        try {
//			is = new FileInputStream(new File(path));
            p.load(new InputStreamReader(is,"UTF-8"));
            config = p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取redis.properties文件时出错！");
        }

        return config;
    }
}
