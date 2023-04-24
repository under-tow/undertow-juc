package cn.under2.collect;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    /**
     * HashSet底层是什么？
     * 底层就是一个HashMap
     * HashSet的add方法的本质是 HashMap的put方法
     */
    public static void main(String[] args) {

//        多线程不安全的Set
//        HashSet<Object> set = new HashSet<>();

//        解决方式一： Collections.synchronizedSet 将其转换为 安全的set
//        Set<Object> set = Collections.synchronizedSet(new HashSet<>());

//        解决方式二：JUC的CopyOnWriteArraySet
        Set<Object> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }).start();
        }
    }
}
