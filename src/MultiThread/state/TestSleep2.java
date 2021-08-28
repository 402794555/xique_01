package MultiThread.state;


//没有继承Thread类也能调用 Thread.sleep() 因为静态方法可以直接调用,会自动获取到当前线程


import java.text.SimpleDateFormat;
import java.util.Date;

//模拟倒计时。。。。
public class TestSleep2 {

    public static void main(String[] args) {
        //模拟倒计时。。。。
        try {
            tenDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //打印当前系统时间
        Date startTime=new Date(System.currentTimeMillis());//获取系统当前时间

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime=new Date(System.currentTimeMillis());//更新当前时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void tenDown() throws InterruptedException {
        int num=10;
        while(true){
            Thread.sleep(1000);
            System.out.println(num--);
            if(num<=0){
                break;
            }
        }



    }
}
