package MultiThread.gaoji;

/*synchronized是Java中的关键字，是一种同步锁。
    修饰方法时锁定的是调用该方法的对象，即如果一个对象中有两个方法同时被synchronized，
    则同一个对象，调用这两个方法时，只能同时执行一个。但它并不能使调用该方法的多个对象在执行顺序上互斥。*/

    /*一但一个线程调用了wait/await方法，它就进入了这个条件的等待集(wait set)(synchronized在一个对象中只有一个关联条件)。
    当锁可用时，该线程并不会变为可运行状态。实际上。它人仍保持非活动状态，直到另一个线程在同一个条件上调用notifyAll/signalAll方法
    这个调用会重新激活等待这个条件的所有线程。当这些线程从等待集中移出时，它们再次成为可以运行的线程，调度器最终将再次将它们激活。
    同时它们会尝试重新进入该对象。一旦锁可用，它们中的某个线程将从wait/await调用返回，得到这个锁，*并从之前暂停的地方继续执行*

    此时，线程应当再次测试条件。不能保证现在一定满足条件————notifyAll/signalAll方法仅仅是通知等待的线程：现在有可能满足条件，值得再次检查条件
    while(accounts[from]<amount) sufficientFunds.await();
    */



//线程之间的通信
//测试：生产者消费者模型-->利用缓冲区解决：管程法
//生产者，消费者，产品，缓冲区
public class TestPC {
    public static void main(String[] args) throws InterruptedException {
        SynContainer container=new SynContainer(); //真实角色
        new Producer(container).start();    //代理同一个缓冲区(真实角色)
        new Consumer(container).start();
        //new Producer(container).start();//这里并不能多生产者多消费者，因为wait被唤醒后从原来暂停的地方开始的所以不再判断if(count==chickens.length)/if(count==0)
        //new Consumer(container).start();//要想能多生产者多消费者那么要把if判断改为while循环判断while(count==chickens.length)/while(count==0)
        //new Producer(container).start();//多生产者多消费者，在目前的程序来看，要让生产者数量等于消费者数量，因为每个线程都要启动run，其中for循环100次
        //new Consumer(container).start();//相当于每个生产者要生产100只，每个消费者消费100只，过不生产消费者不等，则会死锁
        Thread.sleep(3);         //改进方法：生产者类和消费者类中将变量i设为static，这样所有生产者共生产100个，所有消费者共消费100个，此时生产者消费者不等也不会死锁
        for (int i = 0; i < 100; i++) {
           System.out.println(container.count);
        }
    }
}

//生产者，具有代理缓冲区能力
class Producer extends Thread{
    static int i;
    SynContainer container;//声明真实类变量

                                    //一种代理思想，本来Thread子类Producer类中重写了run所以不能代理了Runnable接口下的类了
                                    //但是这里通过声明缓冲区类变量，构造器传入缓冲器类对象，run中操作缓冲区对象，让其有了代理缓冲区类的能力

    public Producer(SynContainer container){//构造器传入真实对象
        this.container=container;
    }

    //生产
    @Override
    public void run() {

        for (  i = 1; i < 100; i++) {
            container.push(new Chicken(i));   //container.push()和container.pop()都被synchronized锁方法,同一个对象互斥调用
            System.out.println(Thread.currentThread().getName()+"生产了 "+i+ "只鸡");
        }
    }
}

//消费者，具有代理缓冲区能力
class Consumer extends Thread{
    static int i;
    SynContainer container;     //一种代理思想，本来Thread子类Producer类中重写了run所以不能代理了Runnable接口下的类了
                                  //但是这里通过声明缓冲区类变量，构造器传入缓冲器类对象，run中操作缓冲区对象，让其有了代理缓冲区类的能力

    public Consumer(SynContainer container){//构造器传入真实对象
        this.container=container;
    }

    //消费
    @Override
    public void run() {
        for ( i = 1; i < 100; i++) {    //container.push()和container.pop()都被synchronized锁方法,同一个对象互斥调用
            System.out.println(Thread.currentThread().getName()+"消费了 "+container.pop().id+" 只鸡");
        }
    }
}

//产品
class Chicken{
    int id; //产品编号

    public Chicken(int id) {
        this.id = id;
    }
}


//缓冲区
class SynContainer{
    //需要一个容器大小
    Chicken[] chickens=new Chicken[10]; //这个数组其实就是一个容器
    //容器计数器
    int count=0;  //指向最后一只鸡的后面位置 0-11  （也可令count=-1）

    //生产者放入产品
    public synchronized void push(Chicken chicken){  //synchronized锁方法则默认锁定的是this,即本对象container对相应的方法互斥调用
        //判断能否生产                                 //synchronized锁代码块可以synchronized(this)锁本对象，也可synchronized(其它对象)

        while(count==chickens.length){//把if换成while循环判断，可以实现多对多

            //return;  //如果只有是一对一只有两个线程的话，在这里可以直接return,因为push()和pop()已经加锁了,但是多生产者或多消费者就不行了，需要wait()

            //等待消费者消费，生产者等待
            try {
                this.wait();  //wait();也可         //让操作到该对象该方法到这个位置的线程 ***** 在该位置等待*****，，wait()方法会释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果没有满，我们需要丢入产品
        chickens[count]=chicken;
        count++;

        //可以通知消费者消费产品
        this.notifyAll();   //notifyAll();也可
    }

    public synchronized Chicken pop(){          //synchronized锁方法则默认锁定的是this,即本对象container对相应的方法互斥调用
        //判断能否消费                            //synchronized锁代码块可以synchronized(this)锁本对象，也可synchronized(其它对象)
        while(count==0){ //把if换成while循环判断，可以实现多对多

            //等待生产者生产，消费者等待
            try {
                this.wait();  //wait();也可        //让操作到该对象该方法到这个位置的线程 ***** 在该位置等待*****，，wait()方法会释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果可以消费
        count--;
        Chicken chicken=chickens[count];

        //可以通知生产者生产
        this.notifyAll();   //notifyAll();也可          //唤醒所有因为操作到该对象而等待的线程
        return chicken;
    }
}


