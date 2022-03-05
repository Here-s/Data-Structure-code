package lambdademo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Testd {
    public void func() {
        System.out.println("func()");
    }
}

//变量捕获
public class Test2 {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "hello");
        map.put(2, "bit");
        map.put(3, "hello");
        map.put(4, "lambda");
        map.forEach(new BiConsumer<Integer, String>(){
            @Override
            public void accept(Integer k, String v){
                System.out.println(k + "=" + v);
            }
        });
        map.forEach((key,value)-> System.out.println("key："+key+"value："+value));
    }

    public static void main2(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("bit");
        list.add("hello");
        list.add("lambda");
        //集合的方法来遍历输出
        list.forEach(new Consumer<String>(){
            @Override
            public void accept(String str){
//简单遍历集合中的元素。
                System.out.print(str+" ");
            }
        });

        //lambda 表达式来输出 list 内容
        list.forEach(s-> System.out.println(s));
        System.out.println("+===================+");

        list.sort(((o1, o2) -> o1.compareTo(o2)));
    }

    public static void main1(String[] args) {
        int a = 100;
        //a = 99;
        new Testd() {
            @Override
            //能够捕获的：要么是常量，要么是没被修改过的
            public void func() {
                System.out.println("内部类，且重写func方法");
                //能捕获到的前提是 a 不发生修改，修改之后就报错了
                System.out.println("捕获变量"+a);
            }
        }.func();

    }
}
