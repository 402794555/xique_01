package MultiThread.Thread_Runnable;

//*****注意：其实Thread 与 Runnable子类 是装饰者模式 不是代理模式


//1，主类自己继承Thread/实现Runnable               (外部类)
//2，主类的成员内部类继承Thread/实现Runnable            (静态内部类)
//3，主类并列的实现类继承Thread/实现Runnable          (实现类)
//Test1-3都用第2种，Test4用的是匿名内部类(普通/lambda写法)

//Test1和Test2和Test3中 我们都需要一个 写一个继承Thread或者实现Runnable的类
//这个类可以是（主类自己）（主类的成员内部类）（主类并列的实现类）
//但是如果我们写的这个类只是用到一次，我们可以用（主类的局部内部类）形式，并写成匿名内部类

//内部类->(局部内部类，成员内部类，匿名内部类)  成员内部类->(静态内部类，非静态内部类)
//实现类->成员内部类->局部内部类->匿名内部类->lambda表达式

//普通匿名内部类   Runnable r=new Runnable(){含run方法体};  普通匿名内部类可以有多个方法
//JDK8前         new Thread(new Runnable(){含run的方法体});

//这里r是一个实现了Runnable接口的匿名内部类的对象

//lambda表达式，避免内部类定义过多，可以让代码看起来更简洁，去掉一些没有意义的代码，只留下核心的逻辑，其实质属于函数式编程的概念
//函数式接口：任何接口，若只有唯一个抽象方法，那么它就式函数式接口
//对于函数式接口，我们可以通过lambda表达式来创建该接口的对象
//Lambda表达式的一个重要用法是简化某些匿名内部类的写法，
//但Lambda表达式并不能取代所有的匿名内部类，只能用来取代函数接口的简写

//如果需要实现了函数式接口的类的对象，可以提供一个lambda表达式()->{}来实现匿名内部类
//Runnable是函数式接口（只有一个抽象方法的接口）
//lambda写法匿名内部类  Runnable r=()->{没有run，只有run外变量和run内方法体};
//JDK8(lambda写法)     new Thread(()->{没有run，只有run外变量和run内方法体});
//用() -> {}代码块替代了整个匿名类，函数式接口只有一个抽象方法， lambda写法匿名内部类不能有方法
//故可以不写它的方法声明和方法名，直接写该方法的方法体，还有接口内的变量
//普通匿名内部类和lambda写法匿名内部类 都不能有构造方法



public class Test4 {
    public static void main(String[] args) {

//1-------------------------------------------------------------
        //代理类代理自己的并列类（即另一个实现了Runnable的子类）
        Runnable r1=new Runnable() {            //Runnable  普通写法匿名内部类,与new Thread()分开写
            private int ticket = 5;
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "  Runnable ticket = " + ticket--);
                    if (ticket < 0) {
                        break;
                    }
                }
            }
        };
        Thread t1=new Thread(r1,"线程1");
        t1.start();

//2-------------------------------------------------------------

        //代理类代理自己的并列类（即另一个实现了Runnable的子类）
        Thread t2=new Thread(new Runnable(){   //Runnable 普通写法匿名内部类,与new Thread()合写
            private int ticket = 5;
            public void run(){
                while(true){
                    System.out.println(Thread.currentThread().getName()+"  Runnable ticket = " + ticket--);
                    if(ticket < 0){
                        break;
                    }
                }
            }
        },"线程2");
        t2.start();

//3-------------------------------------------------------------
        //idea会为重新分配过地址的局部变量加上下划线,提醒你这个变量是个事实不可变变量

        //代理类代理自己的并列类（即另一个实现了Runnable的子类）
        Runnable r2=()->{               //Runnable  lambda写法匿名内部类,与new Thread()分开写
            int ticket = 5;       //不能加private和public等权限控制
            while(true){      //ticket有下划线
                System.out.println(Thread.currentThread().getName()+"  Runnable ticket = " + ticket--);
                if(ticket < 0){
                    break;
                }
            }
        };
        Thread t3=new Thread(r2,"线程3");
        t3.start();

//4-------------------------------------------------------------
        //lambda写法匿名内部类 ,与new Thread()合写
        //虽然表面上不知道是不是传入了一个实现了Runnable接口的匿名内部类的对象
        //因为表面上可能是传入了一个实现了Thread类的匿名内部类的对象
        //但是这里就有一个方法所以一定是传入Runnable 而不是Test2中的Thread

        //代理类代理自己的并列类（即另一个实现了Runnable的子类）
        Thread t4=new Thread(()-> {  //Runnable lambda写法匿名内部类 ,与new Thread()合写
            int ticket = 5; 		//不能加private和public等权限控制
            while (true) {          //ticket有下划线
                System.out.println(Thread.currentThread().getName() + "  Runnable ticket = " + ticket--);
                if (ticket < 0) {
                    break;
                }
            }
        },"线程4");
        t4.start();

//5-------------------------------------------------------------

//Thread是普通类不是接口也不是抽象类，但是普通类也能写成普通匿名内部类
//可以和new Thread()分开写或者合在一起写
//但是不能弄成lambda写法（可能是因为不是接口）,故不能lambda写法匿名内部类与new Thread()分开写
//Runnable lambda写法匿名内部类，长相无法区分是Runnable还是Thread，但实际上是Runnable

        //代理类代理自己的子类(这个子类实际上具有把代理和真实的的功能已经能像Test1那样自己运行了，将这个子类写成匿名内部类)
        Thread t=new Thread(){            //Thread 普通写法匿名内部类,与new Thread()分开写
            private int ticket = 5;
            public void run(){
                while(true){
                    System.out.println(Thread.currentThread().getName()+"  Thread   ticket = " + ticket--);
                    if(ticket < 0){
                        break;
                    }
                }
            }
        };
        Thread t5=new Thread(t,"线程5");
        t5.start();

//6-------------------------------------------------------------

        //代理类代理自己的子类(这个子类实际上具有把代理和真实的的功能已经能像Test1那样自己运行了，将这个子类写成匿名内部类)
        Thread t6=new Thread(new Thread(){   //Thread 普通写法匿名内部类,与new Thread()合写
            private int ticket = 5;
            public void run(){
                while(true){
                    System.out.println(Thread.currentThread().getName()+"  Thread   ticket = " + ticket--);
                    if(ticket < 0){
                        break;
                    }
                }
            }
        },"线程6");
        t6.start();
    }
}
