package MultiThread.state;


/*
public static enum Thread.State
        extends Enum<Thread.State>线程状态。 线程可以处于以下状态之一：
        NEW
        尚未启动的线程处于此状态。
        RUNNABLE
        在Java虚拟机中执行的线程处于此状态。
        BLOCKED
        被阻塞等待监视器锁定的线程处于此状态。
        WAITING
        正在等待另一个线程执行特定动作的线程处于此状态。
        TIMED_WAITING
        正在等待另一个线程执行动作达到指定等待时间的线程处于此状态。
        TERMINATED
        已退出的线程处于此状态。
        一个线程可以在给定时间点处于一个状态。 这些状态是不反映任何操作系统线程状态的虚拟机状态。
*/

//观察测试线程的状态
public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            for (int i = 0; i < 5; i++) {    //睡觉5秒钟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("/////////");
        });

        //观察状态
        Thread.State state=thread.getState();
        System.out.println(state); //NEW

        //观察启动后
        thread.start();//启动线程
        state=thread.getState();
        System.out.println(state); //RUNNABLE


        while(state!= Thread.State.TERMINATED){
            Thread.sleep(100);
            state=thread.getState();   //更新线程状态
            System.out.println(state);
        }




        //thread.start();  会报错，线程不能启动两次，死亡之后的线程就不能再次启动了


    }
}
