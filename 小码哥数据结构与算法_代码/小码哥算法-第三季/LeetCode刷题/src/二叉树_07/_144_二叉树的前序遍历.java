package 二叉树_07;

/*
* https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
* */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _144_二叉树的前序遍历 extends _00_baseTree {

    /*
     * 1.递归实现
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root,res);
        return res;
    }

    public void preorder(TreeNode root,List<Integer> res){
        if (root == null) return;
        res.add(root.val);
        preorder(root.left,res);
        preorder(root.right,res);
    }

    /*
     * 2.迭代实现
     * */
    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (true) {
            if (node != null) {
                res.add(node.val); // 访问当前节点
                if (node.right != null) { // 将右节点入栈
                    stack.push(node.right);
                }
                node = node.left;// 然后继续访问左节点
            } else if (stack.isEmpty()) { // 当没有左节点,并且栈中也没有数字,说明已经访问了所以节点了
                break;
            } else {
                node = stack.pop(); // 去遍历栈中的右节点,假如右节点又有左节点时,依然会走入第一个判断里
            }
        }
        return res;
    }

    /*
    * 前序遍历迭代的另一种实现
    * */
    public List<Integer> preorderTraversal3(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // 是不是跟层序遍历很像,只不过是把队列换成了栈
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
}
