public class MyCircleQueue {

    public int[] elem;
    public int front;
    public int rear;

    public MyCircleQueue(int k) {
        this.elem = new int[k];
    }

    public boolean enQueue(int value) {
        this.elem[rear] = value;
        rear = (rear+1)% elem.length;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front+1)% elem.length;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            //OJ 上面只能返回 -1 如果抛异常的话，可能会认为代码出错了
            return -1;
        }
        return this.elem[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int index = -1;
        if(rear == 0){
            index = elem.length - 1;
        } else {
            index = rear - 1;
        }
        return this.elem[index];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        if((this.rear+1)% elem.length == front){
            return true;
        }
        return false;
    }
}
