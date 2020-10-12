package com.boonya.concurrency.task;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @ClassName: CallableTask
 * @Description: TODO(功能说明 : 有返回值的线程任务)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:40
 */
public class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int count = new Random().nextInt(10000);
        Thread.sleep(count);
        System.out.println("---" + count);
        return count;
    }

}
