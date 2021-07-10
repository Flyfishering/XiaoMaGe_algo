package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/boundary-of-binary-tree/
 */
public class _545_二叉树的边界 {
    // 分别求出左边界，叶子节点，右边界
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 根节点
        ans.add(root.val);
        if (root.left == null && root.right == null) {
            return ans;
        }

        TreeNode node;

        // 左边界
        if (root.left != null) {
            node = root.left;
            while (!isLeaf(node)) {
                ans.add(node.val);
                if (node.left != null) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }

        // 叶子节点
        addLeafNode(root, ans);

        // 右边界
        Stack<Integer> stack = new Stack<>();
        if (root.right != null) {
            node = root.right;
            while (!isLeaf(node)) {
                stack.push(node.val);
                if (node.right != null) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }

        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }

        return ans;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private void addLeafNode(TreeNode node, List<Integer> list) {
        if (node == null) return;
        if (isLeaf(node)) {
            list.add(node.val);
        }
        addLeafNode(node.left, list);
        addLeafNode(node.right, list);
    }
}
