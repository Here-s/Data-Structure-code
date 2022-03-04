package enumdemo;

import java.lang.reflect.Constructor;

public class TestEnumReflect {

    //面试题：如何获取一个线程安全的单例模式。通过枚举来，很安全。单例模式：只能获取一个对象实例
    //枚举面试问题：枚举的构造方法是私有的，那么是否可以通过反射来获取到枚举对象的实例呢？不能，因为枚举的源码当中绕过了这个
    //如果是枚举的话，就不能获取实例

    //枚举非常安全，不能通过反射来获取到对象
    public static void reflectPrivateConstructor() {
        try {
            Class<?> classEnum = Class.forName("enumdemo.TestEnum");
//注意传入对应的参数,获得对应的构造方法来构造对象,当前枚举类是提供了两个参数分别是 String 和 int
            Constructor<?> dl = classEnum.getDeclaredConstructor(String.class,int.class,String.class,int.class);
//设置为true后可修改访问权限
            dl.setAccessible(true);
            //
            Object objectStudent = dl.newInstance("green",666,"Lockey",999);
            TestEnum testEnum = (TestEnum) objectStudent;
            System.out.println("获得枚举的私有构造函数："+testEnum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reflectPrivateConstructor();
    }

}
