package 树;

import common.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class _236_二叉树的最近公共祖先 {
    /**
     * 去以root为根节点的二叉树中查找p，q的最近公共祖先
     * ①如果p,q同时存在于这棵二叉树中，就能成功返回他们的最近公共祖先
     * ②如果p,q都不存在于这棵二叉树中，就返回null
     * ③如果只有p存在于这棵二叉树中，就返回p
     * ④如果只有q存在于这棵二叉树中，就返回q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // ③、④的情况
        // 左右都有命中，表明根节点就是要找的最近公共祖先
        if (left != null && right != null) return root;

        // 表明只有左或者右命中，另外一边是空的,或者两边都没有命中。
        return left == null ? right : left;
    }
}
