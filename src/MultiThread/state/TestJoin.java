package MultiThread.state;

//join()不是静态方法，所以需要继承Thread类，或者让Thread类代理

//测试join方法，想象成插队
public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("线程vip来了 "+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //join()不是静态方法，所以需要继承Thread类，或者让Thread类代理

        //启动我们的线程
        TestJoin testJoin=new TestJoin();
        Thread thread=new Thread(testJoin);
        thread.start();


        //主线程
        for (int i = 0; i < 500; i++) {
            if(i==200){
                thread.join();//插队
            }
            System.out.println("main " +i);
        }
    }


}
