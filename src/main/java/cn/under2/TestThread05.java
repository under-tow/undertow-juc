package cn.under2;

//模拟龟兔赛跑
public class TestThread05 implements Runnable{

    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            //加上Sleep，效果明显
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印乌龟跑步
            String name = Thread.currentThread().getName();
            if(name.equals("乌龟")){
                System.out.println( name+ "跑了" + i + "步");
            }
            //打印兔子跑步，区分颜色
            if (name.equals("兔子")){
                System.err.println( name+ "跑了" + i + "步");
                //中间让兔子歇会
                if( i==300 || i==600 ){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //如果有人跑完了1000步就结束游戏
            if(gameOver(i)){
                break;
            }
        }
    }

    //判断是否完成比赛
    private boolean gameOver(int steps){
        if (winner != null){
            return true;
        }
        if(steps==1000){
            winner = Thread.currentThread().getName();
            System.out.println("winner is "+ winner);
        }
        return false;
    }

    public static void main(String[] args) {
        TestThread05 race = new TestThread05();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();
    }
}
