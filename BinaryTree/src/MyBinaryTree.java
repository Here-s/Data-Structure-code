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
}
