package reflectdemo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectClassDemo {

    /**
     * 通过 Class 类的 newInstance 方法获取学生实例
     */
    public static void reflectNewInstance() {
        //第一步先拿到对象

        try {
            Class<?> c1 = Class.forName("reflectdemo1.Student");
            //因为是 student 类型，所以用 student 来接收
            Student student = (Student)c1.newInstance();
            //通过 newInstance 方法来实例化出一个学生对象。就可以使用 student 的所有方法
            System.out.println(student);
            student.sleep();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射拿到私有/公开的构造方法
     */
    public static void reflectPrivateConstructor() {
        try {
            Class<?> c1 = Class.forName("reflectdemo1.Student");
            //获取私有的构造方法  因为 c1 的类型也是问号，所以前面也用问号
            Constructor<?> constructor = c1.getDeclaredConstructor(String.class,int.class);
            //使用的时候，要调用 setAccessible 方法
            constructor.setAccessible(true);//设置为 true 之后，就说明这个东西是可改变的.就是把私有的属性变得可访问了。
            Student student = (Student) constructor.newInstance("lll",18);
            System.out.println(student);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射获取私有/公开的属性
     */
    public static void reflectPrivateField() {
        try {
            Class<?> c1 = Class.forName("reflectdemo1.Student");
            Student student = (Student) c1.newInstance();
            Field field = c1.getDeclaredField("name");

            //只要是私有的，就要调用 setAccessible 来让它变为 true
            field.setAccessible(true);
            //获取之后，在通过 set 方法来修改值
            field.set(student,"ZhangSan");
            System.out.println(student);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取私有方法
     */
    public static void reflectPrivateMethod() {
        try {
            Class<?> c1 = Class.forName("reflectdemo1.Student");
            Student student = (Student) c1.newInstance();
            Method method = c1.getDeclaredMethod("function", String.class);
            method.setAccessible(true);
            method.invoke(student,"私有的方法的参数");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        reflectPrivateMethod();
    }
}
