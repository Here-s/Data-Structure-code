import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

class OuterClass2 {
    public int data1 = 1;
    private int data2 = 2;
    public static int data3 = 3;

    //静态内部类
    static class InnerClass {
        public int data4 = 4;
        private int data5 = 5;
        //因为是静态内部类，所以可以直接定义静态方法
        public static int data6 = 6;

        //new 一个对象来完成
        public OuterClass out2 = new OuterClass();

        //通过写构造方法也可以
        public OuterClass2 out;
        public InnerClass(OuterClass2 out) {
            this.out = out;
        }

        public InnerClass() {

        }

        public void test() {
            //data1 和 data2 调用的时候需要外部类的引用，所以这里访问不到
            //拿到外部类就可以了
            System.out.println(new OuterClass2().data1);
            System.out.println(out.data1);
            //System.out.println(out.data2);//通过 get set 方法可以拿到，因为是 private 修饰的
            System.out.println(data3);
            System.out.println(data4);
            System.out.println(data5);
            System.out.println(data6);
            System.out.println("static class InnerClass::test");
        }
    }
}
//匿名内部类
class Test {
    public void test() {
        System.out.println("test()haha");
    }
}
public class TestDome2 {

    public static void main(String[] args) {
        //这就是一个匿名的内部类了
        new Test() {
            @Override
            public void test() {
                System.out.println("重写之后的 test 方法");
            }
        }.test();//重写之后的话，这里的 test 调用的是重写之后的方法
        PriorityQueue<Integer> prio = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });//后面的花括号，就是类实现了 Comparator 方法
    }

    //实例化 静态内部类 对象的时候，可以没有外部类对象
    public static void main2(String[] args) {
        //不用外部类对象也可以
        OuterClass2.InnerClass innerClass = new OuterClass2.InnerClass();
    }

    //静态内部类：
    // 1、如何实例化 静态内部类对象：1、通过 new 一个外部类，然后去访问
    // 2、如何访问外部类的普通的成员变量
    public static void main1(String[] args) {
        OuterClass2 o = new OuterClass2();
        OuterClass2.InnerClass innerClass = new OuterClass2.InnerClass(o);
        innerClass.test();
    }
}
