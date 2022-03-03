import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Sort {

    //往往不比较数据大小去排序的话，时间复杂度一般是：O(N)


    //其它基于非比较的排序：计数排序、基数排序、桶排序
    // 基数排序：就是按照个位的大小排除了，然后是十位数，最后是大位数。
    // 桶排序：确定区间，然后把区间内的数排序，然后拿出来就可以了
    // 计数排序（适用于某个范围）：


    //计数排序
    /**
     *一般适用于有 n 个数，数据范围是 0-n 之间的
     * 时间复杂度：O(N)
     * 空间复杂度：O(M) M：当前数据的范围
     * 稳定性：当前代码是不稳定的，但本质是稳定的
     * @param array
     */
    public static void countingSort(int[] array) {
        int maxVal = array[0];
        int minVal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minVal) {
                minVal = array[i];
            }
            if (array[i] > maxVal) {
                maxVal = array[i];
            }
        }
        //说明已经找到了最大值和最小值
        int[] count = new int[maxVal-minVal+1];
        //统计 array 数组当中，每个数据出现的次数
        for (int i = 0; i < array.length; i++) {
            int index = array[i];
            //为了空间的合理使用，达到区间的使用
            count[index-minVal]++;
        }
        //说明在计数数组当中，已经把 array 数组当中，每个数据出现的次数统计好了。
        //接下来只需要遍历输出就可以了
        int indexArray = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                //加上 minVal 因为可能是区间
                array[indexArray] = i+minVal;
                count[i]--;
                indexArray++;
            }
        }
    }
    public static void main11(String[] args) {
        int[] arr = {12,5,6,2,5,418,10,4,2,23,546,97,34,89};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //海量数据排序：内存只有1G 排序的数据有100G
    //因为内存中无法把所有数据放下，所以需要外部排序，而归并排序是最常用的外部排序（外部是指数据在硬盘）
    // 1、把 100 G的文件切成 200 分，每个 512 M
    // 2、分别对 512 M排序，因为内存已经可以放得下，所以任意排序方式都可以
    // 3、进行 200 路归并，同时对 200 份有序文件做归并过程，最终结果就有序了


    //对时间有要求，就用快排
    //对空间有要求：用堆排或归并
    //只有冒泡、插入、归并是稳定的

    //归并排序：非递归
    /**
     *
     * @param array
     */
    public static void mergeSort(int[] array) {
        int nums = 1;//代表每组的数据个数
        while (nums < array.length) {
            //数组每次都要进行遍历
            for (int i = 0; i < array.length; i += nums*2) {
                int left = i;
                int mid = left+nums-1;
                //防止越界
                if (mid >= array.length) {
                    mid = array.length - 1;
                }
                int right = mid+nums;
                if (right >= array.length) {
                    right = array.length - 1;
                }
                //下标确定之后，进行合并
                merge(array, left, mid, right);
            }
            nums *= 2;
        }
    }
    public static void main(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }



    //归并排序：将已有的子序列合并，得到完全有序的的序列。将一个一个的元素二路归并为有序数组
    /**
     * 归并排序
     * 时间复杂度：O(N*log以2为底N)
     * 空间复杂度：O(N)
     * 稳定性：稳定
     * @param array
     */
    public static void mergeSort1(int[] array) {
        mergeSortInternal(array,0, array.length-1);
    }
    private static void mergeSortInternal(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low)/2;
        //左边
        mergeSortInternal(array, low, mid);
        //右边
        mergeSortInternal(array, mid+1, high);
        //合并
        merge(array,low,mid,high);
    }
    private static void merge(int[] array, int low, int mid, int high) {
        int[] tmp = new int[high-low+1];
        int index = 0;
        int s1 = low;
        int e1 = mid;
        int s2 = mid+1;
        int e2 = high;
        while (s1 <= e1 && s2 <= e2) {
            if (array[s1] <= array[s2]) {
                tmp[index++] = array[s1++];
            } else {
                tmp[index++] = array[s2++];
            }
        }
        while (s1 <= e1) {
            tmp[index++] = array[s1++];
        }
        while (s2 <= e2) {
            tmp[index++] = array[s2++];
        }
        //说明排好序了，拷贝 tmp 数组的元素放到原来的数组当中
        for (int i = 0; i < index; i++) {
            array[i+low] = tmp[i];
        }
    }
    public static void main9(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        mergeSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    //给两个有序数组，合并成一个有序数组   合并后的长度是 这个两个数组长度的和 合并后的数组定义为 tmp
    //定义 s1 和 e1 表示 start1 和 end1    s2 和 e2  表示 start2
    //比较 s1 和 s2 谁小把谁放到数组当中，然后再++ 然后继续比较
    /**
     * 合并两个有序数组为一个有序数组
     * @param array1 有序数组1
     * @param array2 有序数组2
     * @return
     */
    public static int[] mergeArray(int[] array1, int[] array2) {
        int s1 = 0;
        int e1 = array1.length - 1;
        int s2 = 0;
        int e2 = array2.length - 1;
        int[] tmp = new int[array1.length+array2.length];
        int index = 0;
        while (s1 <= e1 && s2 <= e2) {
            if (array1[s1] <= array2[s2]) {
                tmp[index++] = array1[s1++];
            } else {
                tmp[index++] = array2[s2++];
            }
        }
        while (s1 <= e1) {
            tmp[index++] = array1[s1++];
        }
        while (s2 <= e2) {
            tmp[index++] = array2[s2++];
        }
        return tmp;
    }

    public static void main8(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        int[] arr = mergeArray(arr1, arr2);
        System.out.println(Arrays.toString(arr));
    }


    //快速排序：非递归
    // 第一次划分之后，把左右的数对都放到栈当中。前提：Pivot 左边有两个元素，右边有两个元素
    public static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int end = array.length - 1;
        int pivot = partition(array,start,end);
        if (pivot > start+1) {
            //左边有两个元素
            stack.push(start);
            stack.push(pivot - 1);
        }
        if (pivot < end - 1) {
            stack.push(pivot+1);
            stack.push(end);
        }
        while (!stack.isEmpty()) {
            end = stack.pop();
            start = stack.pop();
            pivot = partition(array,start,end);
            if (pivot > start+1) {
                //左边有两个元素
                stack.push(start);
                stack.push(pivot - 1);
            }
            if (pivot < end - 1) {
                stack.push(pivot+1);
                stack.push(end);
            }
        }
    }
    public static void main7(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
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
//        int midValIndex = findMidValIndex(array, start, end);
//        swap(array,midValIndex,start);//加上这两行，就不用担心栈溢出了。
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
    public static void main6(String[] args) {
        int[] arr = {12,5,18,10,4,2,23,546,97,34,89};
        quickSort1(arr);
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
