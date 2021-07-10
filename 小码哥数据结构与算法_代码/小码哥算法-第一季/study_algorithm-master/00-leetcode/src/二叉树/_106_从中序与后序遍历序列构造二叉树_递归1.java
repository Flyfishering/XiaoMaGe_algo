package 二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @author Rin
 *
 */
public class _106_从中序与后序遍历序列构造二叉树_递归1 {
	
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, 
    		int[] postorder, int postStart, int postEnd, Map<Integer, Integer> map) {
    	if (inStart > inEnd || postStart > postEnd) return null;
    	int rootVal = postorder[postEnd];
    	int rootIndex = map.get(rootVal);
    	
    	TreeNode root = new TreeNode(rootVal);
    	int leftLen = rootIndex - inStart;
    	
    	root.left = buildTree(inorder, inStart, rootIndex - 1, postorder, postStart, postStart + leftLen - 1, map);
    	root.right = buildTree(inorder, rootIndex + 1, inEnd, postorder, postStart + leftLen, postEnd - 1, map);
    	
    	return root;
    }
	
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if (inorder.length != postorder.length) throw new IllegalArgumentException("两个数组长度不一致");
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i = 0; i < inorder.length; i++) {
    		map.put(inorder[i], i);
    	}
    	TreeNode root = buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    	
    	return root;
    }
}
