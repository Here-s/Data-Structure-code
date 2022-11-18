package avlTree;

import sun.reflect.generics.tree.Tree;

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
        TreeNode node = new TreeNode(val);
        if (root == null) {
            root = node;
            return true;
        }

        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < val) {
                parent = cur;
                cur = cur.right;
            } else if (cur.val == val) {
                return false;
            } else {
                parent = cur;
                cur = cur.left;
            }
        }
        //cur != null
        if (parent.val < val) {
             parent.right = node;
        } else {
            parent.left = node;
        }

        node.parent = parent;
        cur = node;
        //平衡因子的修改
        while (parent != null) {
            //先看 cur 是 parent 的左还是右，决定平衡因子是++ 还是--
            if (cur == parent.right) {
                parent.bf++;
            } else {
                parent.bf--;
            }

            //检查当前的平衡因子，是否平衡
            if (parent.bf == 0) {
                //说明已经平衡了
                break;
            } else if (parent.bf == 1 || parent.bf == -1) {
                //继续向上判断
                cur = parent;
                parent = cur.parent;
            } else {
                if (parent.bf == 2) {
                    //右树高了
                    if (cur.bf == 1) {
                        rotateLeft(parent);
                    } else {
                        //parent.bf = -1
                        rotateRL(parent);
                    }
                } else {
                    //左树高了
                    if (cur.bf == -1) {
                        //右旋
                        rotateRight(parent);
                    } else {
                        //parent.bf = 1
                        rotateLR(parent);
                    }
                }
                //已经平衡
                break;
            }
        }
        return true;
    }

    /**
     * 左单旋
     * @param parent
     */
    private void rotateLeft(TreeNode parent) {
        TreeNode subR = parent.right;
        TreeNode subRL = subR.left;

        parent.right = subRL;

        subR.left = parent;
        if (subRL != null) {
            subRL.parent = parent;
        }

        TreeNode pParent = parent.parent;
        parent.parent = subR;

        if (root == parent) {
            root = subR;
            root.parent = null;
        } else {
            if (pParent.left == parent) {
                pParent.left = subR;
            } else {
                pParent.right = subR;
            }
            subR.parent = pParent;
        }
        subR.bf = parent.bf = 0;
    }

    /**
     * 右单旋
     * @param parent
     */
    private void rotateRight(TreeNode parent) {

        TreeNode subL = parent.left;
        TreeNode subLR = subL.right;

        parent.left = subLR;
        subL.right = parent;

        if (subLR != null) {
            subLR.parent = parent;
        }
        TreeNode pParent = parent.parent;

        parent.parent = subL;

        if (parent == root) {
            root = subL;
            root.parent = null;
        } else {
            //不是根节点，判断这颗子树是左子树，还是右子树
            if (pParent.left == parent) {
                pParent.left = subL;
            } else {
                pParent.right = subL;
            }
            subL.parent = pParent;
        }
        subL.bf = 0;
        parent.bf = 0;
    }

    /**
     * 左右双旋
     * @param parent
     */
    private void rotateLR(TreeNode parent) {
        TreeNode subL = parent.left;
        TreeNode subLR = subL.right;
        int bf = subLR.bf;

        rotateLeft(parent.left);
        rotateRight(parent);

        if (bf == -1) {
            subL.bf = 0;
            subLR.bf = 0;
            parent.bf = 1;
        } else if (bf == 1) {
            subL.bf = -1;
            subLR.bf = 0;
            parent.bf = 0;
        }
    }

    /**
     * 右左双旋
     * @param parent
     */
    private void rotateRL(TreeNode parent) {
        TreeNode subR = parent.right;
        TreeNode subRL = subR.left;
        int bf = subRL.bf;

        rotateRight(parent.right);
        rotateLeft(parent);

        if (bf == 1) {
            parent.bf = -1;
            subR.bf = 0;
            subRL.bf = 0;
        } else if (bf == -1) {
            parent.bf = 0;
            subR.bf = 0;
            subRL.bf = 1;
        }
    }


    //中序遍历的解惑是有序的，不一定能说明当前树是 AVLTree
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val+" ");
        inorder(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = height(root.left);
        int rightH = height(root.right);

        return  leftH > rightH ? leftH+1 : rightH+1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftH = height(root.left);
        int rightH = height(root.right);

        if (rightH - leftH != root.bf) {
            System.out.println("这个节点：" + root.val + " 平衡因子异常");
            return false;
        }

        return Math.abs(leftH - rightH) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }
}
