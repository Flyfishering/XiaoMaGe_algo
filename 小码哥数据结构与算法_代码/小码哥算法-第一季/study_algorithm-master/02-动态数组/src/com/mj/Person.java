package com.mj;

public class Person {
	private String name;
	private int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		// 仅当类型相同时，才会做强转。
		if(obj instanceof Person) {
			Person person = (Person) obj;
			return name.equals(person.name);
		}
		return false;
	}
}
