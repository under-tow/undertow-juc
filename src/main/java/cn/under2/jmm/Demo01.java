package cn.under2.jmm;

import java.util.concurrent.TimeUnit;

public class Demo01 {
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 1;
            while (num == 0) {
                //System.out.println("i am running " + i++);
            }
        }).start();


        TimeUnit.SECONDS.sleep(1);

        num = 1;
        System.out.println("num==> " + num);
    }
}
