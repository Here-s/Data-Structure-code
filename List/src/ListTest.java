import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListTest {

    
    public static void main(String[] args) {
        //add 方法 默认是放到数组的最后一个位置的
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        //放到第一个位置
        list.add(0,"Lockey");
        System.out.println(list);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("qwe");
        list1.add("rty");
        //将另一个 list 放到下一个
        list1.addAll(list);
        System.out.println(list1);
    }

    public static void main4(String[] args) {
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        list2.add("word");
        list2.add("123");

        System.out.println("迭代器 List 相关打印");
        ListIterator<String> i2 = list2.listIterator();
        while (i2.hasNext()) {
            String ret = i2.next();
            if (ret.equals("hello")) {
            i2.add("llllll");//放到 hello 的下一个地方
            } else {
                System.out.println(ret);//因为 i 指向空的一个 所以 next 就是下一个元素
            }
        }
        System.out.println(list2);
    }

    public static void main3(String[] args) {
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        list2.add("word");
        list2.add("123");
        Iterator<String> i = list2.iterator();
        //在删的时候 必须迭代出 集合当中的所有元素 然后才能再删除
        while (i.hasNext()) {
            String ret = i.next();
            if (ret.equals("hello")) {
                i.remove();
            }else {
                System.out.println(i.next());//因为 i 指向空的一个 所以 next 就是下一个元素
            }
        }
    }

    public static void main2(String[] args) {
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        list2.add("word");
        list2.add("123");
        System.out.println(list2);//把元素以字符串的形式输出
        System.out.println("====================");

        //通过 for 循环打印
        for (int i = 0; i < list2.size(); i++){
            System.out.println(list2.get(i));
        }

        //通过 foreach 打印
        for (String s:list2) {
            System.out.println(s);
        }

        //通过迭代器打印
        System.out.println("迭代器打印");
        Iterator<String> i = list2.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());//因为 i 指向空的一个 所以 next 就是下一个元素
        }

        //ListIterator 还具有 add  remove 方法
        System.out.println("迭代器 List 相关打印");
        ListIterator<String> i2 = list2.listIterator();
        while (i2.hasNext()) {
            System.out.println(i2.next());//因为 i 指向空的一个 所以 next 就是下一个元素
        }
    }

    //序列化：把一个对象，转变为字符串    json  Gson 都是转化为序列化用到的
    public static void main1(String[] args) {
        List<String> list1 = new ArrayList<>(20);//20 表示顺序表空间是 20

        //直接实现一个类    后面没有给容量（括号里可以加容量）
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        list2.add("word");
        list2.add("123");
        System.out.println(list2);

        //使用另外一个 Arraylist 对 list3 初始化
        ArrayList<String> list3 = new ArrayList<>(list2);
    }
}
