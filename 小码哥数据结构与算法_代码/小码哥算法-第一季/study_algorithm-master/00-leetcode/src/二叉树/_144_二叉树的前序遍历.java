package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * @author Rin
 *
 */
public class _144_二叉树的前序遍历 {
	
	List<Integer> list = new ArrayList<Integer>();
	
	// 最简单的递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
    	if (root == null) return list;
    	
    	list.add(root.val);
    	list = preorderTraversal(root.left);
    	list = preorderTraversal(root.right);
    	
        return list;
    }
    
	// 迭代..........
    public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> list = new ArrayList<>();
    	if (root == null) return list;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while(!stack.isEmpty()) {
    		root = stack.pop();
    		list.add(root.val);
    		if (root.right != null) stack.push(root.right);
    		if (root.left != null) stack.push(root.left);
    	}
    	
    	return list;
    }
    
	// 迭代..........
    // 利用栈模拟前序的执行方式。栈中放的是所有的右子节点。
    // 思想是向左到底，然后找右子节点，重复
    public List<Integer> preorderTraversal3(TreeNode root) {
		List<Integer> list = new ArrayList<>();
    	if (root == null) return list;
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	
    	while(true) {
    		if (node != null) {
        		list.add(node.val);
        		if (node.right != null) stack.push(node.right);
        		node = node.left;
    		} else if (stack.isEmpty()) {
    			return list;
    		} else {
    			node = stack.pop();
    		}
    	}
    }
    
	// 迭代FFFF
    // 利用栈模拟前序的执行方式。栈中放的是所有的节点。
    public List<Integer> preorderTraversal4(TreeNode root) {
		List<Integer> list = new ArrayList<>();
    	if (root == null) return list;
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	
    	while(node != null || !stack.isEmpty()) {
    		while(node != null) {
    			list.add(node.val);
    			stack.push(node);
    			node = node.left;
    		}
    		node = stack.pop();
    		node = node.right;
    	}
    	return list;
    }

    // 20201224 重刷迭代
	// 栈中存放了所有的右子节点
	public List<Integer> preorderTraversal5(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) return list;
		Stack<TreeNode> stack = new Stack<>();

		while (root != null) {
			list.add(root.val);
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				root = root.left;
			} else if (!stack.isEmpty()){
				root = stack.pop();
			} else {
				root = null;
			}
		}
		return list;
	}

	// 20201224 重刷迭代
	// 用栈模拟前序迭代的顺序
	public List<Integer> preorderTraversal6(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) return list;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode node;

		while (!stack.isEmpty()) {
			node = stack.pop();
			list.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return list;
	}
    
}
