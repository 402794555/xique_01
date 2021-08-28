package MultiThread.proxy_lambda;



//lambda表达式，避免内部类定义过多，可以让代码看起来更简洁，去掉一些没有意义的代码，只留下核心的逻辑，其实质属于函数式编程的概念
//函数式接口：任何接口，若只有唯一个抽象方法，那么它就式函数式接口
//对于函数式接口，我们可以通过lambda表达式来创建该接口的对象
//Lambda表达式的一个重要用法是简化某些匿名内部类的写法，
//但Lambda表达式并不能取代所有的匿名内部类，只能用来取代函数接口的简写


/*非静态内部类：
        1.非静态内部类必须寄存在一个外部类对象里。因此，如果有一个非静态内部类对象那么一定存在对应的外部类对象。
          非静态内部类对象单独属于外部类的某个对象
        2.非静态内部类可以直接访问外部类的成员，但是外部类不能直接访问非静态内部类成员
        3.非静态内部类不能有静态方法、静态属性、静态初始化块
        4.外部类的静态方法、静态代码块不能访问非静态内部类，包括不能使用非静态内部类定义变量、创建实例*/




public class TestLambda {

    //内部类->(匿名内部类，局部内部类，成员内部类)  成员内部类->(静态内部类，非静态内部类)

    //2.静态内部类
    static class Like2 implements ILike{ //要在main方法中实例化这个类对象就一定要加static，要是静态内部类
        @Override                       //非静态内部类必须寄存在一个外部类对象里(TestLambda 类的对象)
        public void lambda() {
            System.out.println("i like lambda 2");
        }
    }


    public static void main(String[] args) {
        ILike like1=new Like1();    //也可Like1 like1=new Like1();
        like1.lambda();             //可用父类或上层接口来声明，然后才能引用一个子类实例对象

        ILike like2=new Like2();
        like2.lambda();

        //Like3 like3=new Like3(); // 局部内部类不能在前面声明，也不能在前面实例化对象


        //3.局部内部类    ，方法里面的类叫局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println("i like lambda 3");
            }
        }
        ILike like3=new Like3();
        like3.lambda();



        //4.匿名内部类，没有类的名称，必须借助接口或者父类
        ILike like4=new ILike(){
            @Override
            public void lambda() {
                System.out.println("i like lambda 4");
            }
        }; //这是一条语句，要加分号
        like4.lambda();


        //用lambda()来简化
        ILike like5=()->{
            System.out.println("i like lambda 5");
        }; //这是一条语句，要加分号
        like5.lambda();



    }
}


//0.定义一个函数式接口
interface ILike{
    void lambda();
}
//1.实现类（外部类）
class Like1 implements ILike{
    @Override
    public void lambda() {
        System.out.println("i like lambda 1");
    }
}