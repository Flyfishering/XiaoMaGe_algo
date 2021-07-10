package com.mj;

import com.mj.list.ArrayList;
import com.mj.list.List;

// 选择继承的话，会提供很多没必要的接口。所以可以选择，组合的方式来实现栈
public class Stack<E> {
	
	// 可选择动态数组，或链表的实例
	private List<E> list = new ArrayList<>();
	
	public int size() {
		return list.size();
	}
	
	public void clear() {
		list.clear();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(E element) {
		list.add(element);
	}
	
	public E pop() {
		return list.remove(list.size() - 1);
	}
	
	public E top() {
		return list.get(list.size() - 1);
	}
}
