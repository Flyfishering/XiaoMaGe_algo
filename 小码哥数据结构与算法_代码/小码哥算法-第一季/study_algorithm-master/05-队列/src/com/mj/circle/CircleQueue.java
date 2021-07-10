package com.mj.circle;

/**
 * 循环队列。
 * @author Rin
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private int front;
	private int size;
	private E[] elements;
	
	private static int DEFAULT_CAPACITY = 10;
	

	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enQueue(E element) {
		ensureCapacity(size + 1);
		elements[(front + size) % elements.length] = element;
		size++;
	}
	
	// 返回队首元素
	public E front() {
		return elements[front];
	}
	
	public E deQueue(E element) {
		E frontE = elements[front];
		elements[front] = null;
		front = (front + 1) % elements.length;
		size--;
		return frontE;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[(front + i) % elements.length] = null;
		}
		front = 0;
		size = 0;
	}
	
	/**
	 * 确保elements数组有capacity的容量
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		// 如果现有容量可以满足要求，则直接返回
		if(oldCapacity >= capacity) return;
		
		// 如果现有容量不可以满足，则重新创建一个新数组
		
		// 新容量为旧容量的1.5倍(java的推荐？ios一般推荐1.6)
		// >>为位移操作(二进制操作),效率很高。右移1位，相当于除以2。为什么，可以自己想想看。
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[(front + i) % elements.length];
		}

		elements = newElements;
		// 循环队列动态扩容后，front重新回到index0的位置
		front = 0;
	}
}
