package 字符串;

import common.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 */
public class _572_另一个树的子树 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        String strS = postSerialize(s);
        String strT = postSerialize(t);

        return strS.contains(strT);
    }

    // 利用后序遍历的方式进行序列化
    private String postSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postSerialize(root, sb);
        return sb.toString();
    }

    private void postSerialize(TreeNode node, StringBuilder sb) {

        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left, sb);
        }
        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right, sb);
        }

        sb.append(node.val).append("!");
    }
}
