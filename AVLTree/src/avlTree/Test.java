package avlTree;

public class Test {

    public static void main(String[] args) {
//        int[] array = {16, 3, 7, 11, 9, 26, 18, 14, 15};
        int[] array = {4, 2, 6, 1, 3, 5, 15, 7, 16, 14};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < array.length; i++) {
            avlTree.insert(array[i]);
        }

        System.out.println(avlTree.isBalanced(avlTree.root));
    }
}
