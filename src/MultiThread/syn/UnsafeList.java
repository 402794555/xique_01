package MultiThread.syn;

import java.util.ArrayList;
import java.util.List;


//加锁后可以保证list安全，虽然可能最后打印的还是小于10000那是因为主线程跑快了，提前打印了当前的大小，
// 而此时还有几个线程没有完成add，实际上最后list就是10000个元素，可以然主线程睡一会再打印大小
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list=new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                    synchronized (list) {
                        list.add(Thread.currentThread().getName());
                    }
            }).start();
        }

        Thread.sleep(30);
        System.out.println(list.size());
        Thread.sleep(30);
        System.out.println(list.size());

    }
}
