package 二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * @author Rin
 *
 */
public class _105_从前序与中序遍历序列构造二叉树_递归 {
	
	public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd,
		int inStart, int inEnd, Map<Integer, Integer> map) {
		if (preStart > preEnd) return null;
		int rootVal = preorder[preStart];
		int rootIndex = map.get(rootVal);
		
		TreeNode node = new TreeNode(rootVal);
		
		int leftLen = rootIndex - inStart;
		
		node.left = buildTree(preorder, inorder, preStart + 1, preStart + leftLen, 
				inStart, rootIndex - 1, map);
		node.right = buildTree(preorder, inorder, preStart + leftLen + 1, preEnd, 
				rootIndex + 1, inEnd, map);
		
		return node;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length == 0) return null;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0; i < inorder.length; i++) {
    		map.put(inorder[i], i);
    	}
    	
    	TreeNode root = buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    	
    	return root;
    }
}
