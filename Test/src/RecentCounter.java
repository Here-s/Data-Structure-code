import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {

    Queue<Integer> queue1;
    public RecentCounter() {
        queue1 = new LinkedList<>();
    }

    public int ping(int t) {
        queue1.offer(t);
        while (queue1.peek() < t - 3000) {
            queue1.poll();
        }
        return queue1.size();
    }
}
