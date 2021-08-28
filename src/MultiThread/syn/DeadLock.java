package MultiThread.syn;

//死锁：多个线程相互抱着对方需要的资源，然后形成僵持。
public class DeadLock {
    public static void main(String[] args) {

        //Makeup g1=new Makeup(0,"灰姑凉");
       // Makeup g2=new Makeup(1,"白雪公主");
       // g1.start();
        // g2.start();

        Makeup g1=new Makeup("灰姑凉");
        new Thread(g1,"线程一").start();   //这种情况也会死锁，注意它锁的不是Makeup的对象g1
        new Thread(g1,"线程二").start();   //它锁的是Lipstick类的对象lipstick还有Mirror的对象mirror
    }
}

//口红
class Lipstick{

}

//镜子
class Mirror{

}

class Makeup extends Thread {

    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;   //选择
    String girlName; //使用化妆品的人

    Makeup(String girlName) {  //构造方法
        //this.choice = choice;
        this.girlName = girlName;
    }


    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //化妆，互相持有对方需要的锁....................要想不死锁，这里不要使用嵌套锁，把两个锁并列就好。
    private void makeup() throws InterruptedException {
        if (Thread.currentThread().getName()== "线程一") {
            synchronized (lipstick) {//获得口红的锁
                System.out.println(Thread.currentThread().getName()+this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror) { //一秒钟后想获得镜子的锁
                    System.out.println(Thread.currentThread().getName()+this.girlName + "获得镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {//获得镜子的锁
                System.out.println(Thread.currentThread().getName()+this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick) { //一秒钟后想获得口红的锁
                    System.out.println(Thread.currentThread().getName()+this.girlName + "获得口红的锁");
                }
            }
        }
    }
}











