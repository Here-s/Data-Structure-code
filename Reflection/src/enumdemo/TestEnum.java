package enumdemo;

public enum TestEnum {
    //这些是 枚举对象 直接放过来的就是枚举对象
    RED("red",1),BLACK("black",3),GREEN("green",4),WHITE("white",2);
    //提供一个带两个参数的构造方法，就可以在枚举常量里面赋值了
    public String color;
    public int ordinal;
    //自己写的构造方法，默认是私有的，所以在类外是拿不到对象的，只能在类内定义枚举常量
    TestEnum(String color, int ordinal) {
        this.color = color;
        this.ordinal = ordinal;
    }
    TestEnum() {

    }

    public static void main(String[] args) {

    }
    //枚举是方便将常量组织起来，因为 JDK1.5 之前是通过定义常量来引用的，但是如果恰好有一个常量的话，就会导致使用的是枚举类型
    //所以就有了枚举类型
    //Java 库当中的枚举 默认是一个抽象方法
    public static void main3(String[] args) {
        //valueOf 是把字符串变成对应的枚举对象，如果没有这个枚举常量的话，就会报错，这里枚举常量有 RED 所以不会报错
        TestEnum testEnum = TestEnum.valueOf("RED");
        System.out.println(testEnum);
        //这里的 CompareTo 是比较序列的，比较的是 ordinal
        System.out.println(RED.compareTo(GREEN));
        System.out.println(BLACK.compareTo(RED));

    }

    public static void main2(String[] args) {
        //values 方法就是一个数组
        TestEnum[] testEnums = TestEnum.values();//这里的 values 方法是一个匿名内部类
        for (int i = 0; i < testEnums.length; i++) {
            System.out.println(testEnums[i]+"->"+testEnums[i].ordinal());//加的东西就是枚举位置
        }
    }

    public static void main1(String[] args) {
        //可以直接调用枚举对象，如果枚举在类外的话，就要通过类来定义
        System.out.println(TestEnum.RED);
        System.out.println(RED);

        TestEnum testEnum2 = TestEnum.BLACK;
        //这里已经定义类型了，所以输出的就是 black
        switch (testEnum2) {
            case RED:
                System.out.println("red");
                break;
            case BLACK:
                System.out.println("black");
                break;
            case WHITE:
                System.out.println("WHITE");
                break;
            case GREEN:
                System.out.println("black");
                break;
            default:
                break;
        }
    }
}
