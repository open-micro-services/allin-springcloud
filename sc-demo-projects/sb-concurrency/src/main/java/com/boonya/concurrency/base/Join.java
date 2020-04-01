package com.boonya.concurrency.base;

/**
 * @ClassName: Join
 * @Description: TODO(功能说明 : Thread join方法将并发线程改为串行)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:59
 */
public class Join {

    /**
     * 函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                System.out.println("线程:" + finalI);
            });
            thread.start();
            try {
                thread.join();//调用join方法将并发线程改为串行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
