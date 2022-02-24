import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    public String ID;

    public Person(String ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(ID, person.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                '}';
    }
}

//加上 K,V 就变成泛型了
public class HashBuck2<K,V> {

    static class Node<K,V> {
        public K key;
        public V val;
        public Node<K,V> next;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node<K,V>[] array = (Node<K, V>[]) new Node[10];
    public int usedSize;

    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % array.length;
        Node<K,V> cur = array[index];
        while (cur!= null) {
            //hashcode 来确定位置， equals 来看谁的 key 一样
            //hashcode 一样，equals 不一定一样
            //equals 一样，hashcode 一定一样
            if (cur.key.equals(key)) {
                cur.val = value;//更新 value 值
                //更新完之后就不用插入了，所以 return
                return;
            }
            cur = cur.next;
        }
        //没有这个 key 元素，这里头插法
        Node<K,V> node = new Node<>(key, value);
        node.next = array[index];
        array[index] = node;
        this.usedSize++;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % array.length;
        Node<K,V> cur = array[index];
        while (cur!= null) {
            if (cur.key.equals(key)) {
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Person person1 = new Person("123");
        Person person2 = new Person("123");
        HashBuck2<Person,String> hashBuck2 = new HashBuck2<>();
        hashBuck2.put(person1,"Lockey");
        System.out.println(hashBuck2.get(person2));
    }

    public static void main1(String[] args) {
        //hashcode 能够解决，在引用类型情况下，不是合法整数的情况下，变成合法整数
        //假设加下了的 key 是一个 person 。身份证号是一样的，我们认为是同一个人。又因为要把 person1 和 person2 放到散列表当中，
        //          我们需要把它变为整形的，就调用 hashcode:生成一个整数 % length = index。因为人一样，所以生成的整数也应该一样
        Person person1 = new Person("123");
        Person person2 = new Person("123");
        //在重写了 hashcode 之后，值就一样了，就会把两个人放到同一个位置
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        //以 person 作为 key 值的时候，一定要重写 hashcode 不然本意上两个一样的人，就不一样了
        Map<Person,String> map = new HashMap<>();
    }
}
