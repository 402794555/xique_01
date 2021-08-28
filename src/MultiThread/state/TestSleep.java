package MultiThread.state;

//sleep(时间)指定当前线程阻塞的毫秒数 1000=1秒
//sleep存在异常 InterruptedException
//sleep时间达到后线程进入就绪状态
//sleep可以模拟网络延时，倒计时等
//每个对象都有一个锁，sleep不会释放锁    synchronized 内部锁

//没有继承Thread类也能调用 Thread.sleep() 因为静态方法可以直接调用,会自动获取到当前线程


//模拟网络：放大问题的发生性
public class TestSleep implements Runnable{
    //票数
    private int ticketNums=10;

    @Override
    public void run() {
        while(true){
            if(ticketNums<=0){
                break;
            }
            //模拟延时
            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->拿到了第"+ticketNums--+"票");

        }

    }
    public static void main(String[] args) {
        TestSleep t=new TestSleep();
        new Thread(t,"小明").start();
        new Thread(t,"老师").start();
        new Thread(t,"黄牛党").start();
    }

}

