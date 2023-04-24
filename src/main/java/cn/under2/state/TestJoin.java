package cn.under2.state;

/**
 * 测试Join方法，可以想象成插队
 * 先让此线程执行，其他线程阻塞
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.err.println("我来插队了==>" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //启动自定义的线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //Main主线程
        for (int i = 0; i < 300; i++) {
            System.out.println("Main执行中...==>" + i);
            if (i == 200) {
                thread.join();
            }
        }
    }
}
