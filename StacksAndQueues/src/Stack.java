public class Stack {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        //弹出栈顶元素并且删除
        System.out.println(stack.pop());
        //获取栈顶元素，不删除
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
    }

    public static void main1(String[] args) {
        //什么是栈：也是一种数据结构  先进后出  先进的放到栈底，栈底最后出
        //JVM当中包括：Java虚拟机栈  本地方法栈  堆  方法区  程序计数器
        //JVM 栈只是 JVM 当中的一块内存，该内存一般用来存放：局部变量......
        //调用函数的时候，回味这个函数开辟一块内存，叫做栈帧，在 JVM 栈开辟

        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //弹出栈顶元素并且删除
        System.out.println(stack.pop());
        //获取栈顶元素，不删除
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println(stack.empty());
        System.out.println(stack.isEmpty());
    }
}
