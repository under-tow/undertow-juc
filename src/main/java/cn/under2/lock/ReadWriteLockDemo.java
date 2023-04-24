package cn.under2.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLockDemo
 * 三种情况：
 * 1. 读-读 可以共存
 * 2. 读-写  不可以共存
 * 3. 写-写     不可以共存
 * <p>
 * 独占锁-》就是指 写锁 一次只能被一个线程占有
 * 共享锁-》就是指 读锁一次可以被多个线程占有
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock cache = new MyCacheLock();
        //MyCache cache = new MyCache();
        //写入
        for (int i = 1; i <= 20; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(temp + "", "val");
            }, String.valueOf(i)).start();
        }
        //读取
        for (int i = 1; i <= 20; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

//自定义缓存
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    //存，写
    public void put(String key, Object val) {
        System.out.println(Thread.currentThread().getName() + " write " + key);
        map.put(key, val);
        System.out.println(Thread.currentThread().getName() + " write done");
    }

    //取，读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " read " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + " read done");
    }
}

//自定义缓存 加锁的
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //存，写
    public void put(String key, Object val) {
        lock.writeLock().lock();

        try {
            System.err.println(Thread.currentThread().getName() + " write " + key);
            map.put(key, val);
            System.err.println(Thread.currentThread().getName() + " write done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    //取，读
    public void get(String key) {
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " read " + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + " read done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}