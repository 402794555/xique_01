package MultiThread.syn;


/*同步方法

◆由于我们可以通过private关键字来保证数据对象只能被方法访问,所以我们只需要针对方法提出一套机制,这套机制就是synchronized关键字,
 它包括两种用法:synchronized方法和synchronized块.
    同步方法: public synchronized void method(int args) {}

◆synchronized方法控制对“对象”的访问,每个对象对应一把锁,每个synchronized方法都必须获得调用该方法的对象的锁才能执行,
否则线程会阻塞，方法一旦执行,就独占该锁,直到该方法返回才释放锁,后面被阻塞的线程才能获得这个锁,继续执行
    缺陷:若将一个大的方法申明为synchronized将会影响效率

 */


/*同步块

◆同步块: synchronized(Obj){ }
◆Obj称之为同步监视器
        ◆Obj可以是任何对象,但是推荐使用共享资源作为同步监视器
        ◆同步方法中无需指定同步监视器,因为同步方法的同步监视器就是this ,就是这个对象本身,或者是class [反射中讲解]
◆同步监视器的执行过程
        1.第一个线程访问,锁定同步监视器,执行其中代码.
        2.第二个线程访问,发现同步监视器被锁定,无法访问.
        3.第一个线程访问完毕,解锁同步监视器.
        4.第二个线程访问,发现同步监视器没有锁,然后锁定并访问
*/

//不安全的取钱
//两个人区银行取钱，账户
public class UnsafeBank {
    public static void main(String[] args){
        //账户 (真实角色)
        Account account=new Account(1000,"结婚基金");
        Drawing you=new Drawing(account,50,"you");
        Drawing girlFriend=new Drawing(account,100,"girlFriend");

        you.start();
        girlFriend.start();

    }
}


//账户
class Account{
    int money; //余额
    String name; //卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread{

    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手里有多少钱

    //注意这里的name 是从Thread类中继承来的，这里父类和子类用同一个name字段
    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //synchronized 默认锁的是this
    //直接synchronized  run()方法是不行的，因为默认锁的是自己类对象即Drawing类对象
    //但是实际上修改的对象是Account类的account对象
    @Override
    public  void run() {
        synchronized (account){  //synchronized (this) 则不行
            //判断有没有钱
            if(account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+" 钱不够取不了 ");
                return;
            }

            //sleep可以方大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额=余额 - 你取的钱
            account.money=account.money-drawingMoney;
            //你手里的钱
            nowMoney=nowMoney+drawingMoney;

            System.out.println(account.name+" 余额为："+account.money);

            //注意这里的name 是从Thread类中继承来的，这里父类和子类用同一个name字段
            //所有 Thread.currentThread().getName()=this.getName()

            System.out.println(this.getName()+" 手里的钱："+nowMoney);

        }

    }

}