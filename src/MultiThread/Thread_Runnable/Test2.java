package MultiThread.Thread_Runnable;

//*****注意：其实Thread 与 Runnable子类 是装饰者模式 不是代理模式

//代理类代理自己已经由完善功能的子类
public class Test2{
    public static void main(String[] arg){//t.setName("88888888888");给这个实例设置名字不会打印
        Thread t =new MyTread2();  //这里的可以直接调用t.start() 就和Test1一样，继承了Thread就有了start()方法
        Thread t1=new Thread(t,"线程1");
        Thread t2=new Thread(t,"线程2");
        t1.start();
        t2.start();


    }
    static class MyTread2 extends Thread {
        private int ticket = 5;
        public void run(){
            while(true){
                System.out.println(Thread.currentThread().getName()+"  Thread_two ticket = " + ticket--);
                if(ticket < 0){
                    break;
                }
            }
        }
    }
}

 /*线程1  Thread ticket = 5
线程1  Thread ticket = 3
线程2  Thread ticket = 4
线程2  Thread ticket = 1
线程1  Thread ticket = 2
线程2  Thread ticket = 0*/