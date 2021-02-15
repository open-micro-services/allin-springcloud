package com.boonya.spark.controller;

import com.boonya.spark.threads.InputStreamReaderRunnable;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

/**
 * @Copyright: 2019-2021
 * @FileName: SparkLauncherController.java
 * @Author: PJL
 * @Date: 2020/9/1 17:59
 * @Description: Spark向Hadoop集群提交任务[https://blog.csdn.net/lyd882/article/details/103806085]
 */
@RestController
public class SparkLauncherController {

    /**
     * 利用官方提供的SparkLauncher java接口来使用java代码提交Spark任务到Spark集群
     *
     * @return
     */
    @GetMapping(value = "spark/kpi")
    public String submitTast(){
        HashMap env = new HashMap();
        //hadoop、spark环境变量读取
        env.put("HADOOP_CONF_DIR" ,  System.getenv().getOrDefault("HADOOP_CONF_DIR","/usr/local/hadoop/etc/overriterHaoopConf"));
        env.put("JAVA_HOME", System.getenv().getOrDefault("JAVA_HOME","/usr/local/java/jdk1.8.0_151"));
        //创建SparkLauncher启动器
        SparkLauncher handle = new SparkLauncher(env)
            .setSparkHome("/home/spark/spark-2.4.4-bin-hadoop2.7")
            .setAppResource("/home/sino/spark-model-1.0/lib/spark-model-1.0.jar")
            .setMainClass("com.sinovatio.spark.JobStarter")
            .setMaster("yarn")
            .setDeployMode("client")
            .setConf("spark.yarn.queue","sino")
            .setConf("spark.app.id", "luncher-test")
            .setConf("spark.driver.memory", "1g")
            .setConf("spark.executor.memory", "1g")
            .setConf("spark.executor.instances", "2")
            .setConf("spark.executor.cores", "2")
            .setConf("spark.default.parallelism", "12")
            .setConf("spark.driver.allowMultipleContexts","true")
            .setVerbose(true);

        try {
            //任务提交
            Process process = handle.launch();
            //日志跟踪子线程
            InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(process.getInputStream(), "input");
            Thread inputThread = new Thread(inputStreamReaderRunnable, "LogStreamReader input");
            inputThread.start();

            InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(process.getErrorStream(), "error");
            Thread errorThread = new Thread(errorStreamReaderRunnable, "LogStreamReader error");
            errorThread.start();

            System.out.println("Waiting for finish...");
            int exitCode = process.waitFor();
            System.out.println("Finished! Exit code:" + exitCode);
            return "status: "+exitCode;

        }catch (Exception e){
            e.printStackTrace();
            return "status: "+1;
        }

    }
}
