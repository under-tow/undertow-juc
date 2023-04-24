package cn.under2.lock;

/**
 * 生产者 消费者 第一种方式：管程法
 */
public class TestPC01 {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }
}

class Producer extends Thread {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            container.push(new Chicken(i));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            container.pop();
        }
    }
}

class SynContainer {

    //产品容器
    Chicken[] cs = new Chicken[15];
    //当前容量
    int currentCount;

    public synchronized void push(Chicken chicken) {
        //如果容器满了，就需要等待消费者消费
        if (currentCount >= cs.length - 1) {
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //如果没有满就放入产品（鸡肉）
        System.out.println("生产了==》" + chicken.id + "只鸡,currentCount==>" + currentCount);
        cs[currentCount] = chicken;
        currentCount++;

        //可以通知消费者消费了
        this.notifyAll();
    }

    public synchronized Chicken pop() {
        //判断是否还有库存（容器）里是否还有鸡肉
        if (currentCount <= 0) {
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.err.println("消费了==》" + cs[currentCount - 1].id + "只鸡,currentCount==>" + (currentCount - 1));
        Chicken chicken = cs[--currentCount];
        //如果还有鸡肉
        if (currentCount < 5) {
            this.notifyAll();
        }
        return chicken;

    }


}

class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}