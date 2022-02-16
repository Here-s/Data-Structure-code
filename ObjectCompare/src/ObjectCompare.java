import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

class Card implements Comparable<Card> {
    public int rank;//数值
    public String suit;//花色
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card o) {
        //这里的排序换一下，就变成大堆排序了
        return this.rank - o.rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit='" + suit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && Objects.equals(suit, card.suit);
    }

    //不关心
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

class RankComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.rank - o2.rank;
    }
}

public class ObjectCompare {

    public static void main(String[] args) {
        Card card1 = new Card(1,"♥");
        Card card2 = new Card(1,"♥");//不重写 equals 方法的话，默认比较的是引用
        //重写之后 就是自定义比较了
        System.out.println(card1.equals(card2));
    }

    public static void main4(String[] args) {
        Card card1 = new Card(1,"♥");
        Card card2 = new Card(2,"♥");
        //重写了 Comparator 方法  是一个匿名内部类  这里简写是一个 lambda 表达式，可读性很差
        PriorityQueue<Card> priorityQueue = new PriorityQueue<>((x, y)-> {
            return y.rank - x.rank;
        });
        priorityQueue.offer(card1);
        priorityQueue.offer(card2);
        System.out.println(priorityQueue);
    }

    public static void main3(String[] args) {
        Card card1 = new Card(1,"♥");
        Card card2 = new Card(2,"♥");
        //重写了 Comparator 方法  是一个匿名内部类
        PriorityQueue<Card> priorityQueue = new PriorityQueue<>(new Comparator<Card>() {
            public int compare(Card o1, Card o2) {
                return o1.rank - o2.rank;
            }
        });
        priorityQueue.offer(card1);
        priorityQueue.offer(card2);
        System.out.println(priorityQueue);
    }

    public static void main2(String[] args) {
        Card card1 = new Card(1,"♥");
        Card card2 = new Card(2,"♥");
        RankComparator rankComparator = new RankComparator();
        PriorityQueue<Card> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(card1);
        priorityQueue.offer(card2);
        System.out.println(priorityQueue);
    }

    public static void main1(String[] args) {
        //默认是一个小根堆
        //而且放的元素之间是必须能比较的  不能是 null
        //关于对象的比较：
        // 1、equals()：比较两个对象相不相同
        // 2、比较大小：Comparable compareTo
        PriorityQueue<Card> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Card(2,"♥"));//
        priorityQueue.offer(new Card(1,"♥"));
        System.out.println(priorityQueue);
    }
}
