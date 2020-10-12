package com.boonya.concurrency.worker;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierWorker
 * @Description: TODO(功能说明 ： CyclicBarrier允许线程之间互相等待)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:46
 */
public class CyclicBarrierWorker {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 循环执行A直到完成
                Thread.sleep(1000);
                System.out.println(getName() + " 执行任务A");
                barrier.await();
                System.out.println(getName() + " 执行任务 A完成");

                // 循环执行B直到完成
                Thread.sleep(2000);
                System.out.println(getName() + " 执行任务B");
                barrier.await();
                System.out.println(getName() + " 执行任务 B完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

            @Override
            public void run() {
                // 回调处理
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
