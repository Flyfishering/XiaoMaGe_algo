package com.mj;

import com.mj.Times.Task;

public class Main {

	/*
	 * 0 1 1 2 3 5 8 ....
	 * 斐波那契数列，递归实现
	 * O(2^n).......
	 */
	public static int fib1(int n) {
		if(n <= 1) {
			return n;
		}
		int num = fib1(n - 1) + fib1(n - 2);
		return num;
	}
	
	// O(n)
	public static int fib2(int n) {
		if(n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;
		int sum = 0;
		for(int i = 0;i < n-1;i++) {
			sum = first + second;
			first = second;
			second = sum;
		}
		return sum;
	}
	
	// O(n)
	public static int fib3(int n) {
		if(n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;

		// 相对第二种解法，无需单独定义sum变量
		// while(n-- > 1)  这样写也可以。
		for(int i = 0;i < n-1;i++) {
			second = first + second;
			first = second - first;
		}
		return second;
	}
	
	public static void main(String[] args) {
		Times.test("fib1", new Task() {
			public void execute() {
				System.out.println(fib1(35));
			}
		});
		Times.test("fib2", new Task() {
			public void execute() {
				System.out.println(fib2(35));
			}
		});
	}

}
