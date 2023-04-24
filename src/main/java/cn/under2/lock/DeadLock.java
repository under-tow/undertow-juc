package cn.under2.lock;

/**
 * A想要B的资源，B想要A的资源。造成死锁
 */
public class DeadLock {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }

}

class Lock1 implements Runnable {
    @Override
    public void run() {
        try {
            int i = 0;
            System.out.println("Lock1 running");
            while (true) {
                synchronized (DeadLock.obj1) {
                    System.out.println("Lock1 lock obj1==>" + ++i);
                    Thread.sleep(1000);
                    synchronized (DeadLock.obj2) {
                        System.out.println("Lock1 lock obj2==>" + i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Lock2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock2 running");
            int i = 0;
            while (true) {
                synchronized (DeadLock.obj2) {
                    System.out.println("Lock2 lock obj2==>" + ++i);
                    Thread.sleep(1000);
                    synchronized (DeadLock.obj1) {
                        System.out.println("Lock2 lock obj1==>" + i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}