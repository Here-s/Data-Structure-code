import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    //所有二叉树相关的题目，基本上都是通过某种遍历方法来去解题的。
    //   有前序遍历（先根遍历），中序遍历，后续遍历   是因为访问的时机不一样
    //   遍历(Traversal)是指沿着某条搜索路线，依次对树中每个结点均做一次且仅做一次访问。访问结点所做的操作
    //   依赖于具体的应用问题(比如：打印节点内容、节点内容加1)
    //前序遍历：先根-》左子树-》右子树  每棵树的访问方式都是根左右这样
    //中序遍历：左子树-》根-》右子树
    //后序遍历：左子树-》右子树-》根
    //因为每棵树的访问方式一样，所以就可以用递归来访问

    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree();
        BTNode root = myBinaryTree.creatTree();
        System.out.println();
        myBinaryTree.preOrder(root );
        System.out.println();
        myBinaryTree.inOrder(root);
        System.out.println();
        myBinaryTree.postOrder(root);
        System.out.println();
        System.out.println(myBinaryTree.size1(root));
        System.out.println(myBinaryTree.getLeafNodeCount1(root));
        System.out.println(myBinaryTree.getKLevelNodeCount(root, 3));
    }

    //二叉树的存储：顺序存储，和链式存储
    //  孩子兄弟表示法：要有 val 左孩子 右孩子     也就是一个节点有三个域：值，左孩子，右孩子
    //  还可以加一个父类的引用，变成四个域

    //对任何一棵二叉树, 如果其叶结点（度为0的节点）个数为 n0, 度为2的非叶结点个数为 n2,则有n0＝n2＋1
    //具有 n 个结点的完全二叉树，树的深度k为 log以2为底（n+1）上取整
    //任何一棵树，有 N 个节点，就会产生 N-1 条边

    //树的表现形式：双亲表示法，孩子表示法、孩子兄弟表示法。常用的是：孩子表示法
    //整颗书变成二叉树的话，左右子树都是二叉树
    //满二叉树也是一种特殊的完全二叉树

    //在具有 2n 个节点的完全二叉树当中，叶子节点为 n
    //偶数个节点的时候，有 1 个度为 1 的节点。 度是指出度
    //奇数个节点的时候，没有度为 1 的节点。

    //对于具有n个结点的完全二叉树，如果按照从上至下从左至右的顺序对所有节点从0开始编号，则对于序号为i的结点有：
    //若i>0，双亲序号：(i-1)/2；i=0，i为根节点编号，无双亲节点
    //假设父亲节点的下标是 i 左孩子：2*i + 1    右孩子：2*i + 2
}
