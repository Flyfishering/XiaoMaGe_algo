package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_二叉树的层序遍历_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelCount = 1;
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);

            if (--levelCount == 0) {
                levelCount = queue.size();
                ans.add(0, list);
                list = new ArrayList<>();
            }

        }
        return ans;
    }
}
