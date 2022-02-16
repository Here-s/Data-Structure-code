import java.util.*;

public class TopK {

    /**
     * 求数组当中前 k 个最小的元素
     * @param arr
     * @param k
     * @return
     */
    public static int[] topK(int[] arr, int k) {
        //求最小，所以先创建应该大小为 k 的大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //遍历数组当中的元素，前 k 个元素放到队列当中
        for (int i = 0; i < arr.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(arr[i]);
            } else {
                //从第 k + 1 个元素开始，每个元素都和堆顶元素进行比较
                int top = maxHeap.peek();
                if (top > arr[i]) {
                    //先弹出
                    maxHeap.poll();
                    //后存入
                    maxHeap.offer(arr[i]);
                }
            }
        }
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = maxHeap.poll();
        }
        return tmp;
    }

    public static void main(String[] args) {
        int[] arr = {18,21,8,10,34,12};
        int[] tmp = topK(arr,3);
        System.out.println(Arrays.toString(tmp));
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return (o2.get(0)+o2.get(1)) - (o1.get(0)+o1.get(1));
            }
        });
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                if (maxHeap.size() < k) {
                    List<Integer> tmpList = new ArrayList<>();
                    tmpList.add(nums1[i]);
                    tmpList.add(nums2[j]);
                    maxHeap.offer(tmpList);
                } else {
                    int top = maxHeap.peek().get(0) + maxHeap.peek().get(1);
                    if (top > nums1[i] + nums2[j]) {
                        maxHeap.poll();
                        List<Integer> tmpList = new ArrayList<>();
                        tmpList.add(nums1[i]);
                        tmpList.add(nums2[j]);
                        maxHeap.offer(tmpList);
                    }
                }
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            ret.add(maxHeap.poll());
        }
        return ret;
    }
}
