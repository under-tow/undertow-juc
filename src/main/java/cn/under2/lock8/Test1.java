package cn.under2.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：关于锁的八个问题
 * 1.标准情况下，两个线程不一定谁先执行（打印）
 * 2. 在A处加上延迟，则肯定是发短信先获取到锁，发短信先执行
 * 3. 在B处加上延迟，不一定谁先执行（打印）
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMessage();
        }).start();
        // #A
        new Thread(() -> {
            phone.call();
        }).start();
    }
}

/**
 * 这两个方法的synchroized锁的是当前的对象，
 * 即一个线程获取到锁了。另一个线程即使执行另一个方法也等待
 */
class Phone {
    public synchronized void sendMessage() {
        //#B
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Send Message..");
    }

    public synchronized void call() {
        System.err.println("call Phone..");
    }
}