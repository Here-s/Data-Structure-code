import java.time.chrono.MinguoDate;
import java.util.*;

class MyArrayList<E> {
    private Object[] elementData;//代表数组 未分配内存
    private int usedSize;//代表有效的数据个数

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    //对参数进行判断
    public MyArrayList(int capacity) throws IllegalAccessException {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = new Object[0];
        } else {
            throw new IllegalAccessException("初始化的容量不能为负数");
        }
    }

    /**
     * E 类型的元素
     * 添加元素 相当于存放到了数组的最后位置
     * @param e
     * @return
     */
    public boolean add(E e) {
        //确定一个真正的容量  预测->扩容 [把检查顺序表空和满和扩容放在一起]
        ensureCapacityInternal(usedSize+1);//走完这个才确定完真正的容量大小
        elementData[usedSize] = e;
        usedSize++;
        return true;
    }

    private  void ensureCapacityInternal(int minCapacity) {
        //计算出所需要的容量
        int capacity = calculateCapacity(elementData,minCapacity);
        //拿着计算出的容量去看  满了：扩容   空的：扩容
        ensureExplicitCapacity(capacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        //进了 if 语句 说明要扩容了  如果进不去 说明数组还没有放满
        if (minCapacity - elementData.length > 0) {
            //扩容
            grow(minCapacity);
        }
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            //说明数组所需容量很大
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private static int calculateCapacity(Object[] elementData,int minCapacity) {
        //先判断是否之前分配过 elementData 数组的大小
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(10,minCapacity);
        }
        //分配过 就返回 +1 后的值
        return minCapacity;
    }


    //给 index 位置添加元素
    public void add(int index, E e) {
        //检查下标是否合法
        rangeCheckForAdd(index);
        //确定真实的容量
        ensureCapacityInternal(usedSize+1);
        //挪数据
        copy(index,e);
        usedSize++;
    }
    private void copy(int index,E e) {
        for (int i = usedSize - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = e;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index 位置不合法，不能插入");
        }
    }

    /**
     * 获取顺序表的大小
     * @return
     */
    public int size() {
        return this.usedSize;
    }
}
public class ListTest {

    
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("ab");
        list.add("c");
        list.add("def");
        list.add(0,"ooooooo");
        System.out.println("hhhhhh");
    }

    public static void main11(String[] args) {
        //初始的大小是 0 因为底层 new 的数组大小是 0 但添加第一个元素的时候 数组的大小就被操作为 10 了
        ArrayList<String> list1 = new ArrayList<>();
        //数组大小是 0 存放元素的时候可以成功存放 是因为每次存放的时候 都会确认一下真正的容量是多少
        //结论：如果 ArrayList 调用不带参数的构造方法，那么顺序表的大小是 0 当第一次 add 的时候
            //整个顺序表才变为了 10 当这 10 个放满了 开始扩容 以 1.5 倍的方式进行扩容
        //如果调用的是给定容量的构造方法 那么顺序表的大小就是给定的容量 放慢了还是 1.5 倍扩容
        list1.add("haha");
        System.out.println(list1);

        //初始的大小是 13
        ArrayList<String> list2 = new ArrayList<>(13);
    }

    public static void main10(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list);
        //contains 是否包含某个元素
        System.out.println(list.contains("c"));
        //indexOf 返回元素的下标 最后一个元素的下标
        System.out.println(list.indexOf("c"));
        //subList 截取
        List<String> sub = list.subList(1,4);//从 1 下标截取 截取 3 个
        //因为是左闭右开 所以是三个
        System.out.println(sub);
        System.out.println(list);

        sub.set(0,"q");//把 sub 截取的 0 下标更改为 q
        System.out.println(sub);
        //发现修改之后 把 list 的也修改了 所以这里是直接把地址给到了 sub
        System.out.println(list);
    }

    //还可以这样删除
    public static void main9(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        boolean flag = list.remove("a");
        System.out.println(flag);
        System.out.println(list);

        //获取某个下标的值
        String ret = list.get(1);
        System.out.println(ret);
        //更新某个位置的值
        list.set(1,"d");
        System.out.println(list);
        //清空
        list.clear();
        System.out.println(list);
    }

    public static void main8(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        //remove 是把移掉的元素放到一个新的字符串里面
        String ret = list.remove(0);
        System.out.println(ret);
        System.out.println(list);
    }

    public static void main7(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        //add 方法 默认是放在数组的最后一个位置的
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        //在某个位置放入元素
        list.add(0,"hhh");
        System.out.println(list);

        //还可以放入另外一个 list
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("测试机");
        list.addAll(list1);//把 list1 整体放到 list 里面 不过都是放在最后
        //就是把 整个 list 当成元素放进来
        System.out.println(list);
    }

    public static void main6(String[] args) {
        //这里是一个线程安全的 适用于多线程
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> i2 = list.listIterator();
        while (i2.hasNext()) {
            String ret = i2.next();
            if (ret.equals("a")) {
                i2.add("llllll");
            } else {
                System.out.println(ret);
            }
        }
        System.out.println(list);
    }

    public static void main5(String[] args) {
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
