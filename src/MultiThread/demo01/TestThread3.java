package MultiThread.demo01;

//没有继承Thread类也能调用 Thread.sleep() 因为静态方法可以直接调用,会自动获取到当前线程

//多个线程操作同一个对象
//买火车票的例子

//发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱


public class TestThread3 implements Runnable{
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
        TestThread3 t=new TestThread3();
        new Thread(t,"小明").start();
        new Thread(t,"老师").start();
        new Thread(t,"黄牛党").start();
    }

}
   /*
      小明-->拿到了第10票
        黄牛党-->拿到了第10票
        老师-->拿到了第9票
        小明-->拿到了第8票
        黄牛党-->拿到了第7票
        老师-->拿到了第6票
        黄牛党-->拿到了第4票
        小明-->拿到了第5票
        老师-->拿到了第3票
        黄牛党-->拿到了第2票
        小明-->拿到了第1票
        老师-->拿到了第0票
        黄牛党-->拿到了第-1票

        */
