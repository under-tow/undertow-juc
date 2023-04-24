package cn.under2.lock;


import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    public static void main(String[] args) {
        Lock lock = new Lock();
        new Thread(lock, "黄牛").start();
        new Thread(lock, "小李").start();
        new Thread(lock, "胖子").start();
    }
}

class Lock implements Runnable {

    int count = 100;

    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            while (true) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张，余票==>" + count--);
                    if (count == 80 && Thread.currentThread().getName() == "黄牛") {
                        wait();
                    }
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}