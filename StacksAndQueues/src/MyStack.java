import java.util.*;

public class MyStack {

    public int[] elem;
    public int usedSize;

    public MyStack() {
        this.elem = new int[5];
    }

    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }

    public  void push(int val) {
        if(isFull()) {
            //满了  那么扩容
            this.elem = Arrays.copyOf(this.elem,2*this.elem.length);
        }
        this.elem[this.usedSize] = val;
        this.usedSize++;
    }

    public boolean isEmpty() {
        return this.usedSize == 0;
    }

    public int pop() {
        if ((isEmpty())) {
            throw new RuntimeException("栈为空！");
        }
        int oldVal = this.elem[this.usedSize-1];
        this.usedSize--;
        return  oldVal;
    }

    public int peek() {
        if ((isEmpty())) {
            throw new RuntimeException("栈为空！");
        }
        return this.elem[this.usedSize-1];
    }

    public int size() {
        return this.usedSize;
    }
}
