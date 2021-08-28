package MultiThread.gaoji;

import java.util.concurrent.*;

public class ThreadNew {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyThread1().start();

        new Thread(new MyThread2()).start();


        FutureTask<Integer> futureTask=new FutureTask<Integer>(new MyThread3());
        new Thread(futureTask).start();
        Integer integer=futureTask.get();
        System.out.println(integer);


        ExecutorService service= Executors.newFixedThreadPool(3);
        Future<Integer> r1=service.submit(new MyThread3());
        Integer rs1=r1.get();
        System.out.println(rs1);
        service.shutdown();



    }
}

//1.继承Thread类
class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

//2.实现Runnable接口
class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("MyThread2");
    }
}

//3.实现Callable接口
class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}