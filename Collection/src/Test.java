import java.util.Map;

//这个顺序表只能存放整型数据 不通用  我们要做的就是 让它什么都能放
class MyArrayList<E> {//加上 <E> 代表当前类是一个泛型类 E 就是一个占位符
    private E[] elem;
    private int usedSize;

    //数组和泛型直接的一个重要区别就是：它们如何强制执行类型检查。
    // 具体来说：数组在运行时存储和检查类型信息，然而，泛型在编译时检查类型错误
    // 并没有在运行时检查类型信息
    public MyArrayList() {
        //这里最正确的创建方式就是通过反射来创建 不是下面这种
        this.elem = (E[]) new Object[10];
    }

    public void add(E val) {
        this.elem[usedSize] = val;
        usedSize++;
    }

    public E get(int pos) {
        return this.elem[pos];
    }

    public Object[] getArray(int size) {
        Object[] genericArray = new Object[size];
        return genericArray;
    }
}
public class Test {


    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        //运行会报错 因为 数组和泛型直接的一个重要区别就是：它们如何强制执行类型检查。
        // 具体来说：数组在运行时存储和检查类型信息，然而，泛型在编译时检查类型错误
        // 并没有在运行时检查类型信息
        String[] ret = (String[]) myArrayList.getArray(10);
    }

    //面试问题：
    // 泛型是怎么编译的？
    // 1 泛型是编译时期的一种机制，擦除机制。 就是类型在编译的时候就变成了 Object

    //泛型中 <> 当中的内容不参与类型的组成。
    public static void main3(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        System.out.println(myArrayList);
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        System.out.println(myArrayList1);
        MyArrayList<Boolean> myArrayList2 = new MyArrayList<>();
        System.out.println(myArrayList2);
    }

    public static void main2(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        //泛型的意义：自动检查类型是否匹配 所以在 String 情况下 如果是 int 就会报错
        //          自动完成了强制类型的转换
        myArrayList.add("abc");
        myArrayList.add("ert");
        String ret = myArrayList.get(1);//get 得到的是下标
        System.out.println(ret);

        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        myArrayList1.add(1);
        myArrayList1.add(23);
        myArrayList1.add(45);
        int tmp = myArrayList1.get(1);
        System.out.println(tmp);
    }

    //泛型知识 了解为主 以看得懂源码为主
    //写一个通用的顺序表
    public static void main1(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        MyArrayList<Boolean> myArrayList2 = new MyArrayList<>();
    }
}
