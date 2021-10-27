
package MultiThread.Thread_Runnable;

//*****注意：其实Thread 与 Runnable子类 是装饰者模式 不是代理模式

//代理类的子类自己把事情都干了
public class Test1 {
    public static void main(String[] args) {
        Thread t1=new MyThread(); //也可以MyThread t1=new MyThread();
        Thread t2=new MyThread(); //括号里面不能直接设置名字？？？要 new Thread()才能自己设置
        t1.setName("线程1");      //因为父类构造方法不能被子类继承
        t2.setName("线程2");
        t1.start();      //可以直接调用t1.start()
        t2.start();

    }
    static class MyThread extends Thread{
        private  int ticket=5;
        public void run(){
            while(true){
                System.out.println(Thread.currentThread().getName()+"  Thread ticket="+ ticket--);
                if(ticket<0){
                    break;
                }
            }
        }
    }
}
/*线程1  Thread ticket=5
线程2  Thread ticket=5
线程1  Thread ticket=4
线程2  Thread ticket=4
线程1  Thread ticket=3
线程2  Thread ticket=3
线程1  Thread ticket=2
线程2  Thread ticket=2
线程1  Thread ticket=1
线程1  Thread ticket=0
线程2  Thread ticket=1
线程2  Thread ticket=0*/
