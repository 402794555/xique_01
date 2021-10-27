
package MultiThread.Thread_Runnable;
//*****注意：其实Thread 与 Runnable子类 是装饰者模式 不是代理模式

//代理类代理自己的并列类
public class Test3 {
    public static void main(String[] args) {
        Runnable r=new MyThread3();   //MyThread3 t=new MyThread3();
        Thread t1=new Thread(r,"线程1"); //这里不能直接调用 r.start()和Test1不一样,要像Test2一样传入
        Thread t2=new Thread(r,"线程2");//因为这里只是实现了Runnable ，还没有start()方法
        t1.start();
        t2.start();
    }
    static class MyThread3 implements Runnable{
        private int ticket = 5;
        public void run(){
            while(true){
                System.out.println(Thread.currentThread().getName()+"  Runnable ticket = " + ticket--);
                if(ticket < 0){
                    break;
                }
            }
        }
    }
}
/*线程2  Runnable ticket = 4
线程2  Runnable ticket = 3
线程1  Runnable ticket = 5
线程2  Runnable ticket = 2
线程1  Runnable ticket = 1
线程2  Runnable ticket = 0*/