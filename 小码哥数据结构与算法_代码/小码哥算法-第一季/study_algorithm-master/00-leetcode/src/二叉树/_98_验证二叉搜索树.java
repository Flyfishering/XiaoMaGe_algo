package 二叉树;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class _98_验证二叉搜索树 {

    TreeNode prev;

    // 利用中序遍历，每个节点跟上个节点比，是否满足升顺
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (left == false) return false;
        if (prev != null && prev.val >= root.val) return false;
        prev = root;
        boolean right = isValidBST(root.right);
        if (right == false) return false;
        return true;
    }

    // 指定每个节点的上限跟下限
    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, null, null);
    }

    private boolean isValidBST2(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;

        if (!isValidBST2(root.left, min, root.val)) return false;
        if (!isValidBST2(root.right, root.val, max)) return false;

        return true;
    }



}
