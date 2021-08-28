package Annotation;

/*自定义注解

➢使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口

➢分析:
    @interface用来声明一个注解，格式: public @interface注解名{定义内容}
    其中的每一个方法实际上是声明了一个配置参数.
    方法的名称就是参数的名称.
    返回值类型就是参数的类型(返回值只能是基本类型,Class，String，enum ).
    可以通过default来声明参数的默认值.
    如果只有一个参数成员，一般参数名为value
    注解元素必须要有值，我们定义注解元素时，经常使用空字符串,0作为默认值.*/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
public class Test03 {
    public static void main(String[] args) {

    }

    //注解可以显式赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation2(name="擎天",schools = {"西北大学","西工大"}) //参数可以无序
    public void test(){}

    @MyAnnotation3("秦疆")   //如果只有一个参数，用value命名，那么调用时可以省略value=
    public void test2(){}
}


@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解的参数：参数类型 + 参数名();
    String name();  //有参数，使用注解时要传入参数
    //String name() default ""; 设置默认为空
    int age() default 0;
    int id() default -1;// 如果默认值为为-1，代表不存在，indexof,如果找不到就返回-1
    String[] schools();
    //String[] schools() default {"西部开源","清华大学"};
}

@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value(); //如果只有一个参数，建议用value命名
                     //如果只有一个参数，用value命名，那么调用时可以省略value=
}
