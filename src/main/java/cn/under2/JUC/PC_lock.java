package cn.under2.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC_lock {
    public static void main(String[] args) {
        Data1 data = new Data1();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.incrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.incrment();
                    Thread.sleep(101);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/**
 * 资源类
 */
class Data1 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //+1
    public void incrment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                //等待
                condition.await();
            }
            //业务操作
            number++;
            System.out.println(Thread.currentThread().getName() + "==>" + number);
            //通知其他线程，我已经操作完了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //-1
    public void decrment() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            //业务操作
            number--;
            System.out.println(Thread.currentThread().getName() + "==>" + number);
            //通知其他线程，我已经操作完了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}