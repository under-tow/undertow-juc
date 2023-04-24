package cn.under2.chapter1;

/**
 * 多线程一定快吗？
 * 下面的代码演示串行和并发执行并累加操作的时间，请分析：下面的代码并发执行一定比 串行执行快吗？
 * 代码来源：《并发编程的艺术》
 */
public class ConcurrencyTest {

    //
    /**
     * 改变count值，发现在大约count=10000时。多线程执行速度 约等于 单线程执行速度
     * 而大约在count值 < 10000时,多线程执行速度 小于 单线程执行速度
     * 而大约在count值 > 10000时,多线程执行速度 大于 单线程执行速度（不同机器可能略有差异）
     * 结论：在count值较小时，多线程的上下文比例占了较大比例的开销
     */
    private static final long count = 10000L;


    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}