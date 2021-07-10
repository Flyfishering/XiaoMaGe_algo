package 二叉树_07;

import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode-cn.com/problems/invert-binary-tree/
* */
public class _226_翻转二叉树 extends _00_baseTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
