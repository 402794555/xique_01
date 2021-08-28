package MultiThread.state;

/*boolean是基本数据类型，Boolean是它的封装类，和其他类一样，有属性有方法可以new
例如：Boolean flag=new Boolean(true)  (已经被废弃)
Boolean 是boolean 的实例化对象类，和Integer对应int一样
自jdk1.5.0以上版本后，Boolean在"赋值"和判断上和boolean一样，
即是你： boolean b1 = true ; 或者 Boolean b2 = true ; 都可以。
唯一只能使用Boolean上的就是从列表或者哈希表获取值时。
比如 boolean t = false;
Map map = new HashMap();
map.put("t", t);
那么获取值时只能用
Boolean t1 = (Boolean) map.get(t); //前面只能用Boolean强制转换，不能使用boolean.*/


//测试stop
//1.建议线程正常停止-->利用次数，不建议死循环
//2.建议使用标志位-->设置一个标志位
//3.不要使用Thread类中的 stop或者destroy等过时或者JDK不建议使用的方法

public class TestStop  implements Runnable{

    //1.设置一个标志位
    boolean flag=true;
    @Override
    public void run() {
        int i=0;
        while(flag){      //检测是否调用了stop方法
            System.out.println("run.......Thread  "+i++);
        }
    }

    //2.设置一个公开的方法停止线程,转换标志位，真实对象控制自己什么时候正常结束
    public void stop(){   //这个是真实类里面自己写的stop，不是代理类Thread类里面的stop
        this.flag=false;
    }

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop=new TestStop();
        new Thread(testStop).start(); //将真实对象传给代理对象，并启动


        for (int i = 0; i < 1000; i++) {
            System.out.println("main  "+ i);   // if(i==0)Thread.sleep(7);或则把i<1000和i==900调大一点
                                              //不然可能只打印：线程该停止了-------，因为主线程跑太快一个时间片内直接跑到900，
                                             // 直接调用stop，当另一个线程拿到cpu权限时flag已经等于true了，啥也干不了直接结束
            if(i==900){
                //调用stop方法切换标志位，让线程停止 (即真实对象运行结束,即线程正常停止)
                testStop.stop();
                System.out.println("线程该停止了------------------");
            }
        }
    }
}
