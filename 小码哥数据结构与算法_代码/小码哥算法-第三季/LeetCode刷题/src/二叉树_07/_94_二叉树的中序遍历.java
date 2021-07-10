package 二叉树_07;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
* */
public class _94_二叉树的中序遍历 extends _00_baseTree {

    /*
    * 1.递归实现
    * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root,res);
        return res;
    }
    public void inorder(TreeNode root, List<Integer> res){
        if (root == null) return;
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }

    /*
    * 2.迭代实现
    * */
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
                res.add(node.val); // 访问该节点
                node = node.right; // 处理右节点
            }
        }
        return res;
    }
}
