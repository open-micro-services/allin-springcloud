package com.boonya.spark.examples;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * @Copyright: 2019-2021
 * @FileName: WordCount.java
 * @Author: PJL
 * @Date: 2020/9/1 16:39
 * @Description: 文字计算统计
 */
public class WordCount {

    /**
     * 函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        String logFile = "YOUR_SPARK_HOME/README.md"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();
        // 聚合计算
        long numAs = logData.filter("a").count();
        long numBs = logData.filter("b").count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        // 停止作业
        spark.stop();
    }
}