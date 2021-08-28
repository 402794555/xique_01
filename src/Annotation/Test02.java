package Annotation;
/*元注解

◆元注解的作用就是负责注解其他注解,Java定义了4个标准的meta-annotation类型他们被用来提供对其他annotation类型作说明.
◆这些类型和它们所支 持的类在java.lang.annotation包中可以找到.( @Target，@Retention，@Documented，@Inherited )

        ➢@Target :用于描述注解的使用范围(即被描述的注解可以用在什么地方)

        ➢@Retention :表示需要在什么级别保存该注释信息，用于描述注解的生命周期
            ➢(SOURCE < CLASS < RUNTIME)  一般是RUNTIME，即在源代码、字节码、运行期 都有效

        ➢@Document:说明该注解将被包含在javadoc中

        ➢@Inherited:说明子类可以继承父类中的该注解*/


import java.lang.annotation.*;


//测试元注解
public class Test02 {
    public static void main(String[] args) {

    }
}

//ElementType是枚举类型

//如果在前面import static java.lang.annotation.ElementType.*;
//则可以简化成@Target({METHOD,TYPE})

//import java.lang.annotation.Target;
//import java.lang.annotation.ElementType; 可以写value=  也可以不写
//如果只有一个参数，用value命名，那么调用时可以省略value=

//定义一个注解
//Target  表示我们的注解可以用在哪些地方。
@Target(value={ElementType.METHOD,ElementType.TYPE})

//Retention  表示我们的注解在什么地方有效(SOURCE < CLASS < RUNTIME)
@Retention(value = RetentionPolicy.RUNTIME)

//Documented 表示是否将我们的注解生成在javadoc中
@Documented

//Inherited 子类可以继承父类的注解
@Inherited
@interface MyAnnotation{//定义一个注解,类似于接口,多个@ （注意这里不能写public了，只能有一个public)


}
