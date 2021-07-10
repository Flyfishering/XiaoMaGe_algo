package com.mj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo{
	private int size;
	private Node<E> root;
	private Comparator<E> comparator; // 比较器
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree() {
		
	}
	
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
	
	public void add(E element) {
		elementNotNullCheck(element);
		// 添加第一个节点
		if(root == null) {
			root = new Node<E>(element, null);
			size++;
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
		Node<E> newNode = new Node<>(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		if (node == null) return;
		
		if (node.hasTwoChildern()) { // 度为2的节点
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
			
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
		} else { // node是叶子节点但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
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
	
//	/**
//	 * 前序遍历(根结点在前面)
//	 * 根节点，前序遍历左子树，前序遍历右子树
//	 * 以下是递归实现
//	 */
//	public void preorderTraversal() {
//		preorderTraversal(root);
//	}
//	
//	public void preorderTraversal(Node<E> node) {
//		if(node == null) return;
//		
//		System.out.print(node.element + ", ");
//		preorderTraversal(node.left);
//		preorderTraversal(node.right);
//	}
//	
//	/**
//	 * 中序遍历(根结点在中间)
//	 * 中序遍历左子树，根节点，中序遍历右子树
//	 * 以下是递归实现
//	 */
//	public void inorderTraversal() {
//		inorderTraversal(root);
//	}
//	
//	public void inorderTraversal(Node<E> node) {
//		if(node == null) return;
//		
//		inorderTraversal(node.left);
//		System.out.print(node.element + ", ");
//		inorderTraversal(node.right);
//	}
//	
//	/**
//	 * 后序遍历(根结点在最后)
//	 * 后序遍历左子树，后序遍历右子树，根节点
//	 * 以下是递归实现
//	 */
//	public void postorderTraversal() {
//		postorderTraversal(root);
//	}
//	
//	public void postorderTraversal(Node<E> node) {
//		if(node == null) return;
//		
//		postorderTraversal(node.left);
//		postorderTraversal(node.right);
//		System.out.print(node.element + ", ");
//	}
//	
//	/**
//	 * 层序遍历  要求能默写出来。前序等也要求，但因为是递归所以好写
//	 */
//	public void levelOrderTraversal() {
//		if(root == null) return;
//		
//		Queue<Node<E>> queue = new LinkedList<>();
//		queue.offer(root);
//		
//		while(!queue.isEmpty()) {
//			Node<E> node = queue.poll();
//			System.out.println(node.element);
//			if(node.left != null) queue.offer(node.left);
//			if(node.right != null) queue.offer(node.right);
//		}
//	}
	
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
	 * @param visitor 访问器
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
			
			if(node.hasTwoChildern()) {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	/**
	 * 找前驱结点(中序排序的前一个节点)
	 * @param node
	 * @return
	 */
	private Node<E> predecessor(Node<E> node) {
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
	private Node<E> successor(Node<E> node) {
		if (node == null) return node;
		Node<E> pre = node.right;
		
		// 右子树不为空的时候，后继结点为右子树中的最小节点(最左)。(right.left.left....)
		if(pre != null) {
			while(pre.left != null) {
				pre = pre.left;
			}
			return pre;
		}
		
		// 从祖父节点中寻找前驱结点
		// 当父节点不为空且为父节点的右子节点的时候，一直循环(为左子节点的父节点是要找的节点)
		while(node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		// node.parent == null || node == node.parent.left
		return node.parent;
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
	
	private static class Node<E> {
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
		
		public boolean hasTwoChildern() {
			return (left != null && right != null);
		}
	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		return ((Node<E>)node).element;
	}
}
