package cn.under2.chapter1;

public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    /**
     * 代码分析：
     *  首先代码里的两个synchronized锁，锁的是静态变量A\B
     *  代码运行后，t1 和 t2都能分别顺利进入到  synchronized (A){}、 synchronized (B){}代码块中。
     *  此时静态变量A、B已被占有锁。然后就出现了 ：
     *      - t1占有A锁，想要获取B锁,一直等待(因为B锁被t2占有中。。)
     *      - t2占有B锁，想要获取A锁,一直等待(因为A锁被t1占有中。。)
     *      (t1进入synchronized (A){}，执行代码块中sleep(2秒)，是为了确保 t2也占有了B锁，从而确保了死锁的发生 )
     */
    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}