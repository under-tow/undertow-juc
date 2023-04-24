package cn.under2;

/**
 * 如何优雅的停止线程
 * 1.建议线程正常停止，利用次数，不建议死循环
 * 2.建议使用标志位，设置一个标志位
 * 3.不要使用stop或者destory等果实或者JKD不建议使用的方法
 */

public class TestThreadStop implements Runnable{

    private static boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("==>" + i++);
        }
    }

    private  static void stop(){
        flag = false;
    }

    public static void main(String[] args) throws InterruptedException {

        TestThreadStop t = new TestThreadStop();
        new Thread(t).start();
        Thread.sleep(10);
        stop();

    }


}
