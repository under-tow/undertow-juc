package cn.under2.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo01 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);


        /**
         * 用两个线程 模仿一下ABD的问题
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> {
            boolean res1 = atomicInteger.compareAndSet(10, 20);
            System.out.println("1. 修改结果==>" + res1 + " ，新的值==>" + atomicInteger);
            boolean res2 = atomicInteger.compareAndSet(20, 10);
            System.out.println("2. 修改结果==>" + res1 + " ，新的值==>" + atomicInteger);
        });
        threadPool.execute(() -> {
            boolean res3 = atomicInteger.compareAndSet(10, 66);
            System.out.println("3. 修改结果==>" + res3 + " ，新的值==>" + atomicInteger);
        });
        Thread.currentThread().getThreadGroup().list();
        threadPool.shutdown();
    }
}
