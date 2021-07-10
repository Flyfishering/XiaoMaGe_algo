package com.mj;

public class Person {
    int age;
    float height;
    String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        // 内存地址
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        // 比较成员变量
        if (age != person.age) return false;
        if (Float.compare(person.height, height) != 0) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }
}
