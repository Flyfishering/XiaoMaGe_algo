package com.mj.circle;

import com.mj.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E>{

	private Node<E> first;
	private Node<E> last;
	private Node<E> current;

	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		public Node(Node<E> prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if(prev == null) {
				sb.append("null");
			} else {
				sb.append(prev.element);
			}
			sb.append("_" + element + "_");
			if(next == null) {
				sb.append("null");
			} else {
				sb.append(next.element);
			}
			return sb.toString();
		}
	}
	
	public void reset() {
		current = first;
	}
	
	public E next() {
		if(current == null) return null;
		
		current = current.next;
		return current.element;
	}

	public E remove() {
		if(current == null) return null;
		
		Node<E> next = current.next;
		E element = remove(current);
		if(size == 0) {
			current = null;
		} else {
			current = next;
		}

		return element;
	}
	
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		// 往最后面添加元素
		if(index == size) {
			last = new Node<E>(last, element, first);
			if(last.prev == null) { // 链表中原来没有元素的时候，prev == null
				first = last;
				first.next = first;
				first.prev = first;
			} else {
				last.prev.next = last;
				first.prev = last;
			}

		} else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<E>(prev, element, next);
			
			prev.next = node;
			next.prev = node;
			// index==0的时候
			if(next == first) {
				first = node;
			}

		}

		size++;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		return remove(node(index));
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		if(element == null) {
			for(int i = 0; i < size; i++) {
				if(node.element == null) return i;
				node = node.next;
			}
		} else {
			for(int i = 0; i < size; i++) {
				// 对象的比较用equals。直接用==比较，比较的是内存地址。
				// 如果类没有覆写equals方法，那效果和==一样，默认比较内存地址
				if(element.equals(node.element)) return i;
				node = node.next;
			}
		}

		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取index位置对应的节点对象
	 * @param index
	 * @return
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		if(index < (size >> 1)) {
			Node<E> node = first;
			while(index-- > 0) {
				node = node.next;
			}
			//for(int i = 0; i < index; i++)
			return node;
		} else {
			Node<E> node = last;
			for(int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
	}
	
	private E remove(Node<E> node) {
		
		if(size == 1) {
			first = null;
			last = null;
		} else {
			
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
			if(node == first) { // index == 0
				first = node.next;
			} 
			
			if(node == last) { // index == size - 1
				last = node.prev;
			}
		}

		size--;
		return node.element;
	}
	
	@Override
	public String toString() {
		// size=3, [1, 221, 99]
		StringBuilder sb = new StringBuilder();
		Node<E> node = first;
		
		sb.append("size=").append(size).append(", [");
		for(int i = 0;i < size; i++) {
			if(i != 0) sb.append(", ");
			sb.append(node);
			node = node.next;
			//if(i != size - 1) sb.append(", ");
		}
		
//		while(node != null) {
//			node = node.next;
//		}
		sb.append("]");
		
		return sb.toString();
	}
}
