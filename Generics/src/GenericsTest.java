import java.util.ArrayList;
import java.util.List;

class MyArray1 {
    public Object[] objects = new Object[10];

    public void set(int pos, Object val) {
        objects[pos] = val;
    }
    public Object get(int pos) {
        return objects[pos];
    }
}

//没有指定边界，默认 Object
class MyArray<T> {//<T> 表示泛型类
    //public T[] objects = new T[10];//ERROR 不能实例化泛型类型的数组
    public T[] objects = (T[]) new Object[10];
    public void set(int pos, T val) {
        objects[pos] = val;
    }
    public T get(int pos) {
        return objects[pos];
    }
    //这个数组什么都可以放
    public T[] getArray() {
        return objects;
    }
}

//泛型的上界，只有上界，没有下界
class MyArray2<T extends Number> {
    //这里的上界就是 Number
    public T[] objects = (T[]) new Object[10];
    public void set(int pos, T val) {
        objects[pos] = val;
    }
    public T get(int pos) {
        return objects[pos];
    }
    public T[] getArray() {
        return objects;
    }
}

//写一个泛型类，求出数组当中的最大值
class Alg<T extends Comparable<T>> {
    //指定上界，表示：此时传入的 T 一定要实现这个接口，泛型的上界
    //extends 是拓展
    public T findMax(T[] array) {
        T max = array[0];
        for (int i = 0; i < array.length; i++) {
            //这里报错是因为：泛型传递的时候都是引用类型，所以要实现 Comparable 接口
//            if (max < array[i]) {
//                max = array[i];
//            }
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }
}

class Alg2 {
    public static  <T extends Comparable> T findMax(T[] array) {
        T max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }
}

class Alg3 {
    public static <T> void print1(ArrayList<T> list) {
        for (T x:list) {
            System.out.println(x);
        }
    }

    //不知道 ArrayList 当中放的是什么，但是都能匹配上。因为用了通配符
    public static void print2(ArrayList<?> list) {
        for (Object x:list) {
            System.out.println(x);
        }
    }
}

//通配符的上界。可以使用通配符来确定父子类关系
class up {
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Double> arrayList2 = new ArrayList<>();
    List<? extends Number> list = arrayList1;
    Number a = list.get(0);
}

//通配符的下界：使用 super 。就是可以引用：父类
//MyArrayList<? super Integer> 是 MyArrayList<Integer>的父类类型
//MyArrayList<?> 是 MyArrayList<? super Integer>的父类类型

class down {

}
//下界是不允许读取，
class Person {

}
class Student extends Person{

}
class C extends Student {

}
public class GenericsTest {

    public static void main(String[] args) {
        ArrayList<? super Person> arrayList1 = new ArrayList<Person>();
        //因为只能引用父类，所以
        //ArrayList<? super Person> arrayList = new ArrayList<Student>();
        arrayList1.add(new Person());
        arrayList1.add(new Student());//添加的元素，可以是 Person 的父类或者是子类
        arrayList1.add(new C());

        //读取不了是因为放的是 Person 或 Person 的子类，所以只能用 Object 来接收
        //
        //Person person = arrayList1.get(0);

        ArrayList<? super Person> arrayList2 = new ArrayList<>();
        arrayList2.add(new Person());
        arrayList2.add(new Student());

        Object o = arrayList1.get(0);
        System.out.println(o);
    }

    public static void main12(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        List<? extends Number> list = arrayList1;
        //list.add(0,1);//通配符的上界，不适合写入数据，适合读数据。
        // 因为此时的 list 可以引用的子类对象很多，编译器无法确定具体的类型。
        // 编译器为了安全起见，只允许进行读取
        //list.add(1,10.9);
        Number o = list.get(0);
        //Integer a = list.get(1);//报错是因为，读到的数据不一定就是 Integer
    }

    public static void main11(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Alg3.print2(list);
    }

    //通配符：用来解决泛型无法协变的问题。
    // 如果是泛型的话，T 是确定的类型，传了一个类型，就被确定了。
    // 通配符的话，就不被确定。

    //泛型当中的父子类关系：理论上来说：Object 是所有类的父类。因为这些东西编译完成之后都被擦除掉了
    //因为在 JVM 当中，泛型都被擦除了，所以不存在父子类关系。
    public static void main10(String[] args) {
        Alg<Integer> alg1 = new Alg<>();
        System.out.println(alg1);
        Alg<Integer> alg2 = new Alg<>();
        System.out.println(alg2);
    }

    //工作的时候，考试的时候，考的不是语法，考的是代码能力：多刷题，多看算法课，多写代码
    //静态的泛型方法，就不用 new 对象了
    public static void main9(String[] args) {
        Integer[] array = {1,2,3,4};
        //指定了当前方法的类型是 Integer 不写 Integer 也可以，它会通过传的参数去推导
        System.out.println(Alg2.<Integer>findMax(array));
    }

    public static void main8(String[] args) {
        Alg<String> alg = new Alg<>();
        String[] strings = {"qwe", "word", "hello"};
        System.out.println(alg.findMax(strings));
    }

    //Java 当中写的接口，默认是 Comparable 比较器
    public static void main7(String[] args) {
        Alg<Integer> alg1 = new Alg<>();
        Integer[] array = {1,2,3,4};
        System.out.println(alg1.findMax(array));
    }

    //泛型的上界
    public static void main6(String[] args) {
        MyArray2<Integer> array1 = new MyArray2<>();
        MyArray2<Number> array2 = new MyArray2<>();
        array1.set(0,10);
        array2.set(0,10);
        array2.set(1,12.5);
    }

    //这里就把所有 T 类型的换为 Object 类型了
    public static void main5(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        //用 Object 类型来接受，就不会报错了
        Object[] ret2 = arrayList.toArray();
    }


    public static void main4(String[] args) {
        MyArray<String> myArray = new MyArray<>();
        //返回的是 Object 数组，运行时会报错。编译没错，是因为转换为 Object 了
        String[] str = myArray.getArray();//因为什么都能放，编译器认为不安全。
        //因为 new 的是 T[] Object 类型，所以什么类型的元素都能放。
        //要通过反射来创建，指定类型的数组
    }

    //泛型也可以继承类   在使用泛型的时候，一定要用 <> 来表示
    //泛型在编译的时候，字节码文件当中都把 T 替换为 Object 了，这种就是擦除机制
    public static void main3(String[] args) {
        //简单类型 基本类型，不能作为泛型的参数
        MyArray<Integer> myArray = new MyArray();
        myArray.set(0,10);
        //输出的时候，就不需要强制类型转换了，编译器会完成
        int a = myArray.get(0);
    }

    public static void main2(String[] args) {
        //指定数据类型为 String
        MyArray<String> myArray = new MyArray();
        myArray.set(0,"hello");
        //因为指定数据类型是 String ，在编译的时候，自动进行类型的检查
        //myArray.set(1,10);
        //输出的时候，就不需要强制类型转换了，编译器会完成
        String str = myArray.get(0);
    }

    //泛型：学习目的是：能看得懂代码就可以。泛型是Java当中比较难的语法/
    //泛型：一般的 类和方法，只能用具体的类型。要编写可以应用多种类型的代码，就是泛型。
    // 就是需要什么类型的代码，就传什么类型就可以了。把泛型作为了参数进行传递
    public static void main1(String[] args) {
        MyArray1 myArray = new MyArray1();
        myArray.set(0,"hello");
        myArray.set(1,10);
        //什么都能存进去，但是取出的时候要强制转换
        String str = (String) myArray.get(0);
    }
}
