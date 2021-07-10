package 二叉树;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class _543_二叉树的直径 {
    // https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/java-shen-du-you-xian-bian-li-dfs-by-sugar-31/
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res;
    }

    // 求当前树的深度，遍历的过程中同时找最大的直径
    private int depth(TreeNode node) {
        if (node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);

        // 一个节点的最大直径 = 它左树的高度 +  它右树的高度
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
}
