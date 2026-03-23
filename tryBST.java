import java.util.*;

class tNode {
    int key;
    tNode left, right;

    public tNode(int key) {
        this.key = key;
        left = right = null;
    }
}
class BST {
    tNode root;

    public tNode insert(tNode node, int key) {
        if (node == null) return new tNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else
            node.right = insert(node.right, key);

        return node;
    }
     public void buildBalanced(int start, int end) {
        if (start > end) return;

        int mid = (start + end) / 2;
        root = insert(root, mid);

        buildBalanced(start, mid - 1);
        buildBalanced(mid + 1, end);
    }
     public boolean isBST() {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
     private boolean isBSTUtil(tNode node, int min, int max) {
        if (node == null) return true;

        if (node.key <= min || node.key >= max)
            return false;

        return isBSTUtil(node.left, min, node.key) &&
               isBSTUtil(node.right, node.key, max);
    }
       public tNode deleteNode(tNode root, int key) {
        if (root == null) return null;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            int minValue = findMin(root.right);
            root.key = minValue;
            root.right = deleteNode(root.right, minValue);
        }
        return root;
    }
 private int findMin(tNode node) {
        while (node.left != null)
            node = node.left;
        return node.key;
    }

    public tNode removeEvens(tNode node) {
        if (node == null) return null;

        node.left = removeEvens(node.left);
        node.right = removeEvens(node.right);

        if (node.key % 2 == 0)
            return deleteNode(node, node.key);

        return node;
    }
}
public class tryBST {

    public static double stdDev(long[] times, double avg) {
        double sum = 0;
        for (long t : times) {
            sum += Math.pow(t - avg, 2);
        }
        return Math.sqrt(sum / times.length);
    }

    public static void main(String[] args) {

        int n = 20;
        int max = (int)Math.pow(2, n) - 1;

        int runs = 30;

        long[] populateTimes = new long[runs];
        long[] removeTimes = new long[runs];
 
