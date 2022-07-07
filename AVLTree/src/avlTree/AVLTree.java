package avlTree;

public class AVLTree {
    static class TreeNode {
        public int val;
        public int bf;//平衡因子
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root;

    public boolean insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return true;
        }
    }
}
