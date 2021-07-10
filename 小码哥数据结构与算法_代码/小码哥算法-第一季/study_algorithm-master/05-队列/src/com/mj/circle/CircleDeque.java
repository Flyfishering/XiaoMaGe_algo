package com.mj.circle;


/**
 * 循环双端队列
 * @author Rin
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class CircleDeque<E> {
	private int front;
	private int size;
	private E[] elements;
	
	private static int DEFAULT_CAPACITY = 10;
	

	public CircleDeque() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 从尾部入队
	 * @param element
	 */
	public void enQueueRear(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}
	
	/**
	 * 从头部入队
	 * @param element
	 */
	public void enQueueFront(E element) {
		ensureCapacity(size + 1);
		front = index(-1);
		elements[front] = element;
		size++;
	}
	
	/**
	 * 从尾部出队
	 * @return
	 */
	public E deQueueRear() {
		E rear = elements[index(size - 1)];
		elements[index(size - 1)] = null;
		size--;
		return rear;
	}
	
	/**
	 * 从头部出队
	 * @return
	 */
	public E deQueueFront() {
		E frontE = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return frontE;
	}
	
	/**
	 * 返回头部
	 * @return
	 */
	public E front() {
		return elements[front];
	}
	
	/**
	 * 返回尾部
	 * @return
	 */
	public E rear() {
		return elements[index(size - 1)];
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[index(i)] = null;
		}
		front = 0;
		size = 0;
	}
	
	private int index(int index) {
		index += front;
		if(index < 0) return index + elements.length;
		return index % elements.length;
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
			newElements[i] = elements[index(i)];
		}

		elements = newElements;
		// 循环队列动态扩容后，front重新回到index0的位置
		front = 0;
	}
}
