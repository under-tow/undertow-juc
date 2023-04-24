package cn.under2.pool;

import java.util.concurrent.*;

public class Demo02 {
    public static void main(String[] args) {

        /**
         * maximumPoolSize 最大线程数该如何设置呢？
         *  1. CPU密集型 几核 就是几 可以保证CPU效率最高
         *  2. IO密集型 判断你程序中十分耗费IO的线程
         *      比如：程序 15个大型任务 io十分占用资源u你
         */
        final int corePoolSize = 2;
        final int maximumPoolSize = 5;
        final int keepAliveLive = 3;
        final int queueCapacity = 3;

        //获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //阻塞队列
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(queueCapacity);
        // 线程池不能处理的情况下
        //1. AbortPolicy 拒绝并 抛出异常 java.util.concurrent.RejectedExecutionException
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        //2. CallerRunsPolicy 如果任务被拒绝了，则由调用线程（提交任务的线程）直接执行此任务 （这里会直接由Main线程来执行）
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        //3.DiscardPolicy 对于拒绝任务，默默丢弃拒绝任务处理程序。
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        //4.DiscardOldestPolicy 对于拒绝任务，抛弃了 的未经处理的请求，然后重试 execute处理程序，
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();

        //工作中 推荐 这样方式来创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveLive, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(), discardOldestPolicy);

        try {
            for (int i = 0; i < 5; i++) {
                int temp = i;
                poolExecutor.execute(() -> {
                    System.out.println(temp + " ==> " + Thread.currentThread().getName() + " go    queue is size => " + queue.size());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //使用后需要关闭线程池
            poolExecutor.shutdown();
        }

    }

    /**
     * 不处理，抛出异常
     * public static class AbortPolicy implements RejectedExecutionHandler {
     *     public AbortPolicy() {
     *     }
     *
     *     public void rejectedExecution(Runnable var1, ThreadPoolExecutor var2) {
     *         throw new RejectedExecutionException("Task " + var1.toString() + " rejected from " + var2.toString());
     *     }
     * }
     *
     * public static class CallerRunsPolicy implements RejectedExecutionHandler {
     *     public CallerRunsPolicy() {
     *     }
     *
     *     public void rejectedExecution(Runnable var1, ThreadPoolExecutor var2) {
     *         if (!var2.isShutdown()) {
     *             var1.run();
     *         }
     *
     *     }
     * }
     *
     * public static class DiscardOldestPolicy implements RejectedExecutionHandler {
     *     public DiscardOldestPolicy() {
     *     }
     *
     *     public void rejectedExecution(Runnable var1, ThreadPoolExecutor var2) {
     *         if (!var2.isShutdown()) {
     *             var2.getQueue().poll();
     *             var2.execute(var1);
     *         }
     *
     *     }
     * }
     *对于拒绝任务，默默丢弃拒绝任务处理程序。
     * public static class DiscardPolicy implements RejectedExecutionHandler {
     *     public DiscardPolicy() {
     *     }
     *
     *     public void rejectedExecution(Runnable var1, ThreadPoolExecutor var2) {
     *     }
     * }
     */
}