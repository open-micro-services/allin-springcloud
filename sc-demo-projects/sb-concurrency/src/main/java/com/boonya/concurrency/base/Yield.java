package com.boonya.concurrency.base;

/**
 * @ClassName: Yield
 * @Description: TODO(功能说明 : Thread.yield ()方法作用是：暂停当前正在执行的线程对象，并执行其他线程)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:59
 */
public class Yield {

    private static Object obj = new Object();

    /**
     * 函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    if (finalI / 2 == 0) {
                        Thread.yield();// 让出CPU时间片,并不表示它不会再执行,它会尝试获取下一个时间片执行任务
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程:" + finalI + "执行完成!");
            });

            thread.start();
        }

    }
}
