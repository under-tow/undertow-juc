package cn.under2.state;

/**
 * 观察线程的状态
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程执行");
        });
        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state); //NEW

        //观察启动后
        thread.start(); //启动线程
        state = thread.getState();
        System.out.println(state); //RUN

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(500);
            state = thread.getState();
            System.out.println(state); //RUN
        }
        thread.start();//线程死亡后就不能再次运行了

    }
}
