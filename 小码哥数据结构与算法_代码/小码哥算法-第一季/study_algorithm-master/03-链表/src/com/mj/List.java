package com.mj;

public interface List<E> {
	
	static final int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 清除所有元素
	 */
	void clear();
	
	/**
	 * 返回元素数量
	 */
	int size();
	
	/**
	 * 是否为空
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	boolean contains(E element);
	
	/**
	 * 添加元素到最后
	 * @param element
	 */
	void add(E element);
	
	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	void add(int index, E element);
	
	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	E get(int index);
	
	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return
	 */
	E set(int index, E element);
	
	/**
	 * 删除index位置的元素
	 * @param index
	 * @return 删除元素的值
	 */
	E remove(int index);
	
	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	int indexOf(E element);
	
}
