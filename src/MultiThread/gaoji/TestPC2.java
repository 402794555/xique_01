package MultiThread.gaoji;


//线程自建的通信
//测试生产者消费者问题2：信号灯，标志位解决
public class TestPC2 {
    public static void main(String[] args) {
        TV tv=new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者-->演员,,,具有代理TV的能力
class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv=tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                this.tv.play("快乐大本营播放中");
                //System.out.println("----"+i);  //play()与watch()方法互斥了，但是其它代码块没
            }else{
                this.tv.play("抖音:记录美好生活");
                //System.out.println("----"+i);  //play()与watch()方法互斥了，但是其它代码块没
            }
        }
    }
}

//消费者-->观众,,,具有代理TV的能力
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv=tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
            //System.out.println(i+"------");  //play()与watch()方法互斥了，但是其它代码块没
        }
    }
}

//产品-->节目
class TV{              //演员表演，观众等待 T。观众观看，演员等待 F

    String voice;     //表演的节目(这里可以认为是容器大小为1的容器)
    boolean flag=true; //标志位

    //表演
    public synchronized void play(String voice){
        if(!flag){
            try{
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了："+voice);     //TestPC缓冲区类中没有打印，这里其实也可以设计到Player类中打印

        //通知观众观看
        this.notifyAll();
        this.voice=voice;
        this.flag=!this.flag;

    }

    //观看
    public synchronized void watch(){
        if(flag){
            try{
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("观众观看了："+voice);   //TestPC缓冲区类中没有打印，这里其实也可以设计到Watcher类中打印

        //通知观众观看
        this.notifyAll();
        this.flag=!this.flag;
    }
}