package com.boonya.spark.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @Copyright: 2019-2021
 * @FileName: SparkConfiguration.java
 * @Author: PJL
 * @Date: 2020/9/1 16:56
 * @Description: Spark配置
 */
@Getter
@Setter
@Configuration
public class SparkConfiguration {

    @Value("${spark.app.name}")
    private String appName;
    @Value("${spark.home}")
    private String sparkHome;
    @Value("${spark.master.uri}")
    private String sparkMasterUri;
    @Value("${spark.driver.memory}")
    private String sparkDriverMemory;
    @Value("${spark.worker.memory}")
    private String sparkWorkerMemory;
    @Value("${spark.executor.memory}")
    private String sparkExecutorMemory;
    @Value("${spark.executor.cores}")
    private String sparkExecutorCores;
    @Value("${spark.num.executors}")
    private String sparkExecutorsNum;
    @Value("${spark.network.timeout}")
    private String networkTimeout;
    @Value("${spark.executor.heartbeatInterval}")
    private String heartbeatIntervalTime;
    @Value("${spark.driver.maxResultSize}")
    private String maxResultSize;
    @Value("${spark.rpc.message.maxSize}")
    private String sparkRpcMessageMaxSize;
    @Value("${spark.storage.blockManagerSlaveTimeoutMs}")
    private String blockManagerSlaveTimeoutMs;
    @Value("${spark.jars}")
    private String jars;

    @Bean
    @ConditionalOnMissingBean(SparkConf.class)
    public SparkConf sparkConf()  {
        SparkConf sparkConf = new SparkConf()
            .setAppName(appName)
            .setMaster(sparkMasterUri)
            .setJars(new String[]{jars})
            .set("spark.driver.memory",sparkDriverMemory)
            .set("spark.driver.maxResultSize",maxResultSize)
            .set("spark.worker.memory",sparkWorkerMemory)
            .set("spark.executor.memory",sparkExecutorMemory)
            .set("spark.executor.cores",sparkExecutorCores)
            .set("spark.executor.heartbeatInterval",heartbeatIntervalTime)
            .set("spark.num.executors",sparkExecutorsNum)
            .set("spark.network.timeout",networkTimeout)
            .set("spark.rpc.message.maxSize",sparkRpcMessageMaxSize)
            .set("spark.storage.blockManagerSlaveTimeoutMs",blockManagerSlaveTimeoutMs);
        return sparkConf;
    }

    @Bean
    @ConditionalOnMissingBean(JavaSparkContext.class)
    public JavaSparkContext javaSparkContext()  {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession(){
        return SparkSession
            .builder()
            .sparkContext(javaSparkContext().sc())
            .appName(appName)
            .getOrCreate();
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
