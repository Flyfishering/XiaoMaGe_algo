package 二叉树_07;

import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode-cn.com/problems/balanced-binary-tree/
* */
public class _110_平衡二叉树 extends _00_baseTree{
    public boolean isBalanced(TreeNode root) {
        return nodeHeight(root) != -1;
    }
    public int nodeHeight(TreeNode node){
        if (node == null) return 0;
        int left = nodeHeight(node.left);
        if (left == -1) return -1;
        int right = nodeHeight(node.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left,right) + 1 : -1;
    }
}
