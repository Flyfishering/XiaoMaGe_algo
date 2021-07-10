package com.mj.tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E>{

	private Comparator<E> comparator; // 比较器
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree() {
		this(null);
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		// 添加第一个节点
		if(root == null) {
			root = createNode(element, null);
			size++;
			
			afterAdd(root);
			return;
		}
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		while(node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if(cmp > 0) {
				node = node.right;
			} else if(cmp < 0) {
				node = node.left;
			} else { // 相等
				node.element = element;
				return;
			}
		}
		Node<E> newNode = createNode(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		afterAdd(newNode);
	}
	
	/**
	 * 添加node之后的调整
	 * @param node 新添加的节点
	 */
	protected void afterAdd(Node<E> node) { }

	/**
	 * 删除node之后的调整
	 * @param node 被删除的节点,或者被删除节点的子节点(度为1的时候用到)
	 */
	protected void afterRemove(Node<E> node) { }

//	/**
//	 * 删除node之后的调整
//	 * @param node 被删除的节点
//	 * @param replacement 红黑树删除时候需要判定替代的节点颜色，AVL树用不到
//	 */
//	protected void afterRemove(Node<E> node, Node<E> replacement) { }
	
	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		if (node == null) return;

		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> successor = successor(node);
			// 用后继节点的值覆盖要删除节点的值
			node.element = successor.element;
			// 删除后继节点(让if外面的代码去删)
			node = successor;
		}
		
		// 删除node节点(此时节点的度必然是0或者1)
		Node<E> replacement = node.left == null ? node.right : node.left;
		// replacement为空，表示node节点度为0
		if (replacement != null) { // 度为1
			 // 更改parent
			replacement.parent = node.parent;
			
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.right) {
				node.parent.right = replacement;
			} else {
				node.parent.left = replacement;
			}
			
			// 度为2的情况下，真正被删除的是，后继或者前驱节点。
			// 删除节点后的处理
			afterRemove(replacement);
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
			// 度为2的情况下，真正被删除的是，后继或者前驱节点。
			// 删除节点后的处理
			afterRemove(node);
		} else { // node是叶子节点但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			// 度为2的情况下，真正被删除的是，后继或者前驱节点。
			// 删除节点后的处理
			afterRemove(node);
		}
		
		size--;
	}
	
	// 查找当前元素，返回所在节点
	private Node<E> node(E element) {
		Node<E> node = root;
		while(node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			} else if (cmp > 0) {
				node = node.right;
			} else { // cmp < 0
				node = node.left;
			}
		}
		return null;
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	// 利用前序遍历来打印
	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if(node == null) return;
		sb.append(prefix).append("【").append(node.element).append("】").append("\n");
		toString(node.left, sb, prefix + "【L】");
		toString(node.right, sb, prefix + "【R】");
		
	}
	
	private void elementNotNullCheck(E element) {
		if(element == null) {
			throw new IllegalArgumentException("element must not be null!");
		}
	}
	
	/**
	 * 
	 * @return 返回值等于0，代表e1和e2相等。大于0，代表e1大于e2。小于0，代表e1小于e2
	 */
	private int compare(E e1, E e2) {
		// 有比较器的话优先用比较器
		if(comparator != null) {
			return comparator.compare(e1, e2);
		}
		// 没有比较器的话，强转。如果强转失败，表示不可比较，而二叉搜索树的元素，必须可以比较。
		return ((Comparable<E>)e1).compareTo(e2);
	}


}
