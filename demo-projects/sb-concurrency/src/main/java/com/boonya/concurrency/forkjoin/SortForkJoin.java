package com.boonya.concurrency.forkjoin;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: SortForkJoin
 * @Description: TODO(功能说明 ： ForkJoin排序【分而治之】)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/7 22:58
 */
public class SortForkJoin {

    public static int[] createIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0, j = array.length; i < j; i++) {
            array[i] = new Random().nextInt(1000);
        }
        return array;
    }

    /**
     * 数组排序
     *
     * @param arry
     * @return
     */
    public static int[] sort(int[] arry) {
        if (arry.length == 0) return arry;
        for (int index = 0; index < arry.length - 1; index++) {
            int pre_index = index;
            int currentValue = arry[index + 1];
            while (pre_index >= 0 && arry[pre_index] > currentValue) {
                arry[pre_index + 1] = arry[pre_index];
                pre_index--;
            }
            arry[pre_index + 1] = currentValue;
        }
        return arry;
    }

    /**
     * 组合
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int resultIndex = 0, leftIndex = 0, rightIndex = 0; resultIndex < result.length; resultIndex++) {
            if (leftIndex >= left.length) {
                result[resultIndex] = right[rightIndex++];
            } else if (rightIndex >= right.length) {
                result[resultIndex] = left[leftIndex++];
            } else if (left[leftIndex] > right[rightIndex]) {
                result[resultIndex] = right[rightIndex++];
            } else {
                result[resultIndex] = left[leftIndex++];
            }
        }
        return result;
    }


    static class SortTask extends RecursiveTask<int[]> {
        private int threshold;
        private int start;
        private int end;
        private int segmentation;
        private int[] src;

        public SortTask(int[] src, int start, int end, int segmentation) {
            this.src = src;
            this.start = start;
            this.end = end;
            this.threshold = src.length / segmentation;
            this.segmentation = segmentation;
        }

        @Override
        protected int[] compute() {
            if ((end - start) < threshold) {
                int mid = (end - start) / 2;
                SortTask leftTask = new SortTask(src, start, mid, segmentation);
                SortTask rightTask = new SortTask(src, mid + 1, end, segmentation);
                invokeAll(leftTask, rightTask);
                return SortForkJoin.merge(leftTask.join(), rightTask.join());
            } else {
                return SortForkJoin.sort(src);
            }
        }
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = SortForkJoin.createIntArray(100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SortTask sortTask = new SortTask(array, 0, array.length - 1, 1000);
        long start = System.currentTimeMillis();
        ForkJoinTask<int[]> forkJoinTask = forkJoinPool.submit(sortTask);
        System.out.println(" spend time:" + (System.currentTimeMillis() - start) + "ms");
        try {
            array = forkJoinTask.get();
            for (int i = 0, j = array.length; i < j; i++) {
                System.out.println(array[i]);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
