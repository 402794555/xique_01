package MultiThread.demo01;


//两个线程同时执行一个对象，这个对象的run要求运行for0-100,谁先跑完这个则会将winner赋值，这样flag就为true了，其它线程也将结束
//虽然这里看上去是两个线程同时执行一个对象，但是由于对象里面实际上没什么共享的资源，所以效果和两个线程同时执行两个对象差不多
public class Runnable_Race implements Runnable{  //主类自己实现Runnable
    private static String winner;
    public void run(){
        for (int i = 0; i < 101; i++) {
            if(Thread.currentThread().getName().equals("兔子")&&i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag=gameOver(i);
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
        }
    }

    private boolean gameOver(int steps){
        if(winner!=null){
            return true;
        }
        if(steps>=100){
            winner=Thread.currentThread().getName();
            System.out.println("胜者为："+winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Runnable r=new Runnable_Race();

        new Thread(r,"乌龟").start();
        new Thread(r,"兔子").start();
    }
}