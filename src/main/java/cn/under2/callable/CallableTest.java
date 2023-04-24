package cn.under2.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        //复习Runnable
        new Thread(new A()).start();

        //Callable
        //1，首先new Thread的参数可以放入Runable的实现类
        //new Thread(new Runnable() .start();
        //2，FutureTask就是Runable的实现类
        //new Thread(new FutureTask().start();
        //3，FutureTask的参数是callable
        //new Thread(new FutureTask<V>( Callable ).start();

        B b = new B();
        FutureTask<Integer> futureTask = new FutureTask<>(b);
        //启动两次，最后只是打印一次。有缓存
        new Thread(futureTask, "B").start();
        new Thread(futureTask, "B").start();
        try {
            //获取callable的返回结果。get可能会产生阻塞,可以把他放在最后一行执行
            Integer res = futureTask.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//复习Runnable
class A implements Runnable {

    @Override
    public void run() {
        System.out.println("AAA");
    }
}

class B implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("callable run");
        return 1024;
    }
}

