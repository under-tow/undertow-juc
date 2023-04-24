package cn.under2.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {
    public static void main(String[] args) {
        //1.创建服务，创建线程池,参数为线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);
        //2.执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        //3.关闭连接
        service.shutdown();
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
        }
    }
}