package MultiThread.gaoji;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 t=new TestLock2();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

    }
}

class TestLock2 implements Runnable{
    int ticketNums=10;

    //定义lock锁
    private final ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {
        while(true){
                try {
                    lock.lock(); //显示加锁
                    if (ticketNums > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ticketNums--);

                    } else {
                        break;
                    }
                }

                finally {
                    lock.unlock();
                }
        }
    }
}