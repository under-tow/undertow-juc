package cn.under2.state;

/**
 * 可以为线程设置优先级 优先级的范围是1-10
 * 数字越大先执行的可能性越高
 * 不一定按优先级执行，看CPU心情（调度顺序）
 */
public class TestPriority {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Daemon daemonThread = new Daemon();
        Thread a = new Thread(myThread, "A");
        Thread b = new Thread(myThread, "B");
        Thread c = new Thread(myThread, "C");
        Thread daemon = new Thread(daemonThread, "DaemonThread");

        //优先级是8
        a.setPriority(8);
        a.start();
        //优先级是10（最大）
        b.setPriority(Thread.MIN_PRIORITY);
        b.start();
        //默认优先级5
        c.start();
        //守护线程
        daemon.setDaemon(true);
        //daemon.start();
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "执行了,优先级是==>" + Thread.currentThread().getPriority());
    }
}

/**
 * JVM等待所有用户线程执行完，自动结束守护线程
 * 守护线程的使用场景：GC、后台日志统计
 */
class Daemon implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("守护线程正在运行...." + i++);
        }
    }
}