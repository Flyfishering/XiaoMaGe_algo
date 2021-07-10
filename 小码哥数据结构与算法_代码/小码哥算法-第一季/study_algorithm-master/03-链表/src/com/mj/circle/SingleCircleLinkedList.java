package com.mj.circle;

import com.mj.AbstractList;

public class SingleCircleLinkedList<E> extends AbstractList<E>{

	private Node<E> first;

	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append(element + "_");
			if(next == null) {
				sb.append("null");
			} else {
				sb.append(next.element);
			}
			return sb.toString();
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		if(index == 0) {

			Node<E> newFirst = new Node<>(element, first);
			// size为0的时候，说明添加前，这是一个空链表,size - 1的值为-1
			Node<E> last = size == 0 ? newFirst : node(size - 1); 
			first = newFirst;
			last.next = first;
		} else {
			Node<E> prev = node(index-1);
			prev.next = new Node<>(element, prev.next);
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
		E old = first.element;
		if(index == 0) {
			if(size == 1) {
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}

		} else {
			Node<E> prev = node(index - 1);
			old = prev.next.element;
			prev.next = prev.next.next;
		}
		
		size--;
		return old;
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
		
		Node<E> node = first;
		while(index-- > 0) {
			node = node.next;
		}
		//for(int i = 0; i < index; i++)
		return node;
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
