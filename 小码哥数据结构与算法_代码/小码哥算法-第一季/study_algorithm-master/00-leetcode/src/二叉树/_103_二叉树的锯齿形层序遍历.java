package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class _103_二叉树的锯齿形层序遍历 {
    // 双端队列，自己乱想的，容易错的解法。。
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        int levelCount = 1;
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node;
            if (leftToRight) {
                node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            } else {
                node = queue.pollLast();
                list.add(node.val);
                if (node.right != null) {
                    queue.offerFirst(node.right);
                }
                if (node.left != null) {
                    queue.offerFirst(node.left);
                }
            }

            if (--levelCount == 0) {
                leftToRight = !leftToRight;
                levelCount = queue.size();
                ans.add(list);
                list = new ArrayList<>();
            }
        }
        return ans;
    }

    // 官方题解的做法
    // 还是标准的层序，只是加入结果list的时候，按照层之间从左到右，从右到左的顺序去加入
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Deque<Integer> levelQueue = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    levelQueue.offerLast(node.val);
                } else {
                    levelQueue.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            leftToRight = !leftToRight;
            ans.add(new LinkedList<>(levelQueue));
        }
        return ans;
    }
}
