package cn.under2.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 了解锁class的情况
 * 当synchronized修饰的方法也被static修饰时，这是锁的就是类
 * 所以多个线程操作这样的方法时，会等待锁的释放
 */
public class Test4 {
    public static void main(String[] args) {
        /**
         * phone1和phone2两个对象都有一个共同的类模板 Phone3
         */
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(() -> {
            phone1.sendMessage();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }).start();
        new Thread(() -> {
            phone2.call2();
        }).start();

    }
}

class Phone3 {
    public static synchronized void sendMessage() {
        System.out.println("Send Message..");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void call() {
        System.out.println("Phone Call..");
    }

    /**
     * 加入这里没有被static修饰，则phone2不会等待 类锁的释放
     */
    public synchronized void call2() {
        System.out.println("Phone Call2..");
    }


}
