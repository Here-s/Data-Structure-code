import java.util.*;

public class Queues {


    public static void main(String[] args) {
        MyCircleQueue myCircleQueue = new MyCircleQueue(4);
        myCircleQueue.enQueue(1);
        myCircleQueue.enQueue(2);
        myCircleQueue.enQueue(3);
        myCircleQueue.enQueue(4);
        myCircleQueue.deQueue();
        int val = myCircleQueue.front;//得到队头元素
        System.out.println(val);
        int val2 = myCircleQueue.rear;//得到队尾元素
        System.out.println(val2);
        myCircleQueue.deQueue();
        int val3 = myCircleQueue.front;
        System.out.println(val3);
    }
    
    public static void main4(String[] args) {
        MyQueue queue = new MyQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());//1
        System.out.println(queue.poll());//1
        System.out.println(queue.poll());//2
        System.out.println(queue.poll());//3
//        System.out.println(queue.poll());//队列为空了
    }

    //用单链表实现队列要求尾 last 双向链表就不用这样

    //ArrayList 和 LinkList 的区别：1、共性，增删查改  2、内存的逻辑来说

    public static void main3(String[] args) {
        //双端队列
        Deque<Integer> queue2 = new LinkedList<>();
        queue2.offerFirst(1);
        queue2.offerLast(2);
        queue2.offerLast(3);
        queue2.offerLast(4);
        queue2.offerLast(5);
        System.out.println(queue2);
        queue2.removeFirst();
        queue2.removeLast();
        System.out.println(queue2);
        System.out.println(queue2.peekFirst());
        System.out.println(queue2.peekLast());

        //LinkedList 可以当作简单队列，也可以当作双端队列，也可以当作栈和双向链表
    }

    public static void main2(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        //add 可能会抛异常，所以多用 offer
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue.element());
        System.out.println(queue.peek());
    }

    public static void main1(String[] args) {
        //队列就跟在餐厅排队打饭一样，先排队的人先打到饭

        //普通队列：只能队尾进，队头出
        Queue<Integer> queue = new LinkedList<>();
        //双端队列，可以队尾进队尾出，也可以对头进队头出。就可以当栈来使用
        Deque<Integer> queue2 = new LinkedList<>();
    }
}
