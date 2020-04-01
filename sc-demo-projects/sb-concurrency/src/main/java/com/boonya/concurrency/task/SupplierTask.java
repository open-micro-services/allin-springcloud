package com.boonya.concurrency.task;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @ClassName: SupplierTask
 * @Description: TODO(功能说明 ： 可回调的Supplier顺序处理线程任务)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:41
 */
public class SupplierTask implements Supplier<Integer> {

    @Override
    public Integer get() {
        int count = new Random().nextInt(10000);
        try {
            Thread.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---" + count);
        return count;
    }

}
