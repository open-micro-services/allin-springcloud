package com.boonya.layui.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName: JsonFileUtil
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/9 23:18
 */
public class JsonFileUtil {

    private static final String JSON_CONTENT="{\"aggregationLayers\": [  ]}";

    /**
     * 如果不存在就创建
     * @throws IOException
     */
    public static void createJsonFile()throws IOException{
        ClassPathResource classPathResource = new ClassPathResource("aggregationService.json");
        if(classPathResource.exists()){
            classPathResource.createRelative("aggregationService.json");
            if(classPathResource.getFile().canWrite()){
                File file=classPathResource.getFile();
                FileWriter writer = new FileWriter(file.getName(),true);
                writer.write(JSON_CONTENT);
                writer.close();
            }
        }
    }

    /**
     * 获取JSON文件内容
     * @return
     * @throws IOException
     */
    public static String getFileJson() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("aggregationService.json");
        if(classPathResource.exists()){
            byte[]  bytes= FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            return new String(bytes);
        }else{
            return JSON_CONTENT;
        }
    }

    /**
     * 解析JSON文件内容
     * @return
     * @throws IOException
     */
    public static String parseFileJson() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("aggregationService.json");
        byte[]  bytes= FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        return new String(bytes);
    }
}
