package MultiThread.syn;

//不安全的买票
//线程不安全，有重复买票，有负数
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station=new BuyTicket();

        new Thread(station,"苦逼的我").start();
        new Thread(station,"牛逼的你们").start();
        new Thread(station,"可恶的黄牛党").start();


    }
}

class BuyTicket implements Runnable{
    //票
    private int ticketNums=10;
    boolean flag=true; //外部停止方式


    @Override
    public void run() {
        //买票
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //同步方法  synchronized(this)锁默认的自己这个对象的buy()方法,同一个对象只能互斥调用这个方法,当然锁run()方法也是可以的
    private synchronized void buy() throws InterruptedException {
        //判断是否有票
        if(ticketNums<=0){
            flag=false;
            return;
        }

        //模拟延时
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName()+" 拿到 "+ticketNums--);
    }
}

