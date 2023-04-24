package cn.under2.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 什么时候会用到BlockQueue呢？
 * 1. 多线程并发处理
 * 2. 线程池
 */
public class BlockQueueDemo {

    public static void main(String[] args) {

        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        // add 和 remove方法
        test1(queue);

        //offer 和 pull方法
        //test2(queue);

        //put 和 take方法
        //test3(queue);

        //offer(E e, long timeout, TimeUnit unit) 和 poll(long timeout, TimeUnit unit)
        //test4(queue);
    }

    /**
     * add 和 remove方法
     * 操作失败时候抛出异常
     *
     * @param queue
     */
    public static void test1(ArrayBlockingQueue queue) {
        //添加操作 add
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        //超出queue的容量 抛出异常 java.lang.IllegalStateException: Queue full
        //System.out.println(queue.add("d"));

        //移除操作 remove
        System.out.println(queue.remove());
        //取出 队首元素 此时应该是 b  因为a被取走了
        System.out.println("queue first element is : " + queue.element());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //没有元素可取出 抛出异常  java.util.NoSuchElementException
        //System.out.println(queue.remove());

    }

    /**
     * offer 和 pull方法
     * 增加时只返回 true 或 false 不会抛出异常
     * 取出时只返回 该元素 或 null 不会抛出异常
     *
     * @param queue
     */
    public static void test2(ArrayBlockingQueue queue) {
        //添加操作 add
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        //超出queue的容量 但并不会抛出异常 只是返回false
        //System.out.println(queue.offer("d"));

        //移除操作 remove
        System.out.println(queue.poll());
        //取出 队首元素 此时应该是 b 因为a被取走了
        System.out.println("queue first element is : " + queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //没有元素可取出 不会抛出异常  返回null
        //System.out.println(queue.poll());
    }

    /**
     * void put(E e)  throws InterruptedException 和 E take()  throws InterruptedException方法
     * 增加时如果容量已满 则等待空位 死等
     * 取出时如果容量为空 则等待元素进行取出 死等
     *
     * @param queue
     */
    public static void test3(ArrayBlockingQueue queue) {
        //添加操作 add
        try {
            queue.put("a");
            queue.put("b");
            queue.put("c");
            //队列容量是3 已无法放入第四个元素  等待有空格（空位可用）时再放入该元素 死等..
            //queue.put("d");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //超出queue的容量 但并不会抛出异常 只是返回false
        //System.out.println(queue.offer("c"));

        //移除操作 remove
        try {
            System.out.println(queue.take());
            //取出 队首元素 此时应该是 b 因为a被取走了
            System.out.println("queue first element is : " + queue.peek());
            System.out.println(queue.take());
            System.out.println(queue.take());
            //此时队列为空 已经没有元素可以取出  等待有元素可以取出 死等..
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //没有元素可取出 不会抛出异常  返回null
        //System.out.println(queue.take());
    }

    /**
     * boolean offer(E e, long timeout, TimeUnit unit)  throws InterruptedException 方法
     * E poll(long timeout, TimeUnit unit) throws InterruptedException 方法
     * <p>
     * 这是offer() 和 poll()的重载方法
     * 增加成功返回 true ， 若容量已满 再尝试一段时间 成功返回true，否则false
     * 取出成功返回 该元素 ， 若容量为空 再尝试一段时间 成功返回该元素，否则null
     *
     * @param queue
     */
    public static void test4(ArrayBlockingQueue queue) {
        //添加操作 add
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        //超出queue的容量 但并不会抛出异常 只是返回false
//        try {
//            System.out.println(queue.offer("d",2, TimeUnit.SECONDS));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //移除操作 remove
        System.out.println(queue.poll());
        //取出 队首元素 此时应该是 b 因为a被取走了
        System.out.println("queue first element is : " + queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //没有元素可取出 不会抛出异常  返回null
//        try {
//            System.out.println(queue.poll(2, TimeUnit.SECONDS));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
