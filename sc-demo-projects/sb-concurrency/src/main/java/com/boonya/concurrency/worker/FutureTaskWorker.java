package com.boonya.concurrency.worker;

import com.boonya.concurrency.task.CallableTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: FutureTaskWorker
 * @Description: TODO(功能说明 ： FutureTask线程执行结果返回值获取)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:48
 */
public class FutureTaskWorker {
    /**
     * 函数入口
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> task1 = new FutureTask<Integer>(new CallableTask());
        FutureTask<Integer> task2 = new FutureTask<Integer>(new CallableTask());
        FutureTask<Integer> task3 = new FutureTask<Integer>(new CallableTask());
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        long start = System.currentTimeMillis();
        System.out.println("task1结果=" + task1.get());
        System.out.println("task2结果=" + task2.get());
        System.out.println("task3结果=" + task3.get());
        long end = System.currentTimeMillis();
        System.out.println("总共耗时=" + (end - start) + "ms");
    }
}
