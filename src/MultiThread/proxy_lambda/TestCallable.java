package MultiThread.proxy_lambda;
//1.实现Callable接口，需要返回值类型
//2.重写call方法，需要抛出异常
//3.创建目标对象
//4创建执行服务：ExecutorService ser=Executors.newFixedThreadPool(3);
//5提交执行：Future<Boolean> result1=ser.submit(t1);
//6获取结果：Boolean r1=result1.get()
//7关闭服务：ser.shutdownNow();


/*Callable的返回类型也可以设置成 Boolean 或者 Integer等等
boolean是基本数据类型，Boolean是它的封装类，和其他类一样，有属性有方法可以new
例如：Boolean flag=new Boolean(true)  (已经被废弃)
Boolean 是boolean 的实例化对象类，和Integer对应int一样
自jdk1.5.0以上版本后，Boolean在"赋值"和判断上和boolean一样，
即是你： boolean b1 = true ; 或者 Boolean b2 = true ; 都可以。
唯一只能使用Boolean上的就是从列表或者哈希表获取值时。
比如 boolean t = false;
Map map = new HashMap();
map.put("t", t);
那么获取值时只能用
Boolean t1 = (Boolean) map.get(t); //前面只能用Boolean强制转换，不能使用boolean.*/



import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//线程创建方方式三：实现Callable接口
public class TestCallable implements Callable<String>{

    private String url; //网络图片地址
    private String name;//保存的文件名

    public TestCallable(String url,String name){  //构造方法
        this.url=url;
        this.name=name;
    }


    @Override
    public String call() {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);
        return "AHHHHHHHHHHHHHHH";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://img2.baidu.com/it/u=1689420976,517306237&fm=26&fmt=auto&gp=0.jpg", "1.jpg");
        TestCallable t2 = new TestCallable("http://img.mp.sohu.com/upload/20170510/62d64c61ccc74f1d840467d483d87566_th.png", "2.jpg");
        TestCallable t3 = new TestCallable("https://img2.baidu.com/it/u=2522461457,668536920&fm=11&fmt=auto&gp=0.jpg", "3.jpg");

        //4创建执行服务：
        ExecutorService ser= Executors.newFixedThreadPool(3);

        //5提交执行：

        //ser.submit(t1);
        System.out.println(ser.submit(t1).get());
        ser.submit(t2);
        ser.submit(t3);


        /*
        //5提交执行：
        Future<String> r1=ser.submit(t1);
        Future<String> r2=ser.submit(t2);
        Future<String> r3=ser.submit(t3);

        //6获取结果：
        String rs1=r1.get();
        String rs2=r2.get();
        String rs3=r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //System.out.println(ser.submit(t1).get()); 与上面3步同样效果
        */

        //7关闭服务：
        ser.shutdownNow();
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