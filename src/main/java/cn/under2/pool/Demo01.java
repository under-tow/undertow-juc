package cn.under2.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池之后，要用线程池来创建线程
 * <p>
 * 三大方法
 * 1.newSingleThreadExecutor
 * 2.newFixedThreadPool
 * 3.newCachedThreadPool
 * <p>
 * 比如创建第一个 底层源码是：
 * public static ExecutorService newSingleThreadExecutor() {
 * return new Executors.FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()));
 * }
 */
public class Demo01 {
    public static void main(String[] args) {

        //单个线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建一个固定的线程池的大小
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //可伸缩的线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 100; i++) {
                int temp = i;
                threadPool.execute(() -> {
                    System.out.println(temp + " ==> " + Thread.currentThread().getName() + " gogo");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //使用后需要关闭线程池
            threadPool.shutdown();
        }

    }
}
