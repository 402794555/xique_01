package Annotation;


/*什么是注解
◆Annotation是从JDK5.0开始引|入的新技术.
◆Annotation的作用:
        ➢不是程序本身,可以对程序作出解释.(这一-点和注释(comment)没什么区别)
        ➢可以被其他程序(比如:编译器等)读取.
◆Annotation的格式:
        ➢注解是以"@注释名"在代码中存在的,还可以添加一-些参数值, 例
        如:@SuppressWarnings(value="unchecked").
◆Annotation在哪里使用?
        ➢可以附加在package,class,method,field等上面,相当于给他们添加了额外的辅助信息,
        我们可以通过反射机制编程实现对这些元数据的访问*/

/*
内置注解

➢@Override :定义在java.lang.Override中，此注释只适用于修辞方法，表示一个方法声明打算 重写超类中的另一个方法声明.
➢@Deprecated :定义在java.lang.Deprecated中，此注释可以用于修辞方法,属性，类，表示不鼓励程序员使用这样的元素， 通常是因为它很危险或者存在更好的选择.
➢@SuppressWarnings :定义在java.lang.SuppressWarnings中,用来抑制编译时的警告信息.
    与前两个注释有所不同,你需要添加一个参数才能正确使用，这些参数都是已经定义好了的，我们选择性的使用就好了.

     @SuppressWarnings("all")
     @SuppressWarnings("unchecked")
     @SuppressWarnings(value={"unchecked","deprecation"})
    等等.....
*/


import java.util.ArrayList;
import java.util.List;



//什么是注解
public class Test01 extends Object{

    //@Override   重写的注解，被该注释注释后的方法一定要是重写超类的方法
    @Override
    public String toString() {
        return super.toString();
    }

    //@Deprecated  不推荐程序员使用或存在更好的方式，但是也可以使用，只是方法名会有横线
    @Deprecated
    public static void test(){
        System.out.println("Deprecated");
    }


    //@SuppressWarnings()   抑制警告注解，这个注解可以放在方法上还能放到类的上面
    @SuppressWarnings("all")
    public void test1(){
        List list=new ArrayList(); //本来list 有警告：Variable 'list' is never used
    }



    public static void main(String[] args) {

        test();     //我这没有横线是因为我IDEA设置过
    }
}
