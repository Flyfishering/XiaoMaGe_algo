package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_二叉树的后序遍历 {

    List<Integer> list = new ArrayList<>();

    // 后序遍历的递归解
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return list;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);

        return list;
    }

    // 后序遍历的迭代解
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;

        while(!stack.isEmpty()) {
            TreeNode top = stack.peek();

            if ((top.left == null && top.right == null)
                    || (prev != null && (top.left == prev || top.right == prev))) {
                prev = stack.pop();
                list.add(prev.val);
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
        return list;
    }
}
