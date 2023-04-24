package cn.under2;

//创建线程方式一：继承Thread类，重写Run方法，调用start开启线程
public class TestThread extends Thread {

    @Override
    public void run() {
        //run方法线程体
        for (int i = 1; i < 2001; i++) {
            System.out.println("TestThread ==》" + i + "次");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //main线程
    public static void main(String[] args) {
        //创建一个线程对象
        TestThread thread = new TestThread();
        //调用start方法，不一定立即执行
        thread.start();

        for (int i = 1; i < 2001; i++) {
            System.err.println("Main ==》" + i + "次");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
