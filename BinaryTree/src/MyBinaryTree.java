import java.util.ArrayList;
import java.util.List;

class BTNode {
    public char val;
    public BTNode left;//左孩子的引用
    public BTNode right;//右孩子的引用
    public BTNode(char val) {
        this.val = val;
    }
}

public class MyBinaryTree {
    //前提：二叉树的创建是一个非常复杂的过程，因此我们简单创建一个
    public BTNode root;//二叉树的根节点
    public BTNode creatTree() {
        BTNode A = new BTNode('A');
        BTNode B = new BTNode('B');
        BTNode C = new BTNode('C');
        BTNode D = new BTNode('D');
        BTNode E = new BTNode('E');
        BTNode F = new BTNode('F');
        BTNode G = new BTNode('G');
        BTNode H = new BTNode('H');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;
        E.right = H;
        return A;
    }

    //前序遍历
    void preOrder(BTNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    void inOrder(BTNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    //后序遍历
    void postOrder(BTNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+" ");
    }

    //获取二叉树当中节点的个数：1、遍历方法（遍历二叉树，如果是节点，判断是不是空，就计数器++）
    int count = 0;
    int size(BTNode root) {
        if (root == null) {
            return 0;
        }
        count++;
        size(root.left);
        size(root.right);
        return count;
    }
    // 2、子问题方法：就是左树的节点个数+右树的节点的个数
    int size1(BTNode root) {
        if (root == null) {
            return 0;
        }
        return size1(root.left) + size1(root.right) + 1;
    }

    //获取叶子节点的个数：1、遍历思路：遍历到叶子节点，就计数器++
    int num = 0;
    int getLeafNodeCount(BTNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            num++;
        }
        getLeafNodeCount(root.left);
        getLeafNodeCount(root.right);
        return num;
    }
    //2、子问题思路：左数的叶子+右树的叶子
    int getLeafNodeCount1(BTNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return getLeafNodeCount1(root.left) + getLeafNodeCount1(root.right);
    }

    //求第 k 层节点的个数：子问题思路
    int getKLevelNodeCount(BTNode root, int k) {
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLevelNodeCount(root.left, k - 1) + getKLevelNodeCount(root.right, k - 1);
    }

    //获取二叉树的高度
    int getHeight(BTNode root) {

    }
}
