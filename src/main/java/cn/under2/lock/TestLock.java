package cn.under2.lock;


import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hzk
 * @date 2018/3/28
 */
public class TestLock {

    private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    static Lock lock = new ReentrantLock();

    public static void main(final String[] args) {
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            //尝试获取锁 成功true 失败false 立即返回结果
            boolean tryLock = lock.tryLock();
            System.out.println(thread.getName() + " " + tryLock);
            if (tryLock) {
                try {
                    System.out.println(thread.getName() + "获得了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            Thread thread = Thread.currentThread();
            boolean tryLock = lock.tryLock();
            System.out.println(thread.getName() + " " + tryLock);
            if (tryLock) {
                try {
                    System.out.println(thread.getName() + "获得了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }).start();
    }
}
