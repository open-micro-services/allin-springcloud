package com.boonya.concurrency.base;

/**
 * @ClassName: WaitNotifyAll
 * @Description: TODO(功能说明 ： 对象锁的等待和通知)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:59
 */
public class WaitNotifyAll {

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
                if (finalI / 2 == 0) {
                    try {
                        // 必须用synchronized关键字处理锁定对象
                        synchronized (obj) {
                            System.out.println("线程:" + finalI + " wait....");
                            obj.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 必须用synchronized关键字处理锁定对象
                    synchronized (obj) {
                        obj.notifyAll();
                        System.out.println("线程:" + finalI + " notifyAll...");
                    }
                }
                System.out.println("线程:" + finalI + "执行完成!");
            });
            thread.start();
        }

    }
}
