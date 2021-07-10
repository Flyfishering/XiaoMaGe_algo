package com.mj.tree;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class AVLTree<E> extends BinarySearchTree<E> {
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		// 新加入的节点的祖父节点，才有可能失衡。所以一路往上，寻找失衡节点
		while ((node = node.parent) != null) {
			if (isBalance(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				// 第一个失衡节点恢复平衡后，整棵树也都恢复了平衡，无需继续遍历下去
				break;
				
			}
		}

	}
	
	// 删除
	@Override
	protected void afterRemove(Node<E> node) {
		while((node = node.parent) != null) {
			if (isBalance(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
			}
		}
	}
	
	/**
	 * 统一做法恢复平衡
	 * @param grand 祖父节点
	 */
	private void rebalance(Node<E> grand) {
		// parent为两颗子树中高的那一颗
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		// node为两颗子树中高的那一颗
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		// L
		if (parent.isLeftChild()) {
			// LL
			if (node.isLeftChild()) {
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			} else { // LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			} else { // RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
			
		}
	}
	
    // 统一旋转
	private void rotate(
			Node<E> r, // 子树的根节点
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		// 让d成为这颗子树的根节点
		d.parent = r.parent;
		if (r.isLeftChild()) {
			d.parent.left = d;
		} else if (r.isRightChild()) {
			d.parent.right = d;
		} else {
			root = d;
		}
		
		// a-b-c
		b.left = a;
		b.right = c;
		
		if (a != null) a.parent = b;
		if (c != null) c.parent = b;
		
		updateHeight(b);
		
		// e-f-g
		f.left = e;
		f.right = g;
		
		if (e != null) {
			e.parent = f;
		}
		if (g != null) {
			g.parent = f;
		}
		updateHeight(f);
		
		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
	}
	
	
	/**
	 * 恢复平衡(通过左旋转，右旋转恢复平衡)
	 * @param grand 高度最低的不平衡节点
	 */
	private void rebalance2(Node<E> grand) {
		// parent为两颗子树中高的那一颗
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		// node为两颗子树中高的那一颗
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		// L
		if (parent.isLeftChild()) {
			// LL
			if (node.isLeftChild()) {
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotateRight(parent);
				rotateLeft(grand);
			} else { // RR
				rotateLeft(grand);
			}

		}
	}
	
	// 左旋转
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
		
		afterRotate(grand, parent, child);
	}
	
	// 右旋转
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	// 更新父节点，更新高度
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // 不为左也不为右，表示grand的父节点为空
			root = parent;
		}

		// child有可能为空
		if (child != null) {
			child.parent = grand;
		}
		
		grand.parent = parent;
		
		updateHeight(grand);
		updateHeight(parent);
	}
	
	// 更新传进来的节点的高度
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}
	
	// 通过复写父类的创建节点达到返回AVL特有节点的目的
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	}
	
	// 判断节点是否平衡
	private boolean isBalance(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private static class AVLNode<E> extends Node<E> {
		// 每次创建出来的叶子节点，高度都是1
		int height = 1;
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		// 获取平衡因子
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode)right).height;
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode)right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode)right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			
			// 如果两边子树高度一样，返回跟父节点相同方向的子树。例，该节点是父节点的左子树，此时返回该节点的左子树
			return isLeftChild() ? left : right;
		}
	}
}
