package cn.under2.lock;

/**
 * 生产者 消费者 第二钟方式：信号灯法
 */
public class TestPC02 {
    public static void main(String[] args) {
        TvShow show = new TvShow();
        new Player(show).start();
        new Watcher(show).start();
    }
}

//生产者--》演员
class Player extends Thread {
    TvShow tvShow;

    public Player(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tvShow.play("好看的电视剧");
            } else {
                this.tvShow.play("无聊的广告");
            }
        }
    }
}

//消费者--》观众
class Watcher extends Thread {
    TvShow tvShow;

    public Watcher(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            this.tvShow.watch();
        }
    }
}

//生产者--》电视节目(录播，，不是直播)
class TvShow extends Thread {
    //演员表演，观众等待
    //观众观看，演员等待
    String voice;//表演的节目
    boolean flag = false;

    //表演
    public synchronized void play(String voice) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演完了:" + voice);
        //通知观众观看
        this.notifyAll();
        this.voice = voice;
        this.flag = true;
    }

    //观看
    public synchronized void watch() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("观众观看了:" + this.voice);
        //在通知演员准备下一个节目
        this.notifyAll();
        this.flag = false;
    }
}