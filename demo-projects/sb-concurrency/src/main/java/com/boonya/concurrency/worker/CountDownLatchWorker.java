package com.boonya.concurrency.worker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchWorker
 * @Description: TODO(功能说明 : 闭锁 ，)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:43
 * @link {@link https://www.cnblogs.com/liun1994/p/7396026.html}
 * @screen 场景:
 * <li>分布式计算进行汇总最终结果</li>
 * <li>主线程等待子线程执行完的操作</li>
 * <li>等待最终执行如初始化资源完成操作</li>
 * <li>等待最终执行如系统资源汇总计算</li>
 */
public class CountDownLatchWorker {

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
        Worker worker1 = new Worker("zhang san", 5000, latch);
        Worker worker2 = new Worker("li si", 8000, latch);
        worker1.start();//线程就绪
        worker2.start();//线程就绪
        latch.await();// 等待所有工人完成工作
        System.out.println("all work done at " + sdf.format(new Date()));
    }

    static class Worker extends Thread {
        String workerName;
        int workTime;
        CountDownLatch latch;

        public Worker(String workerName, int workTime, CountDownLatch latch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.latch = latch;
        }

        public void run() {
            System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));
            doWork();// 工作了
            System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
            latch.countDown();// 工人完成工作，计数器减一

        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
