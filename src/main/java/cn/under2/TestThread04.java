package cn.under2;

//多个线程同时操作一个对象
//买火车票的例子
public class TestThread04 implements Runnable{

    //票数
    int ticketNums = 10;

    @Override
    public  void run() {
        while (true){
            if(ticketNums <= 0){
                break;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票" );
        }
    }

    public static void main(String[] args) {
        TestThread04 t = new TestThread04();
        new Thread(t,"小明").start();
        new Thread(t,"小红").start();
        new Thread(t,"黄牛").start();
        //会出现重复售票的情况，后面用锁来解决
    }
}
