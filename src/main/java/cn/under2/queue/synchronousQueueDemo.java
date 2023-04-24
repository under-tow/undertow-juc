package cn.under2.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 同步对列
 * 容量为 1 的队列
 */
public class synchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("put==>" + i);
                    synchronousQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("remove" + synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
