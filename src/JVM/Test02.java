package JVM;

//我的电脑内存：已安装16GB(16*1024=16384MB) 可使用总内存约15.372GB(15.372*1024=15741MB)   为硬件保留的内存有643MB

//Runtime.getRuntime().totalMemory() 内存不完全不准确的原因：其中一个survivor 区的内存没有计算入内，因为其中一个survivor区的内存必须为空

/*JVM的堆的内存， 是通过下面面两个参数控制的

        -Xms 最小堆的大小， 也就是当你的虚拟机启动后， 就会分配这么大的堆内存给你
        -Xmx 是最大堆的大小

        当最小堆占满后，会尝试进行GC，如果GC之后还不能得到足够的内存(GC未必会收集到所有当前可用内存)，
        分配新的对象，那么就会扩展堆，如果-Xmx设置的太小，扩展堆就会失败，导致OutOfMemoryError错误提示。

        实际上，细节不止于此， 堆还会被分成几个不同的区域，分别应用不同的GC算法*/

 //-Xms512m -Xmx1024m -XX:+PrintGCDetails

/*如果出现OOM：
     1.尝试扩大最大堆内存看看结果
     2.第一步不行说明代码有大问题，分析一下内存，看一下哪个地方出现了问题（专业工具）（使用JProfiler工具分析OOM原因）
          JProfiler作用：分析Dump内存文件，快速定位内存泄露，获得堆中的数据，获得大的对象

*/
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        //java虚拟机最多可以从操作系统获得的内存   (默认为电脑总内存的1/4(默认最大堆大小)  15741*1/4=3935.25MB)

        long max=Runtime.getRuntime().maxMemory();
        System.out.println("max="+max+"B\t"+(max/1024/1024.0)+"MB");

        //java虚拟机当前初始化了的内存   (最开始默认为电脑总内存的1/64(默认最小堆大小)  15741*1/64=245.95MB)
        //如果程序运行中需要更多的内存则会继续初始化，会变大
        long total=Runtime.getRuntime().totalMemory();
        System.out.println("total="+total+"B\t"+(total/1024/1024.0)+"MB");

        //java虚拟机当前初始化了的内存中还没有使用的部分
       long free =Runtime.getRuntime().freeMemory();
        System.out.println("free="+free+"B\t"+(free/1024/1024.0)+"MB");

        //java虚拟机当前初始化了的内存中已经使用了的部分
       long used=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("used="+used+"B\t"+(used/1024/1024.0)+"MB");



    }

}
