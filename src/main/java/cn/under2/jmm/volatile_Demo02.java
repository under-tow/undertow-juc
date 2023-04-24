package cn.under2.jmm;

public class volatile_Demo02 {

    private static volatile int num = 0;

    //此方法是同步的，可以保证结果正确
    public static synchronized void add_syn() {
        num++;
    }

    public static void add() {
        num++;
    }

    /**
     * volatile 不保证原子性
     * 所以结果是不对的
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //理论上num 应该为20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        //默认有 main 和 gc线程在运行
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " ==> " + num);
    }
}
