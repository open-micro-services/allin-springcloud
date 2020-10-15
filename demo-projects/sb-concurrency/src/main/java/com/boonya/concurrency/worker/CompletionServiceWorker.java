package com.boonya.concurrency.worker;

import com.boonya.concurrency.task.CallableTask;

import java.util.concurrent.*;

/**
 * @ClassName: CompletionServiceWorker
 * @Description: TODO(功能说明 ： CompletionService线程完成顺序干预先后排序)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:51
 */
public class CompletionServiceWorker {

    /**
     * 函数入口
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        Future<Integer> task1 = completionService.submit(new CallableTask());
        Future<Integer> task2 = completionService.submit(new CallableTask());
        Future<Integer> task3 = completionService.submit(new CallableTask());
        long start = System.currentTimeMillis();
        System.out.println("task1结果=" + task1.get());
        System.out.println("task2结果=" + task2.get());
        System.out.println("task3结果=" + task3.get());
        long end = System.currentTimeMillis();
        System.out.println("总共耗时=" + (end - start) + "ms");
    }

}
