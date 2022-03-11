interface A {

}
//这是一个普通的类：因为有了内部类，所以这个类就可以当作外部类
class OuterClass {
    public int data1 = 1;
    private int data2 = 2;
    public static int data3 = 3;

    //实例内部类，定义在类里面  可以看作外部类的实例成员
    //使用这个类是需要对象的
    //内部类也可以继承类，实现接口
    class InnerClass extends OuterClass {

        public int data1 = 9999;
        class Inner {
            //内部类类里面还可以写内部类，但是不要这样写，可读性差，会挨批
        }

        public int data4 = 4;
        private int data5 = 5;

        //内部类不能定义静态的，在实例方法里面也不能定义静态
        //public static int data6 = 6;//是属于类的，不属于对象
        public static final int data6 = 6;//可以定义静态的常量

        //因为是一个类，所以也能写构造方法
        public InnerClass() {
            System.out.println("不带参数的内部类的构造方法");
        }
        public void test() {
            System.out.println(OuterClass.this.data1);
            System.out.println(data1);
            System.out.println(data2);
            System.out.println(data3);
            System.out.println(data4);
            System.out.println(data5);
            System.out.println(data6);
            System.out.println("InnerClass::test");
        }
    }

    public void func1() {
        System.out.println("OuterClass::func1");
    }
}


class MyLinkedList {
    class Node {

    }
}

public class InnerClasses extends OuterClass.InnerClass {
    //要继承内部类，要写一个 super 的构造方法
    //只要是类，肯定会被继承的
    public InnerClasses(OuterClass out) {
        out.super();
    }

    //内部类：
    // 本地内部类：定义在方法里面的类，只能在当前方法当中使用。
    // 实例内部类：重要 ：在实力内部类当中，不能定义静态的成员变量，如果非要定义，子女定义一个静态的常量。
    //                 如何实例化一个内部类的对象,
    //                 一个类由什么组成，就可以设计成内部类。实例内部类当中，如果包含了和外部类同盟的成员变量，
    //                 如何访问外部类的成员变量。通过：外部类名.this.成员变量 。实例内部类当中可以包括两个 this
    //                 一个是外部类的 this ，一个是自己的 this 。
    // 静态内部类：重要 ：
    // 匿名内部类：重要 ：线程，接口，都用到匿名内部类。
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.func1();//可以调用内部类的方法

        //外部类类名.内部类类名，就可以拿到内部类的对象
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        //拿到内部类了，就可以直接调用内部类的方法了
        innerClass.test();
    }
}
