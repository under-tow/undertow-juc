package cn.under2;

//创建线程方式2：实现Runnable接口，重写Run方法。
public class TestThread03 implements Runnable{
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
        TestThread03 t = new TestThread03();
        //调用start方法，不一定立即执行
        new Thread(t).start();

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
