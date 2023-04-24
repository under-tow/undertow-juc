package cn.under2.state;

/**
 * 1.礼让线程，让当前正在执行的线程暂停，但不阻塞
 * 2.将线程从运行状态转为就绪状态
 * 3.让CPU重新调度，礼让不一定成功，看CPU心情
 */
public class TestYield {


    public static void main(String[] args) throws InterruptedException {
        MyYield myYield = new MyYield();
        MyYield myYield2 = new MyYield();

        new Thread(myYield2, "B").start();
        new Thread(myYield, "A").start();
    }


}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}

class MyYield2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程正在执行");
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}
