package cn.under2.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class volatile_Demo03 {

    private static volatile AtomicInteger num = new AtomicInteger();

    //是原子操作，可以保证结果正确

    public static void add() {
        num.getAndIncrement();
    }

    /**
     * volatile 不保证原子性
     * 所以结果是不对的
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //理论上num 应该为20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        //默认有 main 和 gc线程在运行
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " ==> " + num);
    }
}
