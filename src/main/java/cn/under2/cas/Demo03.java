package cn.under2.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo03 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Integer -128~127 会直接使用缓存中的值
         * 加入初始化的这个值 不属于这个返回 会导致第一次就无法更新
         * 原因： Integer每一次是用value1Of创建了一个新的对象，
         * 导致比如 Integer.valueOf(500) 和 Integer.valueOf(500) 的地址不相等。见testInt方法的结果
         */

        testInt();

        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(50, 1);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(() -> {
            atomicStampedReference.compareAndSet(50, 60, 1, atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(60, 50, 2, atomicStampedReference.getStamp() + 1);
        });
        pool.execute(() -> {
            System.out.println(atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(50, 123, 1, atomicStampedReference.getStamp() + 1);
        });
        pool.shutdown();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(atomicStampedReference.getReference());
    }

    public static void testInt() {
        System.out.println(
                "Integer.valueOf(500) == Integer.valueOf(500) ?? 结果是 "
                        + (Integer.valueOf(500) == Integer.valueOf(500)));
    }
}
