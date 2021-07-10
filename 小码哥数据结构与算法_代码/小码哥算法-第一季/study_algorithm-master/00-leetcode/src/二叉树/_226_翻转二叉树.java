package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author Rin
 *
 */
public class _226_翻转二叉树 {
	// 使用前序遍历
    public TreeNode invertTree1(TreeNode root) {
    	if(root == null) return root;

    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	invertTree1(root.left);
    	invertTree1(root.right);
    	
    	return root;
    }
    
	// 使用中序遍历
    public TreeNode invertTree2(TreeNode root) {
    	if(root == null) return root;

    	invertTree2(root.left);
    	
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	invertTree2(root.left); // 注意，左右已经交换了！
    	
    	return root;
    }
    
	// 使用后序遍历
    public TreeNode invertTree3(TreeNode root) {
    	if(root == null) return root;
    	
    	invertTree3(root.left);
    	
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	invertTree3(root.right);
    	
    	return root;
    }
    
    // 层序遍历
    public TreeNode invertTree4(TreeNode root) {
    	if (root == null) return root;
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	while(!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		TreeNode temp = node.left;
    		node.left = node.right;
    		node.right = temp;
    		if (node.left != null) {
    			queue.offer(node.left);
    		}
    		
    		if (node.right != null) {
    			queue.offer(node.right);
    		}
    	}
    	return root;
    	
    }
}
