package 二叉树;

import java.util.Stack;

public class _783_二叉搜索树节点最小距离 {
    int res = Integer.MAX_VALUE;
    TreeNode pre;
    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        return res;
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        if (pre != null) {
            res = Math.min(res, node.val - pre.val);
        }
        pre = node;
        inOrder(node.right);
    }


    // 迭代(利用栈实现中序遍历)
    public int minDiffInBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        int res = Integer.MAX_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null) {
                res = Math.min(res, root.val - prev.val);
            }
            prev = root;
            root = root.right;
        }
        return res;
    }
}
