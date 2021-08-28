package JVM;

import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.ArrayList;
import java.util.List;

//-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError

//java_pid13684.hprof 文件下载到了项目的根目录中
public class Test04 {
    byte[] array=new byte[1*1024*1024]; //1M

    public static void main(String[] args) {
        List<Test04> list=new ArrayList<>();
        int count=0;

        try{
            while(true){
                list.add(new Test04());  //问题所在
                count=count+1;
            }
        }catch(Error e){
            System.out.println("count:"+count);
            e.printStackTrace();
        }

    }
}
