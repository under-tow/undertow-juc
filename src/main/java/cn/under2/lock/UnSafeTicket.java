package cn.under2.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class UnSafeTicket {
    public static void main(String[] args) {
        TicketThread ticketThread = new TicketThread();
        new Thread(ticketThread, "小明").start();
        new Thread(ticketThread, "大胖").start();
        new Thread(ticketThread, "张三").start();
    }
}

class TicketThread implements Runnable {

    private AtomicInteger count;

    private boolean isEmpty = false;

    TicketThread() {
        count = new AtomicInteger();
        this.count.set(30);
    }

    @Override
    public void run() {
        while (!isEmpty) {
            buy();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buy() {
        if (count.intValue() > 0) {
            System.out.println(Thread.currentThread().getName() + "购买了一张票，剩余" + count.decrementAndGet() + "张");
        } else {
            isEmpty = true;
            System.out.println("已经售空.");
        }

    }
}
