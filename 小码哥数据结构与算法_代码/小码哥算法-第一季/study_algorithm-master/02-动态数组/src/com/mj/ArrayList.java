package com.mj;

@SuppressWarnings("unchecked")
public class ArrayList<E> {
	
	// 元素的数量
	private int size;
	
	// 存放数据的数组
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	// 构造函数

	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	// 无参构造函数
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 清除所有元素
	 */
	public void clear() {
		// 思想是能循环利用的留下，其余的释放掉。所以这边不写elements = null;
		// 因为这样写后，要重新开辟数组的内存空间
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	/**
	 * 返回元素的数量
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return
	 */
	public E set(int index, E element) {
		rangeCheck(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}
	
	/**
	 * 添加元素到最后
	 * @param element
	 */
	public void add(E element) {
		add(size, element);
	}
	
	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
		for(int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}

		elements[index] = element;
		size++;

	}
	
	/**
	 * 删除index位置的元素
	 * @param index
	 * @return 删除元素的值
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}

		E old = elements[index];
		for(int i = index + 1;i < size; i++) {
			elements[i-1] = elements[i];
		}
		elements[--size] = null;
		return old;
	}
	
	public void remove(E element) {
		int index = indexOf(element);
		if(index != -1) {
			remove(index);
		}
	}
	
	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	public int indexOf(E element) {
		if(element == null) {
			for(int i = 0; i < size; i++) {
				// 对象的比较用equals。直接用==比较，比较的是内存地址。
				// 如果类没有覆写equals方法，那效果和==一样，默认比较内存地址
				if(elements[i] == null) return i;
			}
		} else {
			for(int i = 0; i < size; i++) {
				// 对象的比较用equals。直接用==比较，比较的是内存地址。
				// 如果类没有覆写equals方法，那效果和==一样，默认比较内存地址
				if(element.equals(elements[i])) return i;
			}
		}

		return ELEMENT_NOT_FOUND;
	}
	
	@Override
	public String toString() {
		// size=3, [1, 221, 99]
		StringBuilder sb = new StringBuilder();
		
		sb.append("size=").append(size).append(", [");
		for(int i = 0;i < size; i++) {
			if(i != 0) sb.append(", ");
			sb.append(elements[i]);
			//if(i != size - 1) sb.append(", ");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}
	
	private void rangeCheck(int index) {
		if(index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if(index < 0 || index > size) {
			outOfBounds(index);
		}
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
			newElements[i] = elements[i];
		}

		elements = newElements;
	}

}
