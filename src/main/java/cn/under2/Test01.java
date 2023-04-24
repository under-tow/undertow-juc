package cn.under2;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add("juc");
                arrayList.add("juc");
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
        System.out.println(arrayList.size());
    }
}
