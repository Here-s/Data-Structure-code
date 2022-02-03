import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueForStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public TwoQueueForStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (!queue1.isEmpty()) {
            queue1.offer(x);
        } else if (!queue2.isEmpty()) {
            queue2.offer(x);
        } else {
            queue1.offer(x);
        }
    }

    public int pop() {
        if (empty()) {
            //OJ 上面返回 -1
            return -1;
        }
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                int val = queue1.poll();
                queue2.offer(val);
            }
            return queue1.poll();
        }
        if (!queue2.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                int val = queue2.poll();
                queue1.offer(val);
            }
            return queue2.poll();
        }
        return -1;
    }

    public int top() {
        if (empty()) {
            //OJ上面返回 -1
            return -1;
        }
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            int val = -1;
            for (int i = 0; i < size; i++) {
                val = queue1.poll();
                queue2.offer(val);
            }
            return queue1.poll();
        }
        if (!queue2.isEmpty()) {
            int size = queue2.size();
            int val = 0;
            for (int i = 0; i < size; i++) {
                val = queue2.poll();
                queue1.offer(val);
            }
            return queue2.poll();
        }
        return -1;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
