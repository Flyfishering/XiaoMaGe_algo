package 二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @author Rin
 *
 */
public class _106_从中序与后序遍历序列构造二叉树_递归2 {
	
	int[] inorder;
	int[] postorder;
	int postIndex;
	Map<Integer, Integer> map = new HashMap<>();
	
    public TreeNode buildTree(int left, int right) {
    	if (left > right) return null;
    	int rootVal = postorder[postIndex];
    	int rootIndex = map.get(rootVal);
    	
    	postIndex--;
    	
    	TreeNode root = new TreeNode(rootVal);
    	
    	// 后序遍历，是左子树，右子树，根节点。所以，后序数组从后往前访问的话，先访问到的是右子树的全部节点
    	root.right = buildTree(rootIndex + 1, right);
    	root.left = buildTree(left, rootIndex - 1);

    	return root;
    }
	
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if (inorder.length != postorder.length) throw new IllegalArgumentException("两个数组长度不一致");
    	this.inorder = inorder;
    	this.postorder = postorder;
    	postIndex = postorder.length - 1;
    	for(int i = 0; i < inorder.length; i++) {
    		map.put(inorder[i], i);
    	}
    	TreeNode root = buildTree(0, inorder.length - 1);
    	
    	return root;
    }
}
