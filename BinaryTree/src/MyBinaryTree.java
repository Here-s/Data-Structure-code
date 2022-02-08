import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    /**
     * 这种是穷举的方式建立的二叉树，太复杂了。
     * @return
     */
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

    //获取二叉树的高度：子问题思路：左数的高度和右树的高度取最大值，然后+1
    int getHeight(BTNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    //检测值为 value 的元素是否存在   遍历二叉树某个节点的值是否是我找的数据
    BTNode find(BTNode root, char val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        BTNode ret = find(root.left, val);
        if (ret != null) {
            return ret;
        }
        return find(root.right, val);
    }

    //判断一棵树是不是完全二叉树：用 非递归 更好用   通过队列来：
    // 将每一个节点放入队列，空也要入队，然后左右子树也放入，然后出队，如果出到队列为空的时候，
    // 剩下的全是空，那么就是完全二叉树
    boolean isCompleteTree(BTNode root) {
        if (root == null) {
            return true;
        }
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode cur = queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            BTNode top = queue.peek();
            if (top != null) {
                return false;
            }
            queue.poll();
        }
        return true;
    }

    //层序遍历：也是用队列，
    public void levelOrder(BTNode root) {
        Queue<BTNode> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode cur = queue.poll();
            System.out.print(cur.val+" ");
            if (cur != null) {
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }

    public List<List<Character>> levelOrder(BTNode root) {
        List<List<Character>> ret = new ArrayList<>();
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//代表当前层有多少个节点
            List<Character> list = new ArrayList<>();
            while (size != 0) {
                BTNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                size--;
            }
            ret.add(list);
        }
        return ret;
    }
}
