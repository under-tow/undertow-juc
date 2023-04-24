package cn.under2.stream;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //创建五个对象
        User zhanghsan = new User(1, "zhanghsan", 15);
        User lsi = new User(2, "lsi", 26);
        User wangwu = new User(3, "wangwu", 18);
        User zhaoliu = new User(4, "zhaoliu", 30);
        User xiaoming = new User(5, "xiaoming", 10);
        //转换为list
        List<User> list = Arrays.asList(zhanghsan, lsi, wangwu, zhaoliu, xiaoming);

        /**
         * 1. 过滤 age>23
         * 2. 过滤 age是2的整数倍
         * 3. 让名字变成大写
         * 4. 用户名字母倒着排序
         * 5. 只输出一个
         */
        //链式编程
        list.stream()
                .filter(user -> {
                    return user.getAge() > 23;
                })
                .filter(u -> {
                    return u.getAge() % 2 == 0;
                })
                .map(u -> {
                    return u.getName().toUpperCase();
                })
                .sorted((u1, u2) -> {
                    return u2.compareTo(u1);
                })
                .limit(1)
                .forEach(System.out::println);
        System.err.println("----------------------------------------");
        //上下两个Stream同时运行 结果会不正确 不知道为什么
//        list.stream().
//                filter(u->{return u.getAge()%2 == 0;}).
//                forEach(System.out::println);

    }
}
