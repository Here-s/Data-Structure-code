import java.util.*;

public class CollectionTest {


    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(10,"宋江");
        map.put(3,"鲁班锁");
        System.out.println(map);

        //Map 也是会排序的 输入之后就自动按照 key 来排序了
        Map<String,String> map2 = new HashMap<>();
        map2.put("efg","宋江");
        map2.put("abc","鲁班锁");
        System.out.println(map2);

        //Tree 在放入数据的时候并没有排序
        Map<String,String> map3 = new TreeMap<>();
        map3.put("及时雨","宋江");
        map3.put("国民女神","鲁班锁");
        System.out.println(map3);

        Map<String,String> map4 = new HashMap<>();
        map4.put("及时雨","宋江");
        map4.put("国民女神","鲁班锁");
        System.out.println(map4);
    }

    public static void main5(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("及时雨","宋江");
        map.put("Lockey","鲁班锁");
        //打印出来的是  先打印 Lockey  再打印 及时雨
        //因为 哈希表存储的时候 是通过映射去存储的 可能并不是连续的 可能一个在前 一个在后
        //所以输出的时候 就有了一个在前 一个在后
        System.out.println(map);
        System.out.println("=======================");
        Set<Map.Entry<String,String>> entrySet = map.entrySet();
        for (Map.Entry<String,String> entry:entrySet) {
            System.out.println("key "+entry.getKey()+" value"+entry.getValue());
        }
    }

    //Map 后面的 <> 里面是 key，value 可以设置类型
    public static void main4(String[] args) {
        Map<String,String> map = new HashMap<>();
        //put 就是往里面放元素
        map.put("Lockey","鲁班锁");
        map.put("及时雨","宋江");

        //通过 get 可以通过 map 的前一个，拿到后一个元素
        String ret = map.get("及时雨");
        String tmp = map.getOrDefault("及时雨1","FM850");
        //如果没有第一个元素的话 就返回后面的 FM850
        System.out.println(ret);

        boolean flag = map.containsKey("Lockey");
        System.out.println(flag);
    }

    public static void main3(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("word");
        System.out.println(collection);
        System.out.println(collection.size());
        System.out.println(collection.isEmpty());
        collection.remove("word");
        System.out.println(collection);
        Object[] objects = collection.toArray();
        System.out.println(Arrays.toString(objects));
        System.out.println(objects);
        //String 会报错 因为涉及到 JVM 对数组的处理， 对于数组 不建议进行整体的进行强制类型的转换
        //Object 转换为其他类型时 暂且理解为：内部的元素 并没有全部进行转换
//        String[] str = (String[]) collection.toArray();
    }

    public static void main2(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("word");
        System.out.println(collection);
        collection.clear();
        System.out.println("===================");
        System.out.println(collection);
        System.out.println(collection.isEmpty());
    }

    //集合框架及其背后的数据结构
    //集合框架：就是Java给写好的一些数据结构 是在 util 包里面
    //有接口 interface 和实现类 classes

    //Collection 接口  想放啥都行 因为这里太广了
    public static void main1(String[] args) {
        Collection<String> collection = new ArrayList<String>();//后面的 String 可以不写
        //写上 <>(泛型) 里面限定类型之后 就只能放那个类型的数据了。就只能 add 泛型的类型
        collection.add("hello");
        collection.add("word");
        System.out.println(collection);

        //<> 里面的类型 只能是类类型 不能是基本类型 比如说整型 放 Integer
        Collection<Integer> collection1 = new ArrayList<>();
        collection1.add(1);
        collection1.add(2);
    }
}
