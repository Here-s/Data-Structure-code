class Node {
    public int val;
    public Node left;
    public Node right;
    public Node(int val) {
        this.val = val;
    }
}
public class BinarySearchTree {
    public Node root = null;

    //查找
    public Node search(int key) {
        Node cur = root;
        while (cur != null) {
            if (cur.val == key) {
                return cur;
            }
            if (key < cur.val) {
                cur = cur.left;
            }
            if (key > cur.val) {
                cur = cur.right;
            }
        }
        return null;
    }
    //插入：插入的数据一定是叶子节点：
    // 1、cur  parent 来找到 val 需要存储的位置
    // 2、parent.val  val比较大小，确定是左边还是右边插入

    public boolean insert(int val) {
        if (root == null) {
            root = new Node(val);
            return true;
        }
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            parent = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                //不能有相同的数据进行插入
                return false;
            }
        }
        if (parent.val > val) {
            parent.left = new Node(val);
        } else {
            parent.right = new Node(val);
        }
        return true;
    }

    //删除：
    // 1、cur 是 root  cur 是删除的节点，左边是 null 右边不是，那么只需要 root = cur.right
    // 2、cur 不是 root  cur 是 parent.left 则 parent.left = cur.right
    // 3、cur 不是 root  cur 是 parent.right 则 parent.right = cur.right
    public void remove(int key) {
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            if (cur.val == key) {
                removeNode(cur,parent);
                break;
            } else if (cur.val < key) {
                parent = cur;
                cur = cur.right;
            } else {
                parent = cur;
                cur = cur.left;
            }
        }
    }
    public void removeNode(Node cur, Node parent) {

        if (cur.left == null) {
            if (cur == root) {
                root = cur.right;
            } else if (cur == parent.left) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        } else if (cur.right == null) {
            if (cur == root) {
                root = root.left;
            } else if (cur == parent.left) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        } else {
            Node targetParent = cur;
            Node target = cur.right;
            while (target.left != null) {
                targetParent = target;
                target = target.left;
            }
            cur.val = target.val;
            if (target == targetParent.left) {
                targetParent.left = target.right;
            } else {
                targetParent.right = target.right;
            }
        }
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        int[] array = {10,8,19,3,9,4,7};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < array.length; i++) {
            binarySearchTree.insert(array[i]);
        }
        binarySearchTree.inOrder(binarySearchTree.root);
        System.out.println();
        System.out.println("插入重复的数据");
        System.out.println(binarySearchTree.insert(3));
        System.out.println("删除数据");
        binarySearchTree.remove(7);
        binarySearchTree.inOrder(binarySearchTree.root);
    }
}
