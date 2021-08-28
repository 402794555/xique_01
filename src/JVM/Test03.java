package JVM;

//-Xms512m -Xmx1024m
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("max:" + Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("total:" +Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("free:" +Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("used:"+(Runtime.getRuntime().totalMemory()/1024/1024-Runtime.getRuntime().freeMemory()/1024/1024)+"MB");
        System.out.println("=============");

        Thread.sleep(3000);

        String[] a = new String[20000000];

        System.out.println("max:" + Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("total:" +Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("free:" +Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("used:"+(Runtime.getRuntime().totalMemory()/1024/1024-Runtime.getRuntime().freeMemory()/1024/1024)+"MB");
        System.out.println("=============");

        Thread.sleep(3000);

        for (int i = 0; i < 2000000; i++) {
            a[i] = new String("aaa");
        }

        System.out.println("max:" + Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("total:" +Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("free:" +Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("used:"+(Runtime.getRuntime().totalMemory()/1024/1024-Runtime.getRuntime().freeMemory()/1024/1024)+"MB");
        System.out.println("=============");

        Thread.sleep(3000);


        String[] b = new String[200000000];
        for (int i = 0; i < 2000000; i++) {
            b[i] = new String("bbb");
        }
        System.out.println("max:" + Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("total:" +Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("free:" +Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("used:"+(Runtime.getRuntime().totalMemory()/1024/1024-Runtime.getRuntime().freeMemory()/1024/1024)+"MB");
        System.out.println("=============");
    }
}
