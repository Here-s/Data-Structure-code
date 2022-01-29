<<<<<<< HEAD
public class Test {


    //阿里面试题：下面的输出是否相等 为什么
    public static void main(String[] args) {
        //遇到问题 找焦点 找最主要的地方
        Integer a = 129;
        Integer b = 129;
        System.out.println(a == b);//123 为 true   129 为 false
        //因为底层是 valueOf 所以找到出发点 从 valueOf 里面看 发现范围是 [-128……127]
        //因为 129 超过了下面的范围，所以又会 new 一个对象 所以 new 了对象之后 就是 false 了
    }

    //装箱 和 拆箱 （装包 和 拆包）
    //装箱：把简单类型变成包装类类型的数据
    //拆箱：把包装类类型的数据变成简单类型
    public static void main3(String[] args) {
        //装箱
        Integer a = 123;
        //拆箱
        int b = a;//隐式的：因为把 Integer 变为 int 了
        System.out.println(a+"   "+b);

//        System.out.println("=====================");

        //这里就是显式的装包
        Integer a2 = Integer.valueOf(123);
        Integer a3 = new Integer(123);

        //显式的拆包    显式：一眼看明白的
        int b2 = a2.intValue();
        double d = a2.doubleValue();
    }

    //八种包装类 int 是 Integer   char 是 Character  String 不是包装类 就是一个普通的类
    public static void main2(String[] args) {
        String str = "123";
        int ret = Integer.valueOf(str);
        System.out.println(ret+1);
    }

    public static void main1(String[] args) {
        //因为 Object 是父类 所以可以赋给  而 Object[] 和 String[] 是同一级的
        String[] s= new String[10];
        Object o = new String[10];
        Object[] objects = new String[10];
    }
}
=======
public class Test {


    //阿里面试题：下面的输出是否相等 为什么
    public static void main(String[] args) {
        //遇到问题 找焦点 找最主要的地方
        Integer a = 129;
        Integer b = 129;
        System.out.println(a == b);//123 为 true   129 为 false
        //因为底层是 valueOf 所以找到出发点 从 valueOf 里面看 发现范围是 [-128……127]
        //因为 129 超过了下面的范围，所以又会 new 一个对象 所以 new 了对象之后 就是 false 了
    }

    //装箱 和 拆箱 （装包 和 拆包）
    //装箱：把简单类型变成包装类类型的数据
    //拆箱：把包装类类型的数据变成简单类型
    public static void main3(String[] args) {
        //装箱
        Integer a = 123;
        //拆箱
        int b = a;//隐式的：因为把 Integer 变为 int 了
        System.out.println(a+"   "+b);

//        System.out.println("=====================");

        //这里就是显式的装包
        Integer a2 = Integer.valueOf(123);
        Integer a3 = new Integer(123);

        //显式的拆包    显式：一眼看明白的
        int b2 = a2.intValue();
        double d = a2.doubleValue();
    }

    //八种包装类 int 是 Integer   char 是 Character  String 不是包装类 就是一个普通的类
    public static void main2(String[] args) {
        String str = "123";
        int ret = Integer.valueOf(str);
        System.out.println(ret+1);
    }

    public static void main1(String[] args) {
        //因为 Object 是父类 所以可以赋给  而 Object[] 和 String[] 是同一级的
        String[] s= new String[10];
        Object o = new String[10];
        Object[] objects = new String[10];
    }
}
>>>>>>> efeba3a3c9e0556917c3015480684b51c9c396d6
