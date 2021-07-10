package 二叉树;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * 通过递归解决
 * @author Rin
 *
 */
public class _450_删除二叉搜索树中的节点_递归 {
	
    public TreeNode deleteNode(TreeNode root, int key) {
    	if (root == null) return null;
    	
    	if (root.val > key) {
    		root.left = deleteNode(root.left, key);
    	} else if (root.val < key) {
    		root.right = deleteNode(root.right, key);
    	} else { // 相等，找到了要删除的节点
//    		if (root.left == null) {
//    			return root.right;
//    		} else if (root.right == null) {
//    			return root.left;
//    		} else {
//    			...
//    		}
    		
    		if (root.left == null && root.right == null) {
    			return null;
    		} else if (root.left != null && root.right == null) {
    			return root.left;
    		} else if (root.left == null && root.left != null) {
    			return root.right;
    		} else { // 度为2
    			TreeNode node = root.right;
    			TreeNode pre = root;
    			while(node.left != null) { // 循环过后，node节点为后继节点
    				pre = node;
    				node = node.left;
    			}
    			root.val = node.val;
    			if (pre.left == node) {
    				// 后继节点的左一定为空，所以这边直接给右节点。
    				pre.left = node.right; 
    			} else {
    				pre.right = node.right;
    			}
    			return root;
    		}
    	}
    	return root;
    }
}
