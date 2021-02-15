package com.boonya.spark.controller;

import com.boonya.spark.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * @Copyright: 2019-2021
 * @FileName: SparkController.java
 * @Author: PJL
 * @Date: 2020/9/1 17:11
 * @Description: Spark控制器
 */
@RestController
public class SparkController {

    @Autowired
    private SparkService sparkService;

    @RequestMapping("/spark/top10")
    public Map<String, Object> calculateTopTen() {
        return sparkService.calculateTopTen();
    }

    @RequestMapping("/spark/exercise")
    public void exercise() {
        sparkService.sparkExerciseDemo();
    }

    @RequestMapping("/spark/stream")
    public void streamingDemo() throws InterruptedException {
        sparkService.sparkStreaming();
    }
}
