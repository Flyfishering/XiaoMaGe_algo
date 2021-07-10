package 二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author linkage
 *
 */
public class _102_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<>();
    	
    	if (root == null) return list;
    	
    	Deque<TreeNode> queue = new LinkedList<>();
    	List<Integer> levelList = new ArrayList<>();
    	int levelCount = 1;
    	
    	queue.offer(root);
    	
    	while(!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		levelList.add(node.val);
    		if (node.left != null) queue.offer(node.left);
    		if (node.right != null) queue.offer(node.right);
    		// 该层节点已遍历结束
    		if (--levelCount == 0) {
    			list.add(levelList);
    			levelList = new ArrayList<>();
    			levelCount = queue.size();
    		}
    	}
    	return list;
    }
}
