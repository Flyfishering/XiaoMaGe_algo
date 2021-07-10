package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @author linkage
 *
 */
public class _104_二叉树的最大深度 {
	// 递归解
    public int maxDepth(TreeNode root) {
    	if (root == null) return 0;
    	return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
	// 迭代解
    public int maxDepth2(TreeNode root) {
    	
    	if (root == null) return 0;
    	Queue<TreeNode> queue = new LinkedList<>();
    	int levelCount = 1;
    	int height = 0;
    	
    	queue.offer(root);
    	
    	while(!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if (node.left != null) queue.offer(node.left);
    		if (node.right != null) queue.offer(node.right);
    		if (--levelCount == 0) {
    			levelCount = queue.size();
    			height++;
    		}
    	}
    	return height;
    }
}
