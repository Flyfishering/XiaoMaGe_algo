package 二叉树;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * 通过迭代解决
 * @author Rin
 *
 */
public class _450_删除二叉搜索树中的节点_万恶的迭代 {
	
	// 被删除元素的父节点
	public TreeNode pre = null;
    public TreeNode deleteNode(TreeNode root, int key) {
    	if (root == null) return null;
    	
    	TreeNode node = node(root, key);
    	if (node == null) return root;
    	TreeNode suc = null;
        
    	// 度为2的节点
    	if (node.left != null && node.right != null) {
    		suc = successor(node);
    		node(root, suc.val);
    		node.val = suc.val;
    		node = suc;
    	}
    	
    	// 度为0的节点
    	if (node.left == null && node.right == null) {
    		// 删除对象节点为根节点
    		if (pre == null) {
    			return null;
    		} else if (pre.left == node) {
    			pre.left = null;
    		} else {
    			pre.right = null;
    		}
    	} else if (pre == null){
    		if (node.left != null) {
    			root = node.left;
    		} else {
    			root = node.right;
    		}
    	} else {
    		if (pre.left == node) {
    			pre.left = node.left != null ? node.left : node.right;
    		} else {
    			pre.right = node.left != null ? node.left : node.right;
    		}
    	}
    	
    	return root;
    }
    
    // 查找结点
    public TreeNode node(TreeNode root, int key) {
    	if (root == null) return null;
    	TreeNode node = root;
    	pre = null;
    	
    	while(node != null) {
    		if (node.val > key) {
    			pre = node;
    			node = node.left;
    		} else if (node.val < key) {
    			pre = node;
    			node = node.right;
    		} else {
    			return node;
    		}
    	}
    	return null;
    }
    
    // 返回后继节点(本题只有度为2的时候，才会查找后继节点)
    public TreeNode successor(TreeNode node) {
    	
    	node = node.right;
    	
    	while(node.left != null) {
    		node = node.left;
    	}
    	
    	return node;
    }
}
