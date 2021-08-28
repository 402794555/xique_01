package MultiThread.proxy_lambda;

//静态代理模式总结：
//真实类和代理类都要实现同一个接口（抽象角色）
//(我想这样设计的原因有两个一个是可以在代理类里面声明这个接口变量，然后用这个接口变量引用实现了这个接口的真实类的对象)
//(另一个是因为实现类一定要重写接口中的抽象方法，所以真实对象和代理对象至少有一个同名的方法，这样的话调用方法名一样，容易理解)
//代理角色要代理真实角色

//好处：
    //代理对象可以做很多真实对象做不了的事情
    //真实对象专注做自己的事情

//-----------------------------------------------------------------------------------------------------
//注解：在spring  IoC  代理类和真实类可以实现同一个接口，当然也可以不用实现同一个接口，只要在代理类中声明的是真实类的接口变量就好了，甚至声明一个真实类变量都可以
//真实对象的注入可以像静态代理这样用构造方法的形式注入，也可以用set方法注入，而两个不同的接口的抽象方法名最好一样这样方便理解，当然其实也可以不一样

//控制反转IoC是一种设计思想，依赖注入DI是实现IoC的一种方式，也有人认为DI只是IoC的另一种说法。
//没有IoC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编在程序中，对象的创建由程序自己控制。（类似一个类把代理和真实的事都干了）
//控制反转后将对象的创建转移给第三方，个人认为所谓控制控制反转就算：获取依对象的方式反转了（类似创建完真实对象，把它传入代理类中产出代理对象）
//-----------------------------------------------------------------------------------------------------



/*

  一、正常的代理情况

  ①代理类Thread代理了自己的并列类(该并列类从重写了Runnable接口中的run抽象方法，这个并列类是真实角色专注做自己的事)，
  可以做到将一个真实对象传给一个代理对象，多个真实对象传给多个代理对象，也可以做到将一个真实对象传给多个代理对象这样可以实现资源共享

  代理类Thread实现了Runnable接口(run方法体中就有this.target.run()，即运行传入对象的run方法，
  代理类Thread中还有其它方法例如start(),sleep(),yield(),join(),getName(),getId等等，即代理对象可以做很多真实对象做不了的事),

  一个真实类MyTread3也实现了Runnable接口(重写了run方法做了真实角色要做的事情，而且只有run方法，即真实角色专注做自己的事)
  将MyTread3的对象传入new Thread()中。 若是一传一的情况当然也可以用匿名内部类的方式，直接创建一个实现了Runnable接口的匿名内部类的对象，
  直接将这个对象传入new Thread()中。。。甚至还可以进一步用lambda表达式简化匿名内部类的写法即 ()->{}


    二、两种特殊的代理情况

    ②把代理类Thread转化为真实类，让这个真实类自己把代理角色和真实角色要做的事都干了，代理类Thread的子类并重写了run方法(真实角色要做的事情)，变成真实类(这个类没有代理其它类的能力了)
    这个具有完善功能的子类的对象，不仅有真实角色的要做的run方法,还有从Thread类继承来的代理角色要做的，例如start(),sleep(),yield(),join(),getName(),getId等等

    一个类MyTread1继承了Thread并从写了run方法(即真实角色要做的事情),这个MyTread1类把真实角色和代理角色要做的事都做了，直接new MyTread1().start()



    ③代理类Thread类代理被已经转化为真实类具有完善功能的Thread类子类，即可以将同一个具有完善功能的子类的对象传给多个代理对象，实现资源共享
     但是也有坏处，那就是这里的真实对象有些冗余过于强大了，它没有专注做自己的事情，它还有很多继承来的方法，若是为了资源共享用第一种代理方式就比较好了

    也可以将一个有了完善功能的MyTread2的对象传入new Thread()中。若是一传一的情况当然也可以用匿名内部类的方式，直接创建一个实现了Runnable接口的匿名内部类的对象，
   直接将这个对象传入new Thread()中。。。甚至还可以进一步用lambda表达式简化匿名内部类的写法即 ()->{}
*/




public class StaticProxy {
    public static void main(String[] args) {
        You you=new You(); //你要结婚。。  原来是：you.HappyMarry();
        WeddingCompany weddingCompany=new WeddingCompany(you);//把你给婚庆公司
        weddingCompany.HappyMarry();
        //上面三句等效于：new WeddingCompany(new You()).HappyMarry();
        //类似于new Thread(new Runnable(){含run的方法体}).start();  普通写法匿名内部类并与new Thread()合写

        //传入了一个实现了Runnable接口的匿名内部类的对象，Thread也实现了Runnable接口
        new Thread(()-> System.out.println("我爱你")).start();;

    }

}

//抽象角色，结婚
interface Marry{           //接口
    void HappyMarry();      //抽象方法
}



//真实角色，你去结婚
class You implements Marry{        //要实现接口，重写接口的抽象方法
    @Override
    public void HappyMarry() {
        System.out.println("肖某某要结婚了，他自己要做的事情是感到超开心");
    }
}


//代理角色，帮助你结婚
class WeddingCompany implements Marry{

    //代理谁-->真实目标角色
    private Marry target;  //声明一个接口变量，接口变量必须引用实现了这个接口的类对象
                            //不能使用new运算符实例化一个接口，例如x=new Marry(),错误

    public WeddingCompany(Marry target){ //代理类的构造方法，需要传入一个真实对象
        this.target=target;
    }



    @Override
    public void HappyMarry() {  //要实现接口，重写接口的抽象方法

        before(); //代理类中能够完成的其它功能

        this.target.HappyMarry(); //真实对象调用自己实现了的方法，真实对象自己要做的事情

        after(); //代理类中能够完成的其它功能
    }

    //代理类中能够完成的其它功能
    private void before() {
        System.out.println("结婚前，婚庆公司布置现场");
    }
    private void after() {
        System.out.println("结婚之后，婚庆公司收尾款");
    }
}