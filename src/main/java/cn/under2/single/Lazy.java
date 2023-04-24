package cn.under2.single;

/**
 * 懒汉式单例模式（临阵磨枪）
 */
public class Lazy {

    public Lazy() {
        System.err.println(Thread.currentThread().getName() + " : my lazy is ok. ");
    }

    private static Lazy lazy = null;

    //单线程下是没有问题的
    public static Lazy getInstance() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    /**
                     * lazy = new Lazy();
                     * 不是原子操作
                     * 1.分配内存空间
                     * 2.
                     * 3. 将引用指向 内存
                     */
                    lazy = new Lazy();

                }
            }
        }
        return new Lazy();
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> Lazy.getInstance()).start();
        }

//        ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            threadExecutor.execute(()->{
//                try {
//                    TimeUnit.MILLISECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Lazy.getInstance();
//            });
//        }
//        threadExecutor.shutdown();
    }
}
