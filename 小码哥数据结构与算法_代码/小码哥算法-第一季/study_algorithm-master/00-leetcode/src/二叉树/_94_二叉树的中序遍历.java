package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class _94_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();

        // 还有待遍历元素时，继续循环
        while (root != null || !stack.isEmpty()) {
            // 一路向左，一直到不能再左
            while (root != null) {
                // 保存一路向左路上的节点
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);

            // 向右，右子树也进行相同操作
            root = root.right;
        }
        return list;
    }

    // 递归用list
    List<Integer> list = new ArrayList<>();
    // 递归
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) return list;
        inorderTraversal2(root.left);
        list.add(root.val);
        inorderTraversal2(root.right);
        return list;
    }


}
