package cn.under2.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;
    private long end;
    private long threshold = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) < threshold) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //中间值
            long middle = (start + end) / 2;
            //拆分任务
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            //把任务压入队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }

}
