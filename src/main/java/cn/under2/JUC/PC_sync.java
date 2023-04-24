package cn.under2.JUC;

public class PC_sync {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.incrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.incrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/**
 * 资源类
 */
class Data {
    private int number = 0;

    //+1
    public synchronized void incrment() throws InterruptedException {
        while (number != 0) {
            //等待
            this.wait();
        }
        //业务操作
        number++;
        System.out.println(Thread.currentThread().getName() + "==>" + number);
        //通知其他线程，我已经操作完了
        this.notifyAll();
    }

    //-1
    public synchronized void decrment() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        //业务操作
        number--;
        System.err.println(Thread.currentThread().getName() + "==>" + number);
        //通知其他线程，我已经操作完了
        this.notifyAll();
    }
}