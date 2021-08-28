package MultiThread.demo01;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread2 extends Thread {  //主类自己继承Thread
    private String url; //网络图片地址
    private String name;//保存的文件名

    public TestThread2(String url,String name){  //构造方法
        this.url=url;
        this.name=name;
    }


    @Override
    public void run() {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);

    }


    public static void main(String[] args) {
        TestThread2 t1=new TestThread2("https://img2.baidu.com/it/u=1689420976,517306237&fm=26&fmt=auto&gp=0.jpg","1.jpg");
        TestThread2 t2=new TestThread2("http://img.mp.sohu.com/upload/20170510/62d64c61ccc74f1d840467d483d87566_th.png","2.jpg");
        TestThread2 t3=new TestThread2("https://img2.baidu.com/it/u=2522461457,668536920&fm=11&fmt=auto&gp=0.jpg","3.jpg");
        t1.start();
        t2.start();
        t3.start();
        /*下载了文件名为：2.jpg
        下载了文件名为：3.jpg
        下载了文件名为：1.jpg

        一般来说比较小的图片先下载完

        */


       /* Thread tt1=new Thread(t1);
        Thread tt2=new Thread(t1);
        Thread tt3=new Thread(t1);
        tt1.start();
        tt2.start();
        tt3.start();
        */
        /*下载了文件名为：1.jpg
        下载了文件名为：1.jpg
        下载了文件名为：1.jpg
*/
    }

}



//下载器
class WebDownloader{
    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
