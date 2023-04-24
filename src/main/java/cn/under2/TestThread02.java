package cn.under2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread02 extends Thread{

    private String url;
    private String name;

    public TestThread02(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件===>" + name);

    }


    public static void main(String[] args) {
        new TestThread02("http://img.hopestation.top/hopestation_logo_1604639725577.png","hope1.png").start();
        new TestThread02("http://img.hopestation.top/hopestation_logo_1604639725577.png","hope2.png").start();
        new TestThread02("http://img.hopestation.top/hopestation_logo_1604639725577.png","hope3.png").start();
    }
}

class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}