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

        public OuterClass out = new OuterClass();

        public void test() {
            //data1 和 data2 调用的时候需要外部类的引用，所以这里访问不到
            //拿到外部类就可以了
            System.out.println(new OuterClass().data1);
            System.out.println(out.data1);
            System.out.println(out.data2);//通过 get set 方法也可以
            System.out.println(data3);
            System.out.println(data4);
            System.out.println(data5);
            System.out.println(data6);
            System.out.println("static class InnerClass::test");
        }
    }
}
public class TestDome2 {

    //静态内部类：
    // 1、如何实例化 静态内部类对象
    // 2、如何访问外部类的实例对象
    public static void main(String[] args) {
        OuterClass2.InnerClass innerClass = new OuterClass2.InnerClass();
        innerClass.test();
    }
}
