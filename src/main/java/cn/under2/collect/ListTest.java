package cn.under2.collect;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("A", "C", "G", "D");
        strings.forEach(System.out::println);

        //原始方式线程不安全
        //List<Object> arrayList = new ArrayList<>();

        //方式一：使用Vector 安全集合
        //List<Object> arrayList = new Vector<>();

        //方式二：Collections.synchronizedList将其变为安全集合
        //List<Object> arrayList = Collections.synchronizedList(new ArrayList<>());

        //方式三 使用JUC的CopyOnWriteArrayList 写入时复制 COW 计算机程序设计领域的一种优化策略
        /**
         * 多个线程调用的时候，List写入的时候避免覆盖，造成数据问题
         * CopyOnWriteArrayList 比 Vector 效率高一点 使用的是Lock，而Vector使用的是Synchronized
         */
        List<Object> arrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(arrayList);
            }, String.valueOf(i + 1)).start();
        }
        System.err.println(arrayList);
        //arrayList.forEach(System.out::println);
    }
}
