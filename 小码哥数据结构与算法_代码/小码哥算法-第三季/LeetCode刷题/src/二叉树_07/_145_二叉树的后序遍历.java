package 二叉树_07;
/*
* https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
* */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _145_二叉树的后序遍历 extends _00_baseTree {

    /*
     * 1.递归实现
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root,res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res){
        if (root == null) return;
        postorder(root.left,res);
        postorder(root.right,res);
        res.add(root.val);
    }

    /*
     * 2.迭代实现
     * 其实就是按(节点,右节点,左节点)的顺序入栈,这样pop的时候就可以按照(左节点,右节点,节点)的顺序访问了
     * */
    public List<Integer> postorderTraversal1(TreeNode root){
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode preNode = null;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek(); // 拿到当前的栈顶元素,去入栈右节点和左节点
            if (isLeaf(node) || (preNode != null && (node.left == preNode || node.right == preNode))) {
                /*
                * 能进入到这里说明满足了以下任意两种情况
                * 1.node是叶子节点,则else里的入栈代码就没必要执行
                * 2.当前拿的节点的子节点之前访问过,所以之前入过栈现在已经弹出了,如果不要这个条件容易形成循环
                * */
                preNode = stack.pop();
                res.add(preNode.val);
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return res;
    }
    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
