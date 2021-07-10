package 二叉树_07;

import java.util.LinkedList;
import java.util.Queue;

/*
* leetcode上没有原题
* 什么是完全二叉树
* 就是从上到下,从左到右,依次都有元素
* eg:     5
*      2    6
*   1    3
* */
public class _999_是否是完全二叉树 extends _00_baseTree {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false; // 标记叶子节点

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (leaf && !isLeaf(node)) return false; // 发现以后必须是叶子节点,但遍历出的节点不是叶子节点,则直接返回false

            if (node.left != null) { // 如果左子树不为空
                queue.offer(node.left);
            } else if (node.right != null){
                return false; //能走到这里,左子树一定为空,右子树不为空,直接返回false
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else { // 到这里,右子树一定是空
               leaf = true; // 所以 以后遍历的元素必须得是叶子节点
            }
        }
        return true;
    }

    public boolean isLeaf(TreeNode node) {
        return  node.left == null && node.right == null;
    }
}
