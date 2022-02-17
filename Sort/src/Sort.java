import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Sort {

    //对时间有要求，就用快排
    //对空间有要求：用堆排或归并

    //快速排序：非递归
    public static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int end = array.length - 1;
        int pivot = partition(array,start,end);
    }


    //快速排序：
    // 从待排序区间选择一个数，作为基准值。
    // 然后遍历待排序区间，将比基准值小的放到左边。大的 放到右边。
    // 采用分治思想，对左右两个小区间按照同样的方式处理，直到小区间的长度 == 1，代表已经有序，
    // 或者小区间的长度 == 0，代表没有数据。
    // 找的时候，以第一个数据为基准值，然后从后往前找，比基准值小就放前面。找到之后，再从前往后找这样找。
    /**
     * 快速排序：挖坑法   因为是递归，所以排的数据太多的时候，就会栈溢出  因为可能出现单分支二叉树
     * 做题用挖坑法
     * 时间复杂度：O(N*log以2为底N)   最大是：O(N^2)
     * 空间复杂度：O(logN)  最坏是：O(N)
     * 稳定性：不稳定
     * 也可以把与基准相同的数据，从两边移到基准旁边
     * 利用直接插入排序，越有序越快
     * @param array
     */
    public static void quickSort1(int[] array) {
        quick(array, 0, array.length - 1);
    }
    public static void insertSort2(int[] array, int start, int end) {
        for (int i = 1; i <= end; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            //j 回退到了小于 0 的情况下
            array[j + 1] = tmp;
        }
    }
    public static void quick(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        //如果区间内的数据，在排序过程中，小于某个范围了，可以使用直接插入排序
        if (end - start + 1 <= 40) {
            //使用直接插入排序
            insertSort2(array, start, end);
        }
        //找基准之前，先找到中间大小的值
        int midValIndex = findMidValIndex(array, start, end);
        swap(array,midValIndex,start);//加上这两行，就不用担心栈溢出了。
        //找到基准
        int pivot = partition(array, start, end);
        //处理左边
        quick(array,start,pivot-1);
        //处理右边
        quick(array,pivot+1,end);
    }
    public static int findMidValIndex(int[] array, int start, int end) {
        int mid = start + (end-start)/2;
        if (array[start] < array[end]) {
            if (array[mid] < array[start]) {
                return start;
            } else if (array[mid] > array[end]){
                return end;
            } else {
                return mid;
            }
        } else {
            if (array[mid] > array[start]) {
                return start;
            } else if (array[mid] < array[end]) {
                return end;
            } else {
                return mid;
            }
        }
    }
    private static int partition(int[] array, int start, int end) {
        int tmp = array[start];
        while (start < end) {
            while (array[end] >= tmp && start < end) {
                end--;
            }
            //end 下标遇到了小于 tmp 的值
            array[start] = array[end];
            while (array[start] <= tmp && start < end) {
                start++;
            }
            //start 下标遇到了大于 tmp 的值
            array[end] = array[start];
        }
        array[start] = tmp;
        return start;
    }
    public static void main(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //冒泡排序
    /**
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flg = false;
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] > array[j+1]) {
                    if (array[j] > array[j+1]) {
                        swap(array, j, j+1);
                        flg = true;
                    }
                }
            }
            if (flg == false) {
                break;
            }
        }
    }
    public static void main5(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //堆排序
    /**
     * 时间复杂度：O(N*log N)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * 面试写堆排序：写的就是调整过程
     * @param array
     */
    public static void heapSort(int[] array) {
        //1、建堆
        createHeap(array);
        int end = array.length-1;
        //2、交换且调整
        while (end > 0) {
            swap(array,0,end);
            shiftDown(array,0,end);
            end--;
        }
    }
    //先建成大根堆
    public static void createHeap(int[] array) {
        for (int parent = (array.length-1-1)/2; parent >= 0; parent--) {
            shiftDown(array, parent, array.length);
        }
    }
    //向下调整
    public static void shiftDown(int[] array, int parent, int len) {
        int child = 2*parent+1;
        while (child < len) {
            if (child+1 < len && array[child] < array[child+1]) {
                child++;
            }
            //child 下标 就是左右孩子最大值的下标
            if (array[child] > array[parent]) {
                swap(array,child,parent);
                parent = child;
                child = 2*parent+1;
            } else {
                break;
            }
        }
    }
    public static void main4(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void swap(int[] array,int i,int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //双向排序：一个指针在前 一个在后 然后两个相遇就可以了

    //选择排序
    /**
     * 选择排序
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param arr 待排序序列
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找到最小值
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

    public static void test1(int capacity) {
        int[] arr = new int[capacity];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        shellSort(arr);
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    public static void test2(int capacity) {
        int[] arr = new int[capacity];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(capacity);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        shellSort(arr);
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main3(String[] args) {
        test1(100000);
        test2(100000);
    }


    //思考：假设现在有 10000 个数据，如果对着组数据进行排序，使用插入排序：10000*10000 一亿次
    // 如果分组的话，分成100组，那么每组100个数据，就是 100*100*100  一百万次
    // 这种分组的思想就是希尔排序：shellsort  是直接插入排序的优化
    //最后一次排序，要看做一组  也就是说前面大于 1 组的这些都是预排序
    //分的组数建议是素数，分组是重点

    //每组都是插入

    /**
     *希尔排序的时间复杂度是：O(N^1.3~N^1.5)
     * 空间复杂度：O(1)
     * 稳定性：不稳定    看比较的过程中是否发生跳跃式交换，发生之后就是不稳定的排序
     * @param arr 待排序序列
     * @param gap 组数
     */
    public static void shell(int[] arr, int gap) {
        for (int i = gap; i < arr.length; i++) {
            int tmp = arr[i];
            int j = 0;
            for (j = i - gap; j >= 0; j -= gap) {
                if (arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + gap] = tmp;
        }
    }
    public static void shellSort(int[] arr) {
        int gap = arr.length;
        while (gap > 1) {
            shell(arr, gap);
            gap /= 2;
        }
        shell(arr, 1);//保证最后是一组
    }
    public static void main2(String[] args) {
        int[] arr = {12,5,18,10,4,2};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //折半插入排序：对已经有序的部分进行二分查找，然后插入

    //插入排序：类似于冒泡排序
    /**
     * 时间复杂度：O(N^2)   最好是O(N)：对于直接插入排序，最好的情况就是数据本身就有序
     * 对于直接插入排序来说，数据越有序，越快
     * 空间复杂度：O(1)
     * 稳定性：稳定的 因为排序的时候是大于
     * 一个稳定的排序，可以实现为不稳定的排序，但是一个本身不稳定的排序，是不能变成稳定的排序
     * 经常使用在数据量不多，且整体数据趋于有序
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            //j 回退到了小于 0 的情况下
            array[j + 1] = tmp;
        }
    }
    public static void main1(String[] args) {
        int[] arr = {12,5,18,10,4,2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //排序的稳定性：排序前：3 2 1 三
    // 如果排序后是：1 2 3 三 那么就是稳定的   如果是：1 2 三 3 那么就不是稳定的
    //常见的排序算法：插入排序、选择排序、交换排序、归并排序
    // 插入排序：直接插入排序、希尔排序
    // 选择排序：选择排序、堆排序
    // 交换排序：冒泡排序、快速排序
    // 归并排序：归并排序
}
