package com.boonya.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: RecursiveTaskExample
 * @Description: TODO(功能说明：大任务拆分小任务并行执行【Fork/Join是一种基于“分治”的算法：通过分解任务，并行执行，最后合并结果得到最终结果】)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/7 22:21
 * @note ForkJoinPool线程池可以把一个大任务分拆成小任务并行执行，任务类必须继承自RecursiveTask（有返回值）或RecursiveAction（无返回值）。Fork/Join模式可以进行并行计算以提高效率。
 * @see "https://www.cnblogs.com/linlinismine/p/9295701.html"
 */
public class RecursiveTaskExample extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2; //阀值
    private int start;
    private int end;

    public RecursiveTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            RecursiveTaskExample leftTask = new RecursiveTaskExample(start, middle);
            RecursiveTaskExample rightTask = new RecursiveTaskExample(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完，并得到其结果
            Integer rightResult = rightTask.join();
            Integer leftResult = leftTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveTaskExample recursiveTaskExample = new RecursiveTaskExample(1, 200);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(recursiveTaskExample);
        System.out.println(forkJoinTask.get());
    }
}
