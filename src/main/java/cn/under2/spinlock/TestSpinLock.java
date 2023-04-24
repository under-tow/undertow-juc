package cn.under2.spinlock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();

        MySpinlock spinlock = new MySpinlock();

        new Thread(() -> {
            spinlock.myLock();
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlock.unMyLock();
            }
        }, "线程A").start();

        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            try {
                spinlock.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlock.unMyLock();
            }
        }, "线程B").start();
    }
}
