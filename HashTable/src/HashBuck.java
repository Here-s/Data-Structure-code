public class HashBuck {

    static class Node {
        public int key;
        public int val;
        public  Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node[] array;
    public int usedSize;

    public static final double DEFAULT_LOAD_FACTOR = 0.75;

    public HashBuck() {
        this.array = new Node[8];
    }

    /**
     * put 函数
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        //先找到 key 所对应的位置
        int index = key % this.array.length;
        //遍历这个下标的链表，看看有没有这个 key ，有的话更新 value
        Node cur = array[index];
        while (cur!= null) {
            if (cur.key == key) {
                cur.val = value;//更新 value 值
                //更新完之后就不用插入了，所以 return
                return;
            }
            cur = cur.next;
        }
        //没有这个 key 元素，这里头插法
        Node node = new Node(key, value);
        node.next = array[index];
        array[index] = node;
        this.usedSize++;
        //插入元素后，检查当前散列表的负载因子
        if (loadFactor() > DEFAULT_LOAD_FACTOR) {
            //大了，说明要扩容了。如果扩容了，那么里面的所有元素，都要重新哈希到新的数组
            resize();
        }
    }
    private void resize() {
        Node[] newArray = new Node[array.length*2];
        for (int i = 0; i < array.length; i++) {
            Node cur = array[i];
            while (cur != null) {
                int index = cur.key % newArray.length;//获取新的下标
                //把 cur 这个节点，以头插/尾插的方法，插入到新的数组对应的下标当中
                Node curNext = cur.next;
                cur.next = newArray[index];
                newArray[index] = cur;
                cur = curNext;
            }
        }
        array = newArray;
    }
    private double loadFactor(){
        return 1.0*usedSize/array.length;
    }

    /**
     * 根据 key 拿到 value
     * @param key
     * @return
     */
    public int get(int key) {
        //先找到 key 所对应的位置
        int index = key % this.array.length;
        //遍历这个下标的链表，看看有没有这个 key ，有的话更新 value
        Node cur = array[index];
        while (cur!= null) {
            if (cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    public static void main(String[] args) {
        HashBuck hashBuck = new HashBuck();
        hashBuck.put(1,1);
        hashBuck.put(12,12);
        hashBuck.put(3,3);
        hashBuck.put(6,6);
        hashBuck.put(7,7);
        hashBuck.put(2,2);
        hashBuck.put(11,11);
        hashBuck.put(8,8);
        System.out.println(hashBuck.get(11));
    }
}
