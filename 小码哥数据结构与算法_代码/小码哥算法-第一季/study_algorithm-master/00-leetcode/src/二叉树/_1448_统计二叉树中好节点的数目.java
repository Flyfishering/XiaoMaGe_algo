package 二叉树;

/**
 * https://leetcode-cn.com/problems/count-good-nodes-in-binary-tree/
 */
public class _1448_统计二叉树中好节点的数目 {
    int count;
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        goodNodes(root, root.val);
        return count;
    }

    private void goodNodes(TreeNode node, int max) {
        if (node == null) return;
        if (node.val >= max) {
            count++;
            max = node.val;
        }
        goodNodes(node.left, max);
        goodNodes(node.right, max);
    }
}
