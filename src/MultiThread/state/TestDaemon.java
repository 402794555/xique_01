package MultiThread.state;

//线程分为用户线程和守护线程
//虚拟机必须确保用户线程执行完毕
//虚拟机不用等待守护线程执行完毕
//如：后台记录操作日志，监控内存，垃圾回收等

//测试守护线程
//上帝守护你
public class TestDaemon {
    public static void main(String[] args) {
        God god=new God();
        You you=new You();

        Thread thread=new Thread(god);
        thread.setDaemon(true); //默认事false表示用户线程，正常的线程都是用户线程....

        thread.start();//上帝 守护线程启动

        new Thread(you).start();//你 用户线程启动....


    }




}

//上帝
class God implements Runnable{
    @Override
    public void run() {
        int i=0;
        while(true){
            System.out.println("上帝保佑着你  "+i++);
        }
    }
}

//你
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都很开心  "+i);
        }
        System.out.println("-==============goodbye! world=============");
    }
}
