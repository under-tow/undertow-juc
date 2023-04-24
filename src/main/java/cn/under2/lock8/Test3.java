package cn.under2.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 了解锁对象的情况
 * 这里创建了两个对象，当执行对象1的方法时，对象2并不会受影响
 * 这里的执行顺序为：
 * 1.执行phone1的sendMessage
 * （方法内有延迟4秒，但是phone2.sendMesage并不会等待这4秒在执行，
 * 因为锁的对象不同，所以phone2没有被上锁，不需要等待phone1释放锁）
 * 2.执行等待1秒
 * 3.执行phone2的sendMessage（方法内有延迟4秒）
 * 4.sendMessage的延迟结束后 main方法结束
 */
public class Test3 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {
            phone1.sendMessage();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.sendMessage();
        }).start();

    }
}

class Phone4 {
    public synchronized void sendMessage() {
        System.out.println("Send Message..");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void call() {
        System.out.println("Phone Call..");
    }

    public void hello() {
        System.out.println("Phone Hello..");
    }
}
