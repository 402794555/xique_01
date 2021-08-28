package MultiThread.state;

//优先级低只是意味着获得调度的概率低，并不是优先级低就不会被调用了，这都是看CPU的调度
//优先级为1-10，默认为5
//测试线程优先级
public class TestPriority{
    public static void main(String[] args) {
        //主线程默认优先级
        System.out.println(Thread.currentThread().getName()+"--> 优先级为："+Thread.currentThread().getPriority());

        MyPriority myPriority=new MyPriority();

        Thread t0=new Thread(myPriority);
        Thread t1=new Thread(myPriority);
        Thread t2=new Thread(myPriority);
        Thread t3=new Thread(myPriority);
        Thread t4=new Thread(myPriority);
        Thread t5=new Thread(myPriority);
        Thread t6=new Thread(myPriority);
        Thread t7=new Thread(myPriority);

        //先设置优先级，在启动
        t0.start();

        t1.setPriority(1);
        t1.start();

        t2.setPriority(2);
        t2.start();

        t3.setPriority(3);
        t3.start();

        t4.setPriority(4);
        t4.start();

        t5.setPriority(Thread.MAX_PRIORITY);//MAX_PRIORITY=10
        t5.start();

        t6.setPriority(Thread.MIN_PRIORITY);//MIN_PRIORITY=1
        t6.start();

        t7.setPriority(9);
        t7.start();


    }



}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--> 优先级为："+Thread.currentThread().getPriority());
    }
}
       /* main--> 优先级为：5
        Thread-7--> 优先级为：9
        Thread-2--> 优先级为：2
        Thread-5--> 优先级为：10
        Thread-0--> 优先级为：5
        Thread-4--> 优先级为：4
        Thread-1--> 优先级为：1
        Thread-3--> 优先级为：3
        Thread-6--> 优先级为：1*/