package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class _110_平衡二叉树 {
    // 时间复杂度为O(n*logn)，笨办法。。不要用
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 利用层序遍历(O(n))
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 左右子树高度差大于等于2的话，则判断为不平衡
            if (Math.abs(height(node.left) - height(node.right)) >=2) {
                return false;
            }
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        // 所有节点都是平衡的话，返回true
        return true;
    }

    // 利用递归求节点的高度。时间复杂度为O(logn)
    public int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

    // 优化的算法。利用后序遍历，自下而上，一次遍历。
    // 时间复杂度为O(logn)
    public boolean isBalanced2(TreeNode root) {
        return height2(root) != -1;
    }

    // 利用后序遍历判断节点是否平衡
    public int height2(TreeNode root) {
        if (root == null) return 0;
        int left = height2(root.left);
        int right = height2(root.right);

        if (left == -1) return -1;
        if (right == -1) return -1;

        // 若左右子树高度差超过了2，则返回-1，不然返回当前节点的高度
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
