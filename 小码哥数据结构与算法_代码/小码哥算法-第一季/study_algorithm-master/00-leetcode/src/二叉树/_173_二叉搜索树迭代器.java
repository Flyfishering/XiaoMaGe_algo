package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class _173_二叉搜索树迭代器 { // 利用中序遍历的递归

    List<TreeNode> list;
    int index;
    public _173_二叉搜索树迭代器(TreeNode root) {
        list = new ArrayList<>();
        TreeNode node = new TreeNode(Integer.MIN_VALUE);
        node.right = root;
        list.add(node);
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        list.add(node);
        inOrder(node.right);
    }

    public int next() {
        return list.get(++index).val;
    }

    public boolean hasNext() {
        return (index + 1) < list.size();
    }
}

class BSTIterator { // 中序遍历的迭代
    TreeNode idxNode;
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        TreeNode node = new TreeNode(Integer.MIN_VALUE);
        node.right = root;
        stack = new Stack<>();
        idxNode = node;
    }

    public int next() {
        while (idxNode != null) {
            stack.push(idxNode);
            idxNode = idxNode.left;
        }
        idxNode = stack.pop();

        int val = idxNode.val;
        idxNode = idxNode.right;
        return val;
    }

    public boolean hasNext() {
        return idxNode != null || !stack.isEmpty();
    }
}


