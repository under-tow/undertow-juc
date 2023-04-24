package cn.under2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

// 线程创建 的第三钟方式 callable
public class TestCallable implements Callable {


    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader1 webDownloader = new WebDownloader1();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件===>" + name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable t1 = new TestCallable("http://hopestation.top/upload/2020/10/jdk-551bbe6429df429ea5f03c415490b631.jpg", "hope1.png");
        TestCallable t2 = new TestCallable("http://hopestation.top/upload/2020/10/jdk-551bbe6429df429ea5f03c415490b631.jpg", "hope2.png");
        TestCallable t3 = new TestCallable("http://hopestation.top/upload/2020/10/jdk-551bbe6429df429ea5f03c415490b631.jpg", "hope3.png");

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> res1 = service.submit(t1);
        Future<Boolean> res2 = service.submit(t2);
        Future<Boolean> res3 = service.submit(t3);
        //获取结果
        Boolean b1 = res1.get();
        Boolean b2 = res2.get();
        Boolean b3 = res3.get();
        //打印结果
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        //关闭服务
        service.shutdown();

    }

}

class WebDownloader1 {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}