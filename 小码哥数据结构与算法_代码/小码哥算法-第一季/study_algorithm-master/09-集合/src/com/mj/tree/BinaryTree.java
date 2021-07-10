package com.mj.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class BinaryTree<E> {

	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * 前序遍历(根结点在前面)
	 * 根节点，前序遍历左子树，前序遍历右子树
	 * 以下是递归实现
	 */
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}
	
	public void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}
	
	/**
	 * 非递归实现,利用栈。栈里面存放的是全部的右子节点
	 * @param visitor
	 */
	public void preorder2(Visitor<E> visitor) {
		if (root == null || visitor == null) return;
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		
		while(true) {
			if (node != null) {
				// 访问node节点
				visitor.visit(node.element);
				// 右子节点入栈
				if (node.right != null) stack.push(node.right);
				// 向左走
				node = node.left;
			} else if (stack.isEmpty()){
				return;
			} else {
				node = stack.pop();
			}

		}
	}
	
	/**
	 * 找前驱结点(中序排序的前一个节点)
	 * @param node
	 * @return
	 */
	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return node;
		Node<E> pre = node.left;
		
		// 左子树不为空的时候，前驱结点为左子树中的最大节点(最右)。(left.right.right....)
		if(pre != null) {
			while(pre.right != null) {
				pre = pre.right;
			}
			return pre;
		}
		
		// 从祖父节点中寻找前驱结点
		// 当父节点不为空且为父节点的左子节点的时候，一直循环(为右子节点的父节点是要找的节点)
		while(node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		
		// node.parent == null || node == node.parent.right
		return node.parent;
	}
	
	/**
	 * 找后继结点(中序排序的后一个节点)
	 * @param node
	 * @return
	 */
	protected Node<E> successor(Node<E> node) {
		if (node == null) return node;
		Node<E> pre = node.right;
		
		// 右子树不为空的时候，后继结点为右子树中的最小节点(最左)。(right.left.left....)
		if(pre != null) {
			while(pre.left != null) {
				pre = pre.left;
			}
			return pre;
		}
		
		// 从祖父节点中寻找后继结点
		// 当父节点不为空且为父节点的右子树的时候，一直循环(为左子节点的父节点是要找的节点)
		while(node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		// node.parent == null || node == node.parent.left
		return node.parent;
	}
	
	/**
	 * 中序遍历(根结点在中间)
	 * 中序遍历左子树，根节点，中序遍历右子树
	 * 以下是递归实现
	 */
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}
	
	public void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		inorder(node.left, visitor);
		visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	
	/**
	 * 后序遍历(根结点在最后)
	 * 后序遍历左子树，后序遍历右子树，根节点
	 * 以下是递归实现
	 */
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}
	
	public void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		visitor.visit(node.element);
	}
	
	/**
	 * 层序遍历  要求能默写出来。前序等也要求，但因为是递归所以好写
	 */
	public void levelOrder(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			Node<E> node = queue.poll();
			visitor.visit(node.element);
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
	}
	
	/**
	 * 判断一棵树是否是完全二叉树
	 * @return
	 */
	public boolean isComplete() {
		if (root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		
		while(!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if(leaf && !node.isLeaf()) {
				return false;
			}
			
			if(node.hasTwoChildren()) {
				queue.offer(node.left);
				queue.offer(node.right);
			} else if (node.left == null && node.right != null) {
				return false;
			} else {
				// 要么左右子节点都为空，要么左节点不为空，右节点为空。这时，剩下节点都应该为叶子节点
				leaf = true;
				if (node.left != null) {
					queue.offer(node.left);
				}
			}
		}
		return true;
	}
	
	/**
	 * 判断一棵树是否是完全二叉树
	 * 在1的基础上做出了优化
	 * @return
	 */
	public boolean isComplete2() {
		if(root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		boolean leaf = false;
		while(!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			// 从第一个叶子节点开始，之后若有不是叶子的节点，则不是完全二叉树
			if(leaf && !node.isLeaf()) return false;
			
			if(node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null){
				// left == null && right != null
				return false;
			}
			if(node.right != null) {
				queue.offer(node.right);
			} else {
				// left == null && right == null
				// left != null && right == null
				leaf = true;
			}
		}
		return true;
	}
	
	// 树的高度,迭代实现(层序遍历)
	public int height() {
		if (root == null) return 0;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量(根节点所在层有一个元素)
		int levelSize = 1;
		
		while(!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--; // 每次poll后，该层剩余元素减1
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
			
			if (levelSize == 0) { // levelSize == 0意味着即将访问下一层
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}
	
	// 树的高度
	public int height2() {
		return height(root);
	}
	
	// 节点的高度，递归实现
	private int height(Node<E> node) {
		if (node == null) return 0;
		
		return 1 + Math.max(height(node.left), height(node.left));
	}
	
	/**
	 * 通过visitor来让外界决定如何访问取到的元素
	 * @author Rin
	 *
	 * @param <E>
	 */
	public static interface Visitor<E> {
		void visit(E element);
	}
	
	// 为了让子类可以复写自己的创建
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return (left == null && right == null);
		}
		
		public boolean hasTwoChildren() {
			return (left != null && right != null);
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		// 返回兄弟节点
		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			if (isRightChild()) {
				return parent.left;
			}

			// 没有父节点，则没有兄弟节点
			return null;
		}
	}

}
