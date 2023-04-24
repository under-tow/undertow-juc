package cn.under2.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 线程不安全的集合
 */
public class UnSafeList {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<String>();
        Vector vector = new Vector();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                lists.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(lists.size());
    }
}
