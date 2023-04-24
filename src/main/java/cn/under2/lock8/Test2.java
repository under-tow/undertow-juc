package cn.under2.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：关于锁的八个问题
 * 是先执行发短信 还是 先执行 Hello？ （Hello是普通方法）
 * 先执行了发短信，因为A处的延迟
 * 但是先打印了hello 因为B处的延迟
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sendMessage();
        }).start();
        // #A
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.hello();
        }).start();
    }
}

/**
 * 这次的Hello方法没有锁
 */
class Phone2 {
    public synchronized void sendMessage() {
        //#B
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Send Message..");
    }

    public synchronized void call() {
        System.err.println("call Phone..");
    }

    public void hello() {
        System.err.println("hello Phone..");
    }
}