package cn.under2.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    //普通程序员
    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= 10_0000_0000; i++) {
            sum += i;
        }
        System.out.println("sum==> " + sum);
        long end = System.currentTimeMillis();
        System.out.println("Test1 :take " + (end - start) / 1000.0f + " seconds");
    }

    //中级程序员
    public static void test2() {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0, 10_0000_0000);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        try {
            Long l = submit.get();
            System.out.println("sum==> " + l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Test2 :take " + (end - start) / 1000.0f + " seconds");
    }

    //高级程序员
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000).parallel().reduce(0, Long::sum);
        System.out.println("sum==> " + sum);
        long end = System.currentTimeMillis();
        System.out.println("Test3 :take " + (end - start) / 1000.0f + " seconds");
    }
}
