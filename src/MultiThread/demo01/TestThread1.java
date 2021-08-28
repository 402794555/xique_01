package MultiThread.demo01;
//直接调用run()方法不会创建线程，如果调用start()方法会新建线程，然后自动调用run()方法
//直接调用run()方法不会新建分支线程，相当于新建了Thread类的一个对象，然后执行了对象的一个方法，所以打印出来的线程名称是main，这代表主线程
//start()方法，所以会创建分支线程，然后分支线程会自动调用run()方法，由于是分支线程调用的run()方法，所以返回的线程是分支线程branch

public class TestThread1 extends Thread{    //主类自己继承Thread

    public void run(){
        //run方法线程体
        for (int i = 0; i < 100; i++) { //100.for
            System.out.println("start()启动run方法---***********************" + i);
        }
    }

    public void run2(){
        //run方法线程体
        for (int i = 0; i < 100; i++) { //100.for
            System.out.println("直接启动run方法---~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + i);
        }
    }

    static  class TestThread2 extends Thread{  // 主类的内部类继承Thread
        public void run(){
            //run方法线程体
            for (int i = 0; i < 100; i++) { //100.for
                System.out.println("start()启动run方法---@@@@@@@@@@@@@@@@@@@@@@" + i);
            }
        }

    }
    //.run2()这个函数也是属于主线程的，没有新开线程池，它结束了才到后面的主线程内容
    //~~~~~~~~~~~~~~完全结束才到 ！！！！！！！！
    //而**********和#########与上面主线程，构成3个线程，并发执行
    public static void main(String[] args) {

        new TestThread1().start();  //主类继承
        new TestThread1().run2();   //main线程，主线程的前半部分
        new TestThread2().start();  //主类的内部类继承

        //main线程，主线程的后半部分
        for (int i = 0; i < 100; i++) {
            System.out.println("main方法---!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+i);
        }


    }

}
