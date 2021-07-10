package com.mj;

public class Fib {

    int fib0(int n) {
        if (n <= 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib1(n, array);
    }

    int fib1(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fib1(n - 1, array) + fib1(n - 2, array);
        }
        return array[n];
    }

    int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        return array[n];
    }

    /**
     * 4 % 2 = 0   0b100 & 1
     * 3 % 2 = 1   0b011 & 1
     * 2 % 2 = 0   0b010 & 1
     */
    int fib3(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2]; // 滚动数组
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }

        return array[n & 1];
    }

    int fib4(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }

        return second;
    }
}
