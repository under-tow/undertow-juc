package cn.under2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用： 类似于ajax
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test01();
        test02();
    }


    /**
     * 异步处理
     * 加了两个延迟 主要是为了展示是 异步执行的
     */
    public static void test01() {
        //异步处理
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is runAsync 。");
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" --- 1 gogogo --- ");
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(" --- 2 gogogo --- ");
    }

    /**
     * 异步处理 有返回值 可能成功，可能失败
     * 加了两个延迟 主要是为了展示是 异步执行的
     */
    public static void test02() throws ExecutionException, InterruptedException {
        //异步处理 有返回值
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is supplyAsync 。");
            int a = 1 / 0;
            return 1024;
        });


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" --- 1 gogogo --- ");
        Integer integer = completableFuture.whenComplete((t, u) -> {
            System.out.println("t => " + t + " , u=> " + u);
        }).exceptionally(e -> {
            System.err.println(e.getMessage());
            return -1;
        }).get();
        System.out.println("res ==> " + integer);
        System.out.println(" --- 2 gogogo --- ");
    }

}
