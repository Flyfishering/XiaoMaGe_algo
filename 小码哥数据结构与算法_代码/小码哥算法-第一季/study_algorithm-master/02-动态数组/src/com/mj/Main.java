package com.mj;

public class Main {

	public static void main(String[] args) {
		// new是向对空间申请内存
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		list.add(list.size(), 100);
		
		System.out.println(list);
		System.out.println(list.indexOf(2));
		
		ArrayList<Person> list2 = new ArrayList<>();
		list2.add(new Person("sanae", 17));
		list2.add(new Person("marie", 16));
		list2.add(new Person("honoka", 16));
		
		System.out.println(list2);

	}

}
