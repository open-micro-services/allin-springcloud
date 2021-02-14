package com.boonya.ffmpeg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.*;

/**
 * 核心工具文件复制
 */
@Slf4j
@Configuration
public class LibFileCopy {

    // 加载 resources目录下的工具文件相对路径 如：/tool/ffmpeg
    @Value("${system.loadLibDir}")
    private String loadLibDir;

    // 将加载的文件放入的目录 如 /sbin/
    @Value("${system.outLibDir}")
    private String outLibDir;

    // 是否每次运行服务都覆盖老版本的外部工具，默认 false
    @Value("${system.loadLibFlag}")
    private Boolean loadLibFlag;

    @Bean
    public LibFileCopy FfdynsourceInit() throws IOException {
        copyFile(new String[]{loadLibDir}, outLibDir, loadLibFlag);
        log.info("=====================this lib file init ok!");
        return new LibFileCopy();
    }

    // 拷贝文件 flag
    public void copyFile(String[] fPaths, String outPath, Boolean flag) throws IOException {
        for (String path : fPaths) {

            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:" + path);
            if (resource.exists()) {
                InputStream is = resource.getInputStream();

                File pathF = new File(outPath);
                if (!pathF.exists()) {
                    pathF.mkdirs();
                }
                File f = new File(outPath, path.substring(path.lastIndexOf("/"), path.length()));
                log.info("create file path 文件路径：{}", f.getAbsolutePath());
                if (flag && f.exists()) {
                    f.delete();
                }

                if (!f.exists()) {
                    f.createNewFile();
                    f.setReadable(true);
                    f.setWritable(true);
                    f.setExecutable(true);
                    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f, false));
                    int lentgh = 0;
                    while ((lentgh = is.read()) != -1) {
                        os.write(lentgh);
                    }

                    is.close();
                    os.close();
                }
            }

        }

    }
}
