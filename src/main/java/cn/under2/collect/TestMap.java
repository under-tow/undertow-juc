package cn.under2.collect;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    public static void main(String[] args) {

        HashMap<Object, Object> hashMap1 = new HashMap<>(16, 0.75f);
        //解决方式一:
        //Map<Object, Object> hashMap = Collections.synchronizedMap(hashMap1);
        //解决方式二：
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(() -> {
                hashMap.put(finalI, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(hashMap);
            }).start();
        }
        //此处的代码，在线程后面执行了
        System.out.println("done");
        System.out.println(hashMap.size());
    }
}
