package cn.under2.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用sleep来时间计时器
 * 在多线程使用锁时，sleep不会释放锁。wait会释放锁
 */
public class TestSleep01 {

    public static void countDown() throws InterruptedException {
        int num = 10;
        while (num-- != 0) {
            System.out.println("倒计时==>" + num + "秒");
            Thread.sleep(1000);
        }
    }

    public static void timeDown() throws InterruptedException {
        Date date;
        int num = 10;
        while (num-- != 0) {
            date = new Date();
            System.out.println("当前时间==>" + new SimpleDateFormat("HH:mm:ss").format(date));
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        //countDown();
        timeDown();
        long end = System.currentTimeMillis();
        System.out.println("用时==>" + (end - start) / 1000l);
    }
}
