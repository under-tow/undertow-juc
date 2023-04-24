package cn.under2.spinlock;

import java.util.concurrent.atomic.AtomicReference;

public class MySpinlock {

    public static void main(String[] args) {

    }

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public void test() {
    }

    public void myLock() {
        Thread thread = Thread.currentThread();

        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + " try to  lock ...  自旋中");
        }
        System.err.println(Thread.currentThread().getName() + "  get  mylock");
    }

    public void unMyLock() {
        Thread thread = Thread.currentThread();

        while (!atomicReference.compareAndSet(thread, null)) {
            System.out.println(Thread.currentThread().getName() + " try to  unlock ... 自旋中");
        }
        System.err.println(Thread.currentThread().getName() + "  is leave");

    }

}
