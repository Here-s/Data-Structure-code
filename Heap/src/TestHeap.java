import java.util.Arrays;

public class TestHeap {
    public int[] elem;
    public int usedSize;

    public TestHeap() {
        this.elem = new int[10];
    }

    /**
     * 向下调整
     * @param parent 每棵树的根节点
     * @param len 每棵树的结束位置
     */
    public void shiftDown(int parent, int len) {
        int child = 2*parent+1;
        while (child < len) {
            if (child + 1 < len && elem[child] < elem[child + 1]) {
                child++;
            }
            if (elem[child] > elem[parent]) {
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;
                parent = child;
                child = 2*parent+1;
            } else {
                break;
            }
        }
    }

    public void createHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            elem[i] = array[i];
            this.usedSize++;
        }
        for ( int parent = (usedSize-1-1)/2; parent >= 0; parent--) {
            //调整
            shiftDown(parent, usedSize);
        }
    }

    /**
     * 入堆的向上调整
     * @param child
     */
    private void shiftUp(int child) {
        int parent = (child - 1)/2;
        while (parent >= 0) {
            if (elem[child] > elem[parent]) {
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;
                child = parent;
                parent = (child - 1)/2;
            } else {
                break;
            }
        }
    }
    public void offer(int val) {
        if (isFull()) {
            //满了 扩容
            elem = Arrays.copyOf(elem,2*elem.length);
        }
        elem[usedSize++] = val;
        //传入的是 usedSize - 1
        shiftUp(usedSize-1);
    }
    public  boolean isFull() {
        return  usedSize == elem.length;
    }

    public boolean isEmpty() {
        return usedSize == 0;
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("优先级队列为空！");
        }
        int tmp = elem[0];
        elem[0] = elem[usedSize - 1];
        elem[usedSize - 1] = tmp;
        usedSize--;
        shiftDown(0,usedSize);
        return tmp;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("优先级队列为空！");
        }
        return elem[0];
    }

    public void heapSort() {
        int end = this.usedSize-1;
        while (end > 0) {
            int tmp = elem[0];
            elem[0] = elem[end];
            elem[end] = tmp;
            shiftDown(0,end);
            end--;
        }
    }
}
