class Node {
    public int val;
    public Node next;
    public Node(int val) {
        this.val = val;
    }
}
public class MyQueue {
    public Node head;
    public Node last;

    /**
     * 向队列当中增加元素
     * @param val
     */
    public void offer(int val) {
        Node node = new Node(val);
        if(head == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = last.next;
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * 在队列当中弹出元素
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        int oldVal = head.val;
        this.head = head.next;
        return oldVal;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return this.head.val;
    }
}
