import java.util.Arrays;
import java.util.PriorityQueue;

public class heap {

    //堆排序：对一组数据进行从小到大排序，用大根堆

    //topK 问题：给你 100w 个数据，找到前 10 个最大的数据
    // 思路一：对整体进行排序，输出前 10 个最大的元素
    // 思路二：用堆，整体建成一个大根堆 只需要弹出前 10 个最大的元素就好了
    //   1、如果求前 k 个最大的元素，要建应该小根堆
    //   2、如果求前 k 个最小的元素，要建一个大根堆
    //   3、第 k 大的元素，建一个小堆，堆顶元素就是第 k 大的元素

    //Java 建堆
    public static void main2(String[] args) {
        //默认是一个小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //每放一个元素，都要保证当前堆是大堆还是小堆
        priorityQueue.offer(12);
        priorityQueue.offer(3);
        priorityQueue.offer(15);
        //每次弹出之后，仍然是小根堆或者大根堆
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

    //堆：顺序存储一颗二叉树，就是将二叉树层序遍历放到数组当中  是完全二叉树
    //已知双亲（parent）的下标，则：
    // 左孩子下标：2*parent+1
    // 右孩子下标：2*parent+2
    //已知孩子（不区分左右）（child）下标，则：
    // 双亲（paren）下标 = (child - 1)/2
    //堆有大根堆和小根堆两种：
    // 大根堆：每个根都比结点大
    // 小根堆：每个根都比结点小
    //堆对应的集合：priorityQueue（优先级队列）完全二叉树

    //假设调整为大根堆：
    // 1、先找到最后一颗子树
    // 2、parent 遍历
    // 3、写一个向下调整的子树
    // 4、每棵树的调整结束位置

    //建堆的时间复杂度是 O(n)因为时间复杂度 = 每一层结点的个数*每个节点调整的高度
    public static void main1(String[] args) {
        int[] arr = {27,15,19,18,28,34,65,49,25,37};
        TestHeap testHeap = new TestHeap();
        testHeap.createHeap(arr);
        //得到调整之后的结果
        System.out.println(Arrays.toString(testHeap.elem));
        testHeap.offer(80);
        //每次入队之后还是大根堆
        System.out.println(Arrays.toString(testHeap.elem));
        //每次出队 也要保证大根堆和小根堆  先交换 0 下标和最后下标的位置，然后向下调整就可以了
        System.out.println(testHeap.poll());
        //每次弹出的都是最大的元素
        System.out.println(Arrays.toString(testHeap.elem));
    }
}
