package com.mj;

public class Main {

    public static void main(String args[]) {
        Fib fib = new Fib();
        System.out.println(fib.fib1(6));
    }

    int sum(int n) {
        if (n <= 1) return n;
        return n + sum(n - 1);
    }

    int fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
