package com.boonya.springboot.watcher.file;

import com.sun.nio.file.SensitivityWatchEventModifier;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static com.sun.jmx.mbeanserver.Util.cast;

/**
 *文件监控修改 类似看门狗
 * @see https://blog.csdn.net/chinabestchina/article/details/101173766
 */
@Component
public class FileWatcher implements InitializingBean {

    /**
     * 监听文件
     *
     * @param target_file
     * @throws Exception
     */
    private void watch( String baseDir,String target_file) throws Exception {
        //构造监听服务
        WatchService watcher = FileSystems.getDefault().newWatchService();
        //监听注册，监听实体的创建、修改、删除事件，并以高频率(每隔2秒一次，默认是10秒)监听
        Paths.get(baseDir).register(watcher,
            new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE},
            SensitivityWatchEventModifier.HIGH);
        new Thread(() -> {
            try {
                while (true) {
                    //每隔3秒拉取监听key
                    WatchKey key = watcher.poll(3, TimeUnit.SECONDS);  //等待，超时就返回
                    //监听key为null,则跳过
                    if (key == null) {
                        continue;
                    }
                    //获取监听事件
                    for (WatchEvent<?> event : key.pollEvents()) {
                        //获取监听事件类型
                        WatchEvent.Kind kind = event.kind();
                        //异常事件跳过
                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }
                        //获取监听Path
                        Path path = cast(event.context());
                        //只关注目标文件
                        if (!target_file.equals(path.toString())) {
                            continue;
                        }
                        //文件删除
                        if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                            System.out.printf("file delete, type:%s  path:%s \n", kind.name(), path);
                            continue;
                        }
                        //构造完整路径
                        Path fullPath = Paths.get(baseDir, path.toString());
                        //获取文件
//                File f = fullPath.toFile();
                        //读取文件内容
                        String content = new String(Files.readAllBytes(fullPath), Charsets.UTF_8);
                        //按行读取文件内容
//                List<String> lineList = Files.readAllLines(fullPath);
                        //输出事件类型、文件路径及内容
                        System.out.printf("type:%s  path:%s  content:%s\n", kind.name(), path, content);
                    }
                    //处理监听key后(即处理监听事件后)，监听key需要复位，便于下次监听
                    key.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String basePath = "C:\\DEVELOPERS\\spring-learning\\demo-projects\\sb-file-watcher\\src\\main\\resources\\config";
        watch(basePath,"test.txt");
    }
}
