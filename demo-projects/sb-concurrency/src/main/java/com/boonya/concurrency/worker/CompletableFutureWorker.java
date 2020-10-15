package com.boonya.concurrency.worker;

import com.boonya.concurrency.task.SupplierTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: CompletableFutureWorker
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:53
 */
public class CompletableFutureWorker {

    /**
     * 函数入口
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(new SupplierTask(), executor);
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(new SupplierTask(), executor);
        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(new SupplierTask(), executor);
        long start = System.currentTimeMillis();
        System.out.println("task1结果=" + task1.get());
        System.out.println("task2结果=" + task2.get());
        System.out.println("task3结果=" + task3.get());
        long end = System.currentTimeMillis();
        System.out.println("总共耗时=" + (end - start) + "ms");
    }

}
