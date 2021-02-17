package com.boonya.spark.service;

import com.boonya.spark.threads.CustomStreamingReceiver;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.sparkproject.guava.base.Joiner;
import org.sparkproject.guava.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Copyright: 2019-2021
 * @FileName: SparkService.java
 * @Author: PJL
 * @Date: 2020/9/1 17:02
 * @Description: Spark服务
 */
@Slf4j
@Service
public class SparkService {

    private static final Pattern SPACE = Pattern.compile(" ");

    @Autowired
    private JavaSparkContext javaSparkContext;


    public Map<String, Object> calculateTopTen() {

        Map<String, Object> result = new HashMap<>();
        JavaRDD<String> lines = javaSparkContext.textFile("src/test/java/test.txt").cache();

        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println(lines.count());

        JavaRDD<String> words = lines.flatMap(str -> Arrays.asList(SPACE.split(str)).iterator());

        JavaPairRDD<String, Integer> ones = words.mapToPair(str -> new Tuple2<String, Integer>(str, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((Integer i1, Integer i2) -> (i1 + i2));

        JavaPairRDD<Integer, String> temp = counts.mapToPair(tuple -> new Tuple2<Integer, String>(tuple._2, tuple._1));

        JavaPairRDD<String, Integer> sorted = temp.sortByKey(false).mapToPair(tuple -> new Tuple2<String, Integer>(tuple._2, tuple._1));

        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println(sorted.count());

        //List<Tuple2<String, Integer>> output = sorted.collect();

        //List<Tuple2<String, Integer>> output = sorted.take(10);

        List<Tuple2<String, Integer>> output = sorted.top(10);

        for (Tuple2<String, Integer> tuple : output) {
            result.put(tuple._1(), tuple._2());
        }

        return result;
    }

    /**
     * 练习demo，熟悉其中API
     */
    public void sparkExerciseDemo() {
        List<Integer> data = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        JavaRDD<Integer> rdd01 = javaSparkContext.parallelize(data);
        rdd01 = rdd01.map(num -> {
            return num * num;
        });
        //data map :1,4,9,16,25,36
        log.info("data map :{}", Joiner.on(",").skipNulls().join(rdd01.collect()).toString());

        rdd01 = rdd01.filter(x -> x < 6);

        //data filter :1,4
        log.info("data filter :{}", Joiner.on(",").skipNulls().join(rdd01.collect()).toString());

        rdd01 = rdd01.flatMap(x -> {
            Integer[] test = {x, x + 1, x + 2};
            return Arrays.asList(test).iterator();
        });

        //flatMap :1,2,3,4,5,6
        log.info("flatMap :{}", Joiner.on(",").skipNulls().join(rdd01.collect()).toString());

        JavaRDD<Integer> unionRdd = javaSparkContext.parallelize(data);

        rdd01 = rdd01.union(unionRdd);

        //union :1,2,3,4,5,6,1,2,3,4,5,6
        log.info("union :{}", Joiner.on(",").skipNulls().join(rdd01.collect()).toString());

        List<Integer> result = Lists.newArrayList();
        result.add(rdd01.reduce((Integer v1, Integer v2) -> {
            return v1 + v2;
        }));

        //reduce :42
        log.info("reduce :{}", Joiner.on(",").skipNulls().join(result).toString());
        result.forEach(System.out::print);

        JavaPairRDD<Integer, Iterable<Integer>> groupRdd = rdd01.groupBy(x -> {
            log.info("======grouby========：{}", x);
            if (x > 10) return 0;
            else return 1;
        });

        List<Tuple2<Integer, Iterable<Integer>>> resultGroup = groupRdd.collect();

        //group by  key:1 value:1,2,3,4,5,6,1,2,3,4,5,6
        resultGroup.forEach(x -> {
            log.info("group by  key:{} value:{}", x._1, Joiner.on(",").skipNulls().join(x._2).toString());
        });

    }

    /**
     * spark streaming 练习
     */
    public void sparkStreaming() throws InterruptedException {
        JavaStreamingContext jsc = new JavaStreamingContext(javaSparkContext, Durations.seconds(10));//批间隔时间
        JavaReceiverInputDStream<String> lines = jsc.receiverStream(new CustomStreamingReceiver(StorageLevel.MEMORY_AND_DISK_2()));
        JavaDStream<Long> count = lines.count();
        count = count.map(x -> {
            log.info("这次批一共多少条数据：{}", x);
            return x;
        });
        count.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.stop();
    }
}
