package MultiThread.gaoji;

/*使用线程池
◆背景:经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
◆思路:提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。
        可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
◆好处:
      ◆提高响应速度(减少了创建新线程的时间)
      ◆降低资源消耗(重复利用线程池中线程，不需要每次都创建)
      ◆便于线程管理(....)
        ◆corePoolSize:核心池的大小
        ◆maximumPoolSize:最大线程数
        ◆keepAliveTime:线程没有任务时最多保持多长时间后会终止*/

/*
◆JDK 5.0起提供了线程池相关API: ExecutorService 和Executors
◆ExecutorService: 真正的线程池接口。常见子类ThreadPoolExecutor
    ◆ void execute(Runnable command) :执行任务/命令，没有返回值，一般用来执行Runnable
    ◆<T> Future<T> submit(Callable<T> task):执行任务，有返回值，一般又来执行Callable
    ◆void shutdown() :关闭连接池
◆Executors: 工具类、线程池的工厂类，用于创建并返回不同类型的线程池
*/

/*
    ExecutorService service=Executors.newCachedThreadPool();方法构造一个线程池，
    会立即执行各个任务，如果有空闲的线程可用，就使用现有空闲线程执行任务；如果没有可用的空闲线程，则创建一个新线程

    ExecutorService service=Executors.newFixedThreadPool(n);方法构造一个具有固定大小的线程池。
    如果提交的任务数多余空闲线程数，就把未得到服务的任务放到队列中。当其他任务完成以后在运行这些排队的任务。

    ExecutorService service=Executors.newSingleThreadExecutor();方法
    是一个退化了的大小为1的线程池：由一个线程顺序地执行所提交的任务(一个接一个的执行)


*/


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试线程池
public class TestPool {
    public static void main(String[] args) {
        //创建服务，创建线程池

        //ExecutorService service=Executors.newCachedThreadPool();
        //ExecutorService service=Executors.newFixedThreadPool(5);
        ExecutorService service=Executors.newSingleThreadExecutor();


        //执行
        for (int i = 0; i < 100; i++) {
            service.execute(new MyThread());

        }


        //2.关闭连接
        service.shutdown();   //所有任务执行完后关闭

        //service.shutdownNow(); //立刻关闭

    }

}

class MyThread implements Runnable{
    static int i;

    @Override
    public void run() {
        System.out.println(i++);
        System.out.println(Thread.currentThread().getName());
    }
}



