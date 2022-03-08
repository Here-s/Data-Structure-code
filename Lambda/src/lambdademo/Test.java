package lambdademo;


import java.util.PriorityQueue;

//注解证明这个一个函数式接口
@FunctionalInterface
//函数式接口，是一个抽象方法。里面写一个方法就行了
//没参数，没返回值
interface NoParameterNoreturn {
    void test();
}
//无返回值一个参数
@FunctionalInterface
interface OneParameterNoReturn {
    void test(int a);
}
//无返回值多个参数
@FunctionalInterface
interface MoreParameterNoReturn {
    void test(int a,int b);
}
//有返回值无参数
@FunctionalInterface
interface NoParameterReturn {
    int test();
}
//有返回值一个参数
@FunctionalInterface
interface OneParameterReturn {
    int test(int a);
}
//有返回值多参数
@FunctionalInterface
interface MoreParameterReturn {
    int test(int a,int b);
}

public class Test {

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(3,(o1,o2)->o1-o2);

    }

    public static void main4(String[] args) {
        NoParameterReturn noParameterReturn = ()->{return 10;};
        NoParameterReturn noParameterReturn2 = ()->10;
        int ret = noParameterReturn2.test();
        System.out.println(ret);

        OneParameterReturn oneParameterReturn = (a)->a+11;
        System.out.println(oneParameterReturn.test(10));

        MoreParameterReturn moreParameterReturn = (a,b)->a+b;
        System.out.println(moreParameterReturn.test(20, 30));
    }

    public static void main3(String[] args) {
        int size = 10;
        OneParameterNoReturn oneParameterNoReturn = (a)-> {
            System.out.println(a);
            //因为 size 未被修改，所以这里可以捕获到这个变量
            System.out.println(size);
        };
        oneParameterNoReturn.test(10);
        //代码可以优化，只有一个参数的话，小括号可以省略
        OneParameterNoReturn oneParameterNoReturn2 = a-> {System.out.println(a);};
        oneParameterNoReturn2.test(20);
        //最简形式
        OneParameterNoReturn oneParameterNoReturn3 = System.out::println;
        oneParameterNoReturn3.test(10);

        MoreParameterNoReturn moreParameterNoReturn = (a,b)->{System.out.println(a+b);};
        moreParameterNoReturn.test(10,20);
        MoreParameterNoReturn moreParameterNoReturn2 = (int a,int b)->{System.out.println(a+b);};
        moreParameterNoReturn2.test(30,20);
        MoreParameterNoReturn moreParameterNoReturn3 = (int a,int b)->System.out.println(a+b);
        moreParameterNoReturn3.test(20,20);

    }

    public static void main2(String[] args) {
        //这里就是利用 lambda 表达式简化代码了
        NoParameterNoreturn noParameterNoreturn = ()->System.out.println("重写方法");
        //也可以调用方法
        noParameterNoreturn.test();
    }

    public static void main1(String[] args) {
        //在 new 一个接口之后，重写方法
        NoParameterNoreturn noParameterNoreturn = new NoParameterNoreturn() {
            @Override
            public void test() {
                System.out.println("重写方法");
            }
        };
        //这里就可以调用 test 方法，通过 new 一个类来调用方法
        noParameterNoreturn.test();
    }
}
