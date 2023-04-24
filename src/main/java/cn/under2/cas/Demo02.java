package cn.under2.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo02 {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 发现 stampedReference 的版本号为：" + stamp);
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        }, "A").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 发现 stampedReference 的版本号为：" + stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(b); // false
            System.out.println(stampedReference.getReference()); // 100
            System.out.println(stampedReference.getStamp()); // 3

        }, "B").start();

    }

}
