package 树;

import common.TreeNode;

/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 */
public class _99_恢复二叉搜索树 {
    //记录下中序遍历中的上一个节点
    private TreeNode prev;
    // 第一个错误节点
    private TreeNode first;
    // 第二个错误节点
    private TreeNode second;
    // 利用中序遍历来找到错误节点并恢复。时间复杂度是O(n)，空间是O(h)
    public void recoverTree(TreeNode root) {
        findWrongNode(root);
        // 交换两个错误节点的值
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void findWrongNode(TreeNode root) {
        if (root == null) return;
        findWrongNode(root.left);

        // 出现了逆序对
        if (prev != null && prev.val > root.val) {
            // 第二个错误节点，是最后一个逆序对中较小的那个节点
            second = root;
            // 第一个错误节点，是第一个逆序对中较大的那个节点
            if (first != null) return;
            first = prev;
        }
        prev = root;

        findWrongNode(root.right);
    }

    // 利用morris来达到空间O(1)
    public void recoverTree2(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                // 找到前驱节点(predecessor)
                TreeNode pred = node.left;
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    pred.right = node;
                    node = node.left;
                } else { // pred.right = node
                    find(node);
                    pred.right = null;
                    node = node.right;
                }
            } else {
                find(node);
                node = node.right;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void find(TreeNode node) {
        if (prev != null && prev.val > node.val) {
            second = node;
            if (first != null) return;
            first = prev;
        }
        prev = node;
    }

}
