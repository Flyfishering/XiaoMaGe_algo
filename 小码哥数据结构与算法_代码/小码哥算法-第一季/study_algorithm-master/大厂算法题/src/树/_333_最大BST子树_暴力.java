package 树;
import common.TreeNode;
/**
 * https://leetcode-cn.com/problems/largest-bst-subtree/
 */
public class _333_最大BST子树_暴力 {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isBST(root)) return getCount(root);
        int left = largestBSTSubtree(root.left);
        int right = largestBSTSubtree(root.right);
        return Math.max(left, right);
    }

    private int getCount(TreeNode root) {
        if (root == null) return 0;
        return getCount(root.left) + getCount(root.right) + 1;
    }

    private boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

}
